package com.mrizkisaputra.service;

import com.mrizkisaputra.exception.VirtualAccountAlreadyPaidException;
import com.mrizkisaputra.exception.VirtualAccountNotFoundException;
import com.mrizkisaputra.model.entity.PaymentProvider;

import java.math.BigDecimal;

public interface PaymentService {
    public void pay(PaymentProvider paymentProvider,
                    String companyId, String accountNumber,
                    BigDecimal amount, String reference) throws VirtualAccountNotFoundException, VirtualAccountAlreadyPaidException;
}
