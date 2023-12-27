package com.mrizkisaputra.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity(name = "invoices")
@SQLDelete(sql = "UPDATE invoices  SET status_record = 'INACTIVE' WHERE id = ?")
@Where(clause = "status_record = 'ACTIVE'")
public class Invoice extends BaseEntity<String> {
    @NotNull @NotEmpty @Size(min = 8, max = 100)
    private String invoiceNumber;

    @NotNull @Min(0)
    private BigDecimal amount;

    @NotNull
    private Boolean paid = false;

    @NotNull
    private Instant dueDate;

    @Lob
    @NotNull @NotBlank @Size(min = 8)
    private String description;

    @ManyToOne @JoinColumn(name = "id_invoice_type")
    private InvoiceType invoiceType;
}
