package com.dg.drimansy.view.vo;

import java.util.Date;

import com.dg.drimansy.model.InvoicePayment;
import com.dg.drimansy.utils.DateUtils;

import lombok.Data;

@Data
public class InvoicePaymentVO {
	private Long id;
	private Date date;
	private Double value;	
	private CollaboratorVO received;
	private String dateStr;
	private InvoiceVO invoice;
	
	public void toVO(InvoicePayment payment) {
		setId(payment.getId());
		InvoiceVO invoiceVO = new InvoiceVO();
		if(payment.getInvoice()!=null) {
			invoiceVO.toVO(payment.getInvoice());
		}
		setInvoice(invoiceVO);
		setDateStr(DateUtils.getShortFWebOrNow(payment.getDate()));
		setValue(payment.getValue());
		CollaboratorVO responsible = new CollaboratorVO();
		if(payment.getReceived() != null) {
			responsible.toVO(payment.getReceived());
		}
		setReceived(responsible);
	}
}
