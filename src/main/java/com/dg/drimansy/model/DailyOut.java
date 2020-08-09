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
@Table(name = "dailyout")
public class DailyOut {
	
	public DailyOut() {}
	public DailyOut(long id) {
		this.id = id > 0 ? Long.valueOf(id) : null;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "code_dot", nullable = false)
	private Long id;
	
	@OneToMany(mappedBy = "dailyOut", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<DailyOutDetail> details = new ArrayList<DailyOutDetail>(); 
	
	@Column(name = "date_dot", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name = "totalout_dot", nullable = false)
	private Double totalOut;
	
	@ManyToOne
	@JoinColumn(name = "car_dot", nullable = false)
	private Car car = new Car();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateout_dot", nullable = false)
	private Date dateOut;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "arrivedate_dot")
	private Date arriveDate;
	
	@Column(name = "arrivetotal_dot")
	private Double arriveTotal;
	
	@ManyToOne
	@JoinColumn(name = "createdby_dot", nullable = false)
	private User createdBy = new User();
	
	@ManyToOne
	@JoinColumn(name = "responsible_dot", nullable = false)
	private Collaborator responsible = new Collaborator();
	
	@ManyToOne
	@JoinColumn(name = "receivedby_dot")
	private User receivedBy = new User();
	
	@Column(name = "comment_dot")
	private String comment;
	
	public void toEntity(JsonNode json) throws ParseException {
		long lId = json.get("id").asLong(0);
		setId(lId > 0 ? Long.valueOf(lId) : null);
		setCar(new Car(json.get("carId").asLong()));
		setDate(DateUtils.getDateFromWeb(json.get("dailyDate").asText()));
		setDateOut(DateUtils.getDateTimeFromWeb(json.get("dateOut").asText()));
		String arriveDateStr = json.get("arriveDate").asText();
		if(!arriveDateStr.isEmpty()) {
			setArriveDate(DateUtils.getDateTimeFromWeb(arriveDateStr));
		}
		setResponsible(new Collaborator(json.get("responsibleId").asLong()));
		setArriveTotal(json.get("arriveTotal").asDouble());
		setTotalOut(json.get("totalOut").asDouble());
		long cBy = json.get("createdBy").asLong();
		long uId = json.get("userId").asLong();
		setCreatedBy(new User(cBy>0?cBy:uId));
		setReceivedBy(null);
		if(lId > 0) {
			setReceivedBy(new User(uId));
		}
	}
}
