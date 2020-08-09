package com.dg.drimansy.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dg.drimansy.model.InvoicePayment;
import com.dg.drimansy.repository.IInvoicePaymentRepository;
import com.dg.drimansy.view.vo.InvoicePaymentVO;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class InvoicePaymentService {
	private final IInvoicePaymentRepository paymentRepository;

	public InvoicePaymentService(IInvoicePaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	@Transactional(readOnly = true)
	public List<InvoicePayment> findAllOrdered() {
		return this.paymentRepository.findAllOrdered();
	}

	public List<InvoicePaymentVO> getPaymentsVO(List<InvoicePayment> payments) {
		List<InvoicePaymentVO> list = new ArrayList<InvoicePaymentVO>();
		for (InvoicePayment payment: payments) {
			InvoicePaymentVO paymentVO = new InvoicePaymentVO();
			paymentVO.toVO(payment);
			list.add(paymentVO);
		}
		return list;
	}

	public InvoicePayment getPaymentFromWeb(JsonNode root) throws ParseException {
		JsonNode jMaster = (JsonNode)root.get("jMaster");
		InvoicePayment payment = new InvoicePayment();
		payment.toEntity(jMaster);
		return payment;
	}
	
	public void save(InvoicePayment payment) {
		this.paymentRepository.saveAndFlush(payment);
	}
	
	@Transactional(readOnly = true)
	public InvoicePayment findById(long id) {
		Optional<InvoicePayment> findById = this.paymentRepository.findById(Long.valueOf(id));
		return findById.isPresent() ? findById.get() : new InvoicePayment();
	}
	
	@Transactional(readOnly = true)
	public List<InvoicePayment> findByInvoice(long invoiceId) {
		return this.paymentRepository.findByInvoiceId(Long.valueOf(invoiceId));
	}
	
	@Transactional(readOnly = true)
	public Double sumPayments(long invoiceId) {
		return this.paymentRepository.sumPayments(Long.valueOf(invoiceId));
	}
	
	public InvoicePaymentVO getPaymentVO(InvoicePayment payment) {
		InvoicePaymentVO paymentVO = new InvoicePaymentVO();
		paymentVO.toVO(payment);
		return paymentVO;
	}
	
}
