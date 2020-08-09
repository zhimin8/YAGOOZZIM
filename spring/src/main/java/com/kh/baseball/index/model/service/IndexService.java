package com.kh.baseball.index.model.service;

import java.util.List;
import java.util.Map;

import com.kh.baseball.index.model.vo.PlayData;

public interface IndexService {
	
	public Map<String, Object> selectData(String sysdate, String tomorrow, String aftertomorrow);
	public int insertData(List<Map<String, String>> datalist);

	
}
