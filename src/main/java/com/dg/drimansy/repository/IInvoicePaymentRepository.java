package com.dg.drimansy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dg.drimansy.model.InvoicePayment;

public interface IInvoicePaymentRepository extends JpaRepository<InvoicePayment, Long>{
	@Query("Select i from InvoicePayment i order by i.date desc")
	public List<InvoicePayment> findAllOrdered();
	
	@Query("Select i from InvoicePayment i where i.invoice.id = :invoiceId order by i.date desc")
	public List<InvoicePayment> findByInvoiceId(@Param("invoiceId")Long invoiceId);
	
	@Query("Select sum(i.value) from InvoicePayment i where i.invoice.id = :invoiceId")
	public Double sumPayments(@Param("invoiceId")Long invoiceId);
}
