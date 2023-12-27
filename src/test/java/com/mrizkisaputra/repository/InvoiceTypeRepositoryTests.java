package com.mrizkisaputra.repository;

import com.mrizkisaputra.model.entity.InvoiceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = {"/sql/delete-invoice-type.sql", "/sql/insert-soft-delete-invoice-type.sql"})
class InvoiceTypeRepositoryTests {
    @Autowired InvoiceTypeRepository repository;

    private InvoiceType invoiceType;

    @Test
    public void jpaAuditing() throws InterruptedException {
        InvoiceType it = new InvoiceType();
        it.setName("Pembayaran SPP");
        it.setCode("IT-001-SPP");
        it.setDescription("melakukan pembayaran spp tahap 1");

        invoiceType = repository.save(it);
        Assertions.assertNotNull(invoiceType.getId());
        Assertions.assertNotNull(invoiceType.getName());
        Assertions.assertEquals("melakukan pembayaran spp tahap 1", invoiceType.getDescription());
        Assertions.assertNotNull(invoiceType.getStatusRecord());
        Assertions.assertNotNull(invoiceType.getCreatedBy());
        Assertions.assertNotNull(invoiceType.getCreatedDate());
        Assertions.assertNotNull(invoiceType.getModifiedBy());
        Assertions.assertNotNull(invoiceType.getLastModifiedDate());

        Thread.sleep(2000);
        invoiceType.setName("Muhammat Rizki");
        repository.save(invoiceType);
    }

    @Test
    public void softDelete() {
        InvoiceType it = repository.findById("ID-001").get();
        repository.delete(it);
        Assertions.assertEquals(0, repository.count());
    }
}