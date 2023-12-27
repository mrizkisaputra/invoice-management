package com.mrizkisaputra.model.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity(name = "payment_providers")
@SQLDelete(sql = "UPDATE payment_providers SET status_record = 'INACTIVE' WHERE id = ?")
@Where(clause = "status_record = 'ACTIVE'")
public class PaymentProvider extends BaseEntity<String> {
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 3, max = 100)
    private String code;

    @NotNull @NotEmpty @NotBlank @Size(min = 8, max = 255)
    private String name;

    private String logo;
}
