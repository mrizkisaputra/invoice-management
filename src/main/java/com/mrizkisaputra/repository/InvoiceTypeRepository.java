package com.mrizkisaputra.repository;

import com.mrizkisaputra.model.entity.InvoiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceTypeRepository extends JpaRepository<InvoiceType, String> {
}
