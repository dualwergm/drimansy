package com.dg.drimansy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dg.drimansy.model.InvoiceDetail;

public interface IInvoiceDetailRepository extends JpaRepository<InvoiceDetail, Long>{
}
