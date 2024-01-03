package com.mrizkisaputra.utils;

import com.mrizkisaputra.exception.VirtualAccountAlreadyPaidException;
import com.mrizkisaputra.exception.VirtualAccountNotFoundException;
import com.mrizkisaputra.model.entity.PaymentProvider;
import com.mrizkisaputra.model.entity.VirtualAccount;
import com.mrizkisaputra.repository.VirtualAccountRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

public class VirtualAccountUtil {
    public static VirtualAccount checkVaExist(PaymentProvider paymentProvider, String companyId,
                                              String accountNumber, VirtualAccountRepository virtualAccountRepository
    ) throws VirtualAccountNotFoundException {
        Optional<VirtualAccount> optVa =
                virtualAccountRepository.findByPaymentProviderAndCompanyIdAndAccountNumber(paymentProvider, companyId, accountNumber);
        VirtualAccount virtualAccount = optVa.orElseThrow(() -> new VirtualAccountNotFoundException("VA [" + companyId + "/" + accountNumber + "-" + paymentProvider.getCode() + "] not found"));
        return virtualAccount;
    }

    public static void checkVaPaid(PaymentProvider paymentProvider, String companyId,
                                   String accountNumber, VirtualAccount virtualAccount
    ) throws VirtualAccountAlreadyPaidException {
        if (virtualAccount.getInvoice().getPaid()) {
            throw new VirtualAccountAlreadyPaidException("VA [" + companyId + "/" + accountNumber + "-" + paymentProvider.getCode() + "] already paid");
        }
    }
}
