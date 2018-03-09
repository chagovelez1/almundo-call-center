package com.almundo.callcenter;

import com.almundo.callcenter.entities.User;
import com.almundo.callcenter.fakeFactory.UserFactory;
import com.almundo.callcenter.parsers.DispatchCallResponseParser;
import com.almundo.callcenter.repositories.UserRepository;
import com.almundo.callcenter.services.DispatcherService;
import com.almundo.callcenter.utils.UserTypesEnum;
import com.almundo.callcenter.validations.DispatchCallValidation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class CallCenterApplicationTests {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DispatcherService dispatcherService;

	@Test
	public void dispatcherTest() {
	    mongoTemplate.getDb().drop();
	    Integer operadorAmount = new Random().nextInt(10)+10;
	    Integer supervisorAmount = new Random().nextInt(2)+2;
	    Integer directorAmount = new Random().nextInt(1)+1;

        this.createUsers(operadorAmount, UserTypesEnum.OPERADOR.getType());
        this.createUsers(supervisorAmount, UserTypesEnum.SUPERVISOR.getType());
        this.createUsers(directorAmount, UserTypesEnum.DIRECTOR.getType());

        //First $operadorAmount calls should be assigned to operators
        Assert.assertEquals(true, this.createCallAndCheckUserType(operadorAmount, UserTypesEnum.OPERADOR.getType()));

        //subsequent $supervisorAmount calls should be assigned to supervisors
        Assert.assertEquals(true, this.createCallAndCheckUserType(supervisorAmount, UserTypesEnum.SUPERVISOR.getType()));

        //subsequent $directorAmount calls should be assigned to directors
        Assert.assertEquals(true, this.createCallAndCheckUserType(directorAmount, UserTypesEnum.DIRECTOR.getType()));

        //last call (1+ of the amount of active users) should be returned without userId witch means pending for assignation with the cron job CheckUnasignedCallsCron
        Assert.assertEquals(false, this.createCallAndCheckUserType(1,null));
	}

	private void createUsers(Integer amount, String type){
	    for(int i = 0; i <= amount; i++){
            User user = new UserFactory().getUser();
            user.setType(type);
            userRepository.insert(user);
        }
    }

    private boolean createCallAndCheckUserType(Integer amount, String type){
        Random random = new Random();
        DispatchCallValidation request = new DispatchCallValidation();

	    for(int i = 0; i <= amount; i++){
            request.setTelephone(String.valueOf(random.nextInt()));
            DispatchCallResponseParser response = dispatcherService.dispatch(request);

            if (response.getUserId() == null){

                return false;
            }else if (type != null && !type.equals(userRepository.findById(response.getUserId()).getType())){

                return false;
            }
        }

        return true;
    }
}
