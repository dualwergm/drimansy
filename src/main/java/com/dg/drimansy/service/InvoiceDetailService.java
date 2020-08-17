package com.dg.drimansy.service;

import org.springframework.stereotype.Service;

import com.dg.drimansy.repository.IInvoiceDetailRepository;

@Service
public class InvoiceDetailService {
	private final IInvoiceDetailRepository invoiceDetailRepository;
	
	public InvoiceDetailService(IInvoiceDetailRepository invoiceDetailRepository) {
		this.invoiceDetailRepository = invoiceDetailRepository;
	}
	
	public void deleteById(Long id) {
		invoiceDetailRepository.deleteById(id);
	}
}
