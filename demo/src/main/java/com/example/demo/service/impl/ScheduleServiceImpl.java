package com.example.demo.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ScheduleServiceImpl {
    private static Logger logger=Logger.getLogger(ScheduleServiceImpl.class.getName());
    int count1 = 1;
    int count2 = 1;

    @Scheduled(fixedRate = 1000)
    @Async
    public void  job1(){
        logger.info("job1:"+Thread.currentThread().getName()+" ----- count:"+count1);
        count1++;
    }

    @Scheduled(fixedRate = 1000)
    @Async
    public void  job2(){
        logger.info("job2:"+Thread.currentThread().getName()+" ----- count:"+count2);
        count2++;
    }
    int count3=1;
    int count4=1;
    @Scheduled(initialDelay = 3000,fixedRate = 1000)
    @Async
    public void  job3(){
        logger.info("job3:"+Thread.currentThread().getName()+" ----- count:"+count3);
        count3++;
    }
    @Scheduled(cron = "*/5 * * * * ?")
    @Async
    public void  job4(){
        logger.info("job4:"+Thread.currentThread().getName()+" ----- count:"+count4);
        count4++;
    }
}
