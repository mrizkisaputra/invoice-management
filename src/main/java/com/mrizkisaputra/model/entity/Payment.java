package com.mrizkisaputra.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity(name = "payments")
@SQLDelete(sql = "UPDATE payments SET status_record = 'INACTIVE' WHERE id = ?")
@Where(clause = "status_record = 'ACTIVE'")
public class Payment extends BaseEntity<String> {
    @NotNull
    @ManyToOne @JoinColumn(name = "id_virtual_account")
    private VirtualAccount virtualAccount;

    @NotNull
    private LocalDateTime transactionTime;

    @NotNull @NotEmpty
    private String providerReference;

    @NotNull @Min(1)
    private BigDecimal amount;
}
