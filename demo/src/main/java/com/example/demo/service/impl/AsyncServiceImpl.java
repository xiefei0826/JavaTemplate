package com.example.demo.service.impl;

import com.example.demo.service.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class AsyncServiceImpl implements AsyncService {
    private static Logger logger = Logger.getLogger(AsyncServiceImpl.class.getName());
    @Override
    @Async
    public void TestAsync() throws InterruptedException {
        Thread.sleep(10000);
        logger.info(" this is async");
        logger.info(Thread.currentThread().getName());

    }
}
