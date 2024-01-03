package com.mrizkisaputra.service;

import com.mrizkisaputra.model.entity.RunningNumber;
import com.mrizkisaputra.repository.RunningNumberRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@NoArgsConstructor
public class RunningNumberServiceImpl implements RunningNumberService {
    private RunningNumberRepository rnRepository;

    @Autowired
    public RunningNumberServiceImpl(RunningNumberRepository runningNumberRepository) {
        this.rnRepository = runningNumberRepository;
    }

    @Override
    public Long getNumber(String prefix) {
        RunningNumber runningNumber = rnRepository.findByPrefix(prefix);
        if (runningNumber == null) {
            runningNumber = new RunningNumber();
            runningNumber.setPrefix(prefix);
        }

        runningNumber.setLastNumber(runningNumber.getLastNumber() + 1);
        rnRepository.save(runningNumber);
        return runningNumber.getLastNumber();
    }
}
