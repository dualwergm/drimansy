package com.dg.drimansy.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	public static JsonNode getJNodeParams(String jparams) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readTree(jparams);
		} catch (JsonMappingException e) {//
		} catch (JsonProcessingException e) {//	
		}
		return null;
	}
	
	public static List<Long> stringToLongList(String ids){
		Pattern pattern = Pattern.compile(",");
		return pattern.splitAsStream(ids)
		                            .map(Long::valueOf)
		                            .collect(Collectors.toList());
	}
	
	public static List<Integer> stringToIntegerList(String amounts){
		Pattern pattern = Pattern.compile(",");
		return pattern.splitAsStream(amounts)
		                            .map(Integer::valueOf)
		                            .collect(Collectors.toList());
	}
	
	public static String decode(String value) throws UnsupportedEncodingException {
	    return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
	}
	
	public static boolean isEmpty(String s) {
		return s == null || s.length() == 0;
	}
	
	public static boolean gtZero(Long l) {
		return l != null && l.longValue() > 0;
	}
}
