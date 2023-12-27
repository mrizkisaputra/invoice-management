package com.mrizkisaputra.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity(name = "virtual_accounts")
@SQLDelete(sql = "UPDATE virtual_accounts SET status_record = 'INACTIVE' WHERE id = ?")
@Where(clause = "status_record = 'ACTIVE'")
public class VirtualAccount extends BaseEntity<String> {
    @NotNull
    @ManyToOne @JoinColumn(name = "id_payment_provider")
    private PaymentProvider paymentProvider;

    @NotNull
    @ManyToOne @JoinColumn(name = "id_invoice")
    private Invoice invoice;

    @NotNull @NotEmpty @Size(min = 1)
    private String companyId;

    @NotNull @NotEmpty @Size(min = 3, max = 100)
    private String accountNumber;

    @NotNull @Enumerated(EnumType.STRING)
    private VirtualAccountType virtualAccountType = VirtualAccountType.CLOSED;
}
