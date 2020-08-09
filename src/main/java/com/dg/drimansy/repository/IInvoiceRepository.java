package com.dg.drimansy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dg.drimansy.model.Invoice;

public interface IInvoiceRepository extends JpaRepository<Invoice, Long>{
	@Query("Select i from Invoice i order by i.date desc")
	public List<Invoice> findAllOrdered();
}
