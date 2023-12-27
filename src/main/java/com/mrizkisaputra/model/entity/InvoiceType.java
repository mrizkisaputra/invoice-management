package com.mrizkisaputra.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity(name = "invoice_type")
@SQLDelete(sql = "UPDATE invoice_type SET status_record = 'INACTIVE' WHERE id = ?")
@Where(clause = "status_record = 'ACTIVE'")
public class InvoiceType extends BaseEntity<String> {
    @NotNull @NotEmpty @NotBlank @Size(min = 3, max = 100)
    private String code;

    @NotNull @NotEmpty @NotBlank @Size(min = 8, max = 255)
    private String name;

    @Lob
    @NotNull @NotEmpty @NotBlank @Size(min = 8)
    private String description;

    @ManyToMany
    @JoinTable(
            name = "invoice_type_providers",
            joinColumns = @JoinColumn(name = "id_invoice_type"),
            inverseJoinColumns = @JoinColumn(name = "id_payment_provider")
    )
    private Set<PaymentProvider> paymentProviders = new LinkedHashSet<>();
}
