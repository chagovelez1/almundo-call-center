package com.almundo.callcenter.services;

import com.almundo.callcenter.entities.Call;
import com.almundo.callcenter.entities.User;
import com.almundo.callcenter.exceptions.CustomError;
import com.almundo.callcenter.parsers.DispatchCallResponseParser;
import com.almundo.callcenter.repositories.CallRepository;
import com.almundo.callcenter.repositories.UserRepository;
import com.almundo.callcenter.utils.UserTaken;
import com.almundo.callcenter.validations.DispatchCallValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DispatcherService {
    @Autowired
    CallRepository callRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserTaken userTaken;

    public DispatchCallResponseParser dispatch(DispatchCallValidation request) {
        Call call = this.createCall(request.getTelephone());

        User user = this.findAvailableUser();
        this.assignUserToCall(user, call);

        DispatchCallResponseParser response = new DispatchCallResponseParser();
        response.setCallId(call.getId());
        response.setUserId(user == null ? null : user.getId());
        return response;
    }

    private User findAvailableUser(){
        boolean validation = false;
        User user = null;

        while (!validation){
            user = userRepository.findAvailable();

            if(user != null && userTaken.validateUserIsNotInDispatchProcess(user.getId())){
                validation = true;
            } else if (user == null){
                validation = true;
            }
        }

        return user;
    }

    private void assignUserToCall(User user, Call call){
        if (user != null){
            LocalDateTime now = LocalDateTime.now();

            user.setCurrentCall(call);
            user.setCurrentCallStartTime(now);
            userRepository.save(user);

            call.setStartTime(now);
            call.setAssignedUser(user);
            callRepository.save(call);

            userTaken.finishDispatchProcess(user.getId()); //TODO: do something if finishDispatchProcess() returns false, something might have went wrong
        }
    }

    private Call createCall(String telephone) {
        Call call = new Call();
        call.setTelephoneNumber(telephone);
        call.setIncomeTime(LocalDateTime.now());
        return callRepository.insert(call);
    }

    public void endCall(String callId) throws CustomError {
        Call call = callRepository.findById(callId);

        if (call != null && call.getEndTime() == null && call.getAssignedUser() != null){
            LocalDateTime now = LocalDateTime.now();
            call.setEndTime(now);
            callRepository.save(call);

            User user = call.getAssignedUser();
            user.setCurrentCallStartTime(null);
            user.setCurrentCall(null);
            userRepository.save(user);
        }else {
            throw new CustomError("can not finish given call id or is already finished");
        }
    }
}
