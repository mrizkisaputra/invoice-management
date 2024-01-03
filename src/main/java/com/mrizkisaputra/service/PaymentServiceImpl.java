package com.mrizkisaputra.service;

import com.mrizkisaputra.exception.VirtualAccountAlreadyPaidException;
import com.mrizkisaputra.exception.VirtualAccountNotFoundException;
import com.mrizkisaputra.model.entity.*;
import com.mrizkisaputra.repository.VirtualAccountRepository;
import com.mrizkisaputra.utils.VirtualAccountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional(
        propagation = Propagation.REQUIRES_NEW,
        rollbackFor = {VirtualAccountAlreadyPaidException.class, VirtualAccountNotFoundException.class}
)
public class PaymentServiceImpl implements PaymentService {
    private final VirtualAccountRepository virtualAccountRepository;

    @Autowired
    public PaymentServiceImpl(VirtualAccountRepository virtualAccountRepository) {
        this.virtualAccountRepository = virtualAccountRepository;
    }

    @Override
    public void pay(PaymentProvider paymentProvider, String companyId,
                    String accountNumber, BigDecimal amount, String reference)
            throws VirtualAccountNotFoundException, VirtualAccountAlreadyPaidException {

        VirtualAccount virtualAccount = VirtualAccountUtil.checkVaExist(paymentProvider, companyId, accountNumber, virtualAccountRepository);
        VirtualAccountUtil.checkVaPaid(paymentProvider, companyId, accountNumber, virtualAccount);
        // 3. Cek apakah amount pembayaran > nilai tagihan
        // 4. Update status va jadi lunas
        virtualAccount.setStatusRecord(StatusRecord.INACTIVE);

        // 5. Update status invoice jadi lunas
        Invoice invoice = virtualAccount.getInvoice();
        invoice.setTotalPayment(invoice.getTotalPayment().add(amount));
        invoice.setPaymentStatus(PaymentStatus.FULL);

        // 6. Insert ke table payment
        // 7. Notifikasi (next fase)
    }


}
