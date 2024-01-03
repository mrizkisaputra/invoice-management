package com.mrizkisaputra.model.entity;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity(name = "customers")
@SQLDelete(sql = "UPDATE customers  SET status_record = 'INACTIVE' WHERE id = ?")
@Where(clause = "status_record = 'ACTIVE'")
public class Customer extends BaseEntity<String> {
    @NotNull @NotEmpty @Size(max = 100)
    private String code;

    @NotNull @NotEmpty @Size(max = 255)
    private String name;

    @Email
    @NotNull @NotEmpty @Size(max = 100)
    private String email;

    @NotNull @NotEmpty @Size(max = 13) @Min(0)
    private String mobilePhone;
}
