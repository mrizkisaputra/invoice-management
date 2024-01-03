package com.mrizkisaputra.repository;

import com.mrizkisaputra.model.entity.PaymentProvider;
import com.mrizkisaputra.model.entity.VirtualAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VirtualAccountRepository extends JpaRepository<VirtualAccount, String> {
    @Query(
            value = "select va from VirtualAccount va where va.id_payment_provider=:paymentProvider and va.companyId=:companyId and va.accountNumber=:accountNumber",
            nativeQuery = true
    )
    Optional<VirtualAccount> findByPaymentProviderAndCompanyIdAndAccountNumber(@Param("paymentProvider") PaymentProvider paymentProvider,
                                                                               @Param("companyId") String companyId,
                                                                               @Param("accountNumber")String accountNumber);
}
