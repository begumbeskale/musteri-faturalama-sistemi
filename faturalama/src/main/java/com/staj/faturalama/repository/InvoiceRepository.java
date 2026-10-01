package com.staj.faturalama.repository;

import com.staj.faturalama.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface InvoiceRepository extends JpaRepository<Invoice , Long>{
}
