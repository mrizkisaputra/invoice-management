package com.mrizkisaputra.service;

import com.mrizkisaputra.model.entity.Invoice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
    @Override
    public Invoice createInvoice() {
        Invoice invoice = new Invoice();
        return invoice;
    }
}
