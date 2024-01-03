package com.mrizkisaputra.service;

import com.mrizkisaputra.repository.RunningNumberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RunningNumberServiceImplTest {
    @Autowired RunningNumberService rnService;

    @Test
    void getNumber() {
        Long result = rnService.getNumber("Tests");
        Assertions.assertNotNull(result);
        System.out.println("Result = "+result);
    }

    @Test
    void multiThread() throws InterruptedException {
        for (var i = 1; i <= 10; i++) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    Long result = rnService.getNumber("tests");
                    System.out.println("Thread ["+this.getId()+"] last = "+result);
                }
            };
            thread.start();
        }

        Thread.sleep(10 * 1000);
    }
}