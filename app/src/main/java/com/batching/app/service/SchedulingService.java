package com.batching.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchedulingService {
    private final UsageHistoryService service;
//    private int random = 10;
    @Scheduled(cron = "*/5 * * * * *") // Execute every 1 second
    @Async // Execute in a separate thread
    public void deleteOldRecords() {
        // Task logic goes here
        System.out.println("Task 1 executed.");
//        try {
//
//            service.deleteAllBy(--random);
//        }catch (Exception e){
//            System.out.println("Task 1 executed."+e.getMessage());
//        }
    }
}
