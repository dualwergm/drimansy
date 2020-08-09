package com.dg.drimansy.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dg.drimansy.utils.DateUtils;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.Data;

@Data
@Entity
@Table(name = "invoice")
public class Invoice {
	
	public Invoice() {}
	
	public Invoice(long id) {
		this.id = id>0?Long.valueOf(id):null;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "code_inv", nullable = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "dayliout_inv")
	private DailyOut dailyOut;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_inv", nullable = false)
	private Date date;
	
	@ManyToOne
	@JoinColumn(name = "responsible_inv", nullable = false)
	private Collaborator responsible;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creationdate_inv")
	private Date creationdate;
	
	@ManyToOne
	@JoinColumn(name = "createdby_inv", nullable = false)
	private User createdBy;
	
	@ManyToOne
	@JoinColumn(name = "client_inv", nullable = false)
	private Client client;	
	
	@Column(name = "comment_inv")
	private String comment;
	
	@Column(name = "total_inv")
	private Double total;
	
	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<InvoiceDetail> details = new ArrayList<>();
	
	public void toEntity(JsonNode json) throws ParseException {
		long iId = json.get("id").asLong(0);
		setId(iId > 0 ? Long.valueOf(iId) : null);
		long dailyId = json.get("dailyId").asLong(0);
		setDailyOut(dailyId > 0 ? new DailyOut(dailyId) : null);
		setDate(DateUtils.getDateFromWeb(json.get("iDate").asText()));
		setResponsible(new Collaborator(json.get("responsibleId").asLong()));
		long cBy = json.get("createdBy").asLong();
		long uId = json.get("userId").asLong();
		setCreatedBy(new User(cBy>0?cBy:uId));
		setClient(new Client(json.get("clientId").asLong()));
		if(json.has("comment")) {
			setComment(json.get("comment").asText(""));
		}
		setTotal(json.get("total").asDouble(0));
		setCreationdate(new Date());
	}
}
