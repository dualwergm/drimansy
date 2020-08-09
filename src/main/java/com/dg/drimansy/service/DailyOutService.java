package com.dg.drimansy.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dg.drimansy.model.DailyOut;
import com.dg.drimansy.model.DailyOutDetail;
import com.dg.drimansy.model.Product;
import com.dg.drimansy.repository.IDailyOutRepository;
import com.dg.drimansy.view.vo.DailyOutDetailVO;
import com.dg.drimansy.view.vo.DailyOutVO;
import com.dg.drimansy.view.vo.ProductVO;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class DailyOutService {
	private final IDailyOutRepository dailyOutRepository;

	public DailyOutService(IDailyOutRepository dailyOutRepository) {
		this.dailyOutRepository = dailyOutRepository;
	}

	@Transactional(readOnly = true)
	public List<DailyOut> findAllOrdered() {
		return this.dailyOutRepository.findAllOrdered();
	}

	public List<DailyOutVO> getDailyOutsVO() {
		List<DailyOut> findAllOrdered = findAllOrdered();
		return getDailyOutsVO(findAllOrdered);
	}
	
	public List<DailyOutVO> getDailyOutsVO(List<DailyOut> dailyOuts) {
		List<DailyOutVO> list = new ArrayList<DailyOutVO>();
		for (DailyOut dailyOut : dailyOuts) {
			DailyOutVO dailyOutVO = new DailyOutVO();
			dailyOutVO.toVO(dailyOut);
			list.add(dailyOutVO);
		}
		return list;
	}

	@Transactional(readOnly = true)
	public DailyOut findByCarAndDate(Long carId, Date date) {
		if(carId.longValue() == 0 || date == null) {
			return new DailyOut();
		}
		List<DailyOut> dailyOuts = this.dailyOutRepository.findByCarAndDate(carId, date);
		return dailyOuts.isEmpty() ? new DailyOut() : dailyOuts.get(0);
	}

	public DailyOutVO getDailyOut(DailyOut dailyOut, List<Product> products) {
		DailyOutVO dailyOutVO = new DailyOutVO();
		dailyOutVO.toVO(dailyOut);
		List<DailyOutDetail> details = dailyOut.getDetails();
		for (Product product : products) {
			DailyOutDetailVO detailVO = new DailyOutDetailVO();
			DailyOutDetail detail = getDetail(details, product.getId().longValue());
			if (detail != null) {
				detailVO.toVO(detail);
			}else {
				ProductVO pVO = new ProductVO();
				pVO.toVO(product);
				detailVO.setProduct(pVO);
			}
			dailyOutVO.getDetails().add(detailVO);
		}
		return dailyOutVO;
	}

	public DailyOutDetail getDetail(List<DailyOutDetail> details, long productId) {
		for (DailyOutDetail detail : details) {
			if (detail.getProduct().getId().longValue() == productId) {
				return detail;
			}
		}
		return null;
	}
	
	public DailyOut getDetailFromWeb(JsonNode root) throws ParseException {
		JsonNode jMaster = (JsonNode)root.get("jMaster");
		DailyOut out = new DailyOut();
		out.toEntity(jMaster);
		JsonNode jDetails = (JsonNode)root.get("jDetail");
		for(JsonNode jDetail: jDetails) {
			DailyOutDetail detail = new DailyOutDetail();
			detail.toEntity(jDetail);
			detail.setDailyOut(out);
			out.getDetails().add(detail);
		}
		return out;
	}
	
	public void save(DailyOut out) {
		this.dailyOutRepository.saveAndFlush(out);
	}
	
	@Transactional(readOnly = true)
	public DailyOut findById(long dailyId) {
		Optional<DailyOut> findById = this.dailyOutRepository.findById(Long.valueOf(dailyId));
		return findById.isPresent() ? findById.get() : new DailyOut();
	}
}
