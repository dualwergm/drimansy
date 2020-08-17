package com.dg.drimansy.model;

import java.text.ParseException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dg.drimansy.utils.DateUtils;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.Data;

@Data
@Entity
@Table(name = "invoicepayment")
public class InvoicePayment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "code_ipm", nullable = false)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_ipm", nullable = false)
	private Date date;
	
	@Column(name = "value_ipm", nullable = false)
	private Double value;	
	
	@ManyToOne
	@JoinColumn(name = "receivedby_ipm", nullable = false)
	private Collaborator received;
	
	@ManyToOne
	@JoinColumn(name = "invoice_ipm")
	private Invoice invoice;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creationdate_ipm")
	private Date creationdate;
	
	@ManyToOne
	@JoinColumn(name = "createdby_ipm", nullable = false)
	private User createdBy;
	
	public void toEntity(JsonNode json) throws ParseException {
		long lId = json.get("id").asLong();
		setId(lId > 0 ? Long.valueOf(lId) : null);
		setValue(json.get("value").asDouble(0));
		setDate(DateUtils.getDateFromWeb(json.get("iDate").asText()));
		setReceived(new Collaborator(json.get("receivedby").asLong()));
		setInvoice(new Invoice(json.get("invoiceId").asLong()));
		long cBy = 0;
		if(json.has("createdBy")) {
			cBy = json.get("createdBy").asLong(0);
		}
		long uId = json.get("userId").asLong();
		setCreatedBy(new User(cBy>0?cBy:uId));
		setCreationdate(new Date());
	}
}
