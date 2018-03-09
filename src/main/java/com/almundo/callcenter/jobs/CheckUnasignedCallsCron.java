package com.almundo.callcenter.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CheckUnasignedCallsCron {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Scheduled(fixedRateString = "${values.cron-jobs.check-unasigned-calls-millis}")
    public void run(){
        LOGGER.info("runing {}", CheckUnasignedCallsCron.class);
        //TODO: check if there are unasigned calls, then try to assign them if posible and comunicate the asigned user via messaging (RabitMQ for example) or by Http request
    }
}
