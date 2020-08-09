package com.dg.drimansy.view.web;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.dg.drimansy.view.vo.ProductVO;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class AjaxResponseBody {

	@JsonView
	HttpStatus status;

	@JsonView
	List<ProductVO> list;
}
