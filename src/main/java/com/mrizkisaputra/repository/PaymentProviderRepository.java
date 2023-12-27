package com.mrizkisaputra.repository;

import com.mrizkisaputra.model.entity.PaymentProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentProviderRepository extends JpaRepository<PaymentProvider, String> {
}
