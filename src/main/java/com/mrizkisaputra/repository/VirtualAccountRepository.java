package com.mrizkisaputra.repository;

import com.mrizkisaputra.model.entity.VirtualAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VirtualAccountRepository extends JpaRepository<VirtualAccount, String> {
}
