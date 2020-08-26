package com.example.demo.api;

import com.example.demo.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/async")
public class AsyncController {
    private static Logger logger = Logger.getLogger(AsyncController.class.getName());
    @Autowired
    private AsyncService asyncService=null;
    @GetMapping("/test")
    public String Test() throws InterruptedException {
        asyncService.TestAsync();
        logger.info(Thread.currentThread().getName());
        return "this async";
    }

}
