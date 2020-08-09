package com.dg.drimansy.view.vo;

import java.util.ArrayList;
import java.util.List;

import com.dg.drimansy.model.DailyOut;
import com.dg.drimansy.utils.DateUtils;

import lombok.Data;

@Data
public class DailyOutVO {
	private Long id;
	private List<DailyOutDetailVO> details = new ArrayList<DailyOutDetailVO>(); 
	private String dateStr;
	private Double totalOut;
	private CarVO car;
	private String dateOutStr;
	private String arriveDateStr;
	private Double arriveTotal;
	private UserVO createdBy;
	private CollaboratorVO responsible;
	private UserVO receivedBy;
	private String comment;
	
	public void toVO(DailyOut dailyOut) {
		setId(dailyOut.getId());
		setDateStr(DateUtils.getShortFWebOrNow(dailyOut.getDate()));
		setTotalOut(dailyOut.getTotalOut());
		CarVO carVO = new CarVO();
		carVO.toVO(dailyOut.getCar());
		setCar(carVO);
		setDateOutStr(DateUtils.getFWebOrNow(dailyOut.getDateOut()));
		if(dailyOut.getArriveDate() != null) {
			setArriveDateStr(DateUtils.getFWebOrNow(dailyOut.getArriveDate()));
		}
		setArriveTotal(dailyOut.getArriveTotal());
		UserVO createdBy = new UserVO();
		createdBy.toVO(dailyOut.getCreatedBy());
		setCreatedBy(createdBy);
		CollaboratorVO responsible = new CollaboratorVO();
		responsible.toVO(dailyOut.getResponsible());
		setResponsible(responsible);
		UserVO receivedBy = new UserVO();
		receivedBy.toVO(dailyOut.getReceivedBy());
		setReceivedBy(receivedBy);
		setComment(dailyOut.getComment());
	}
	
	public String getSign() {
		return getDateStr() + " - " + getCar().getPlate();
	}
}
