package com.staj.faturalama.repository;

import com.staj.faturalama.entity.BillingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BillingAccountRepository extends JpaRepository<BillingAccount , Long>{
}
