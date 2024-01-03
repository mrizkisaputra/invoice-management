package com.mrizkisaputra.service;

import com.mrizkisaputra.model.entity.Invoice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InvoiceServiceImplTest {

    @Autowired InvoiceService invoiceService;

    @Test
    void createInvoice() {
        Invoice invoice = invoiceService.createInvoice();
    }
}