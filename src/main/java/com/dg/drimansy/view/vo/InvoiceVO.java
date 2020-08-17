package com.dg.drimansy.view.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dg.drimansy.model.Invoice;
import com.dg.drimansy.utils.DateUtils;

import lombok.Data;

@Data
public class InvoiceVO {
private Long id;
	private DailyOutVO dailyOut;
	private Date date;
	private String dateStr;
	private CollaboratorVO responsible;
	private ClientVO client;	
	private String comment;
	private Double total;
	private Double paymentTotal;
	private UserVO createdBy;
	private List<InvoiceDetailVO> details = new ArrayList<>();
	
	public void toVO(Invoice invoice) {
		setId(invoice.getId());
		setDateStr(DateUtils.getShortFWebOrNow(invoice.getDate()));
		if(invoice.getDailyOut()!=null) {
			DailyOutVO outVO = new DailyOutVO();
			outVO.toVO(invoice.getDailyOut());
			setDailyOut(outVO);
		}
		CollaboratorVO responsible = new CollaboratorVO();
		if(invoice.getResponsible() != null) {
			responsible.toVO(invoice.getResponsible());
		}
		setResponsible(responsible);
		setTotal(invoice.getTotal());
		ClientVO clientVO = new ClientVO();
		if(invoice.getClient() != null) {
			clientVO.toVO(invoice.getClient());
		}
		setClient(clientVO);
		setComment(invoice.getComment());
		setTotal(invoice.getTotal());
	}
	
	public Long getDailyId() {
		return getDailyOut() == null ? 0l : getDailyOut().getId();
	}
	
	public double getToPay() {
		return getTotal() - getPaymentTotal();
	}
}
