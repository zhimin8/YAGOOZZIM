package com.kh.baseball.index.model.service;

import java.util.List;
import java.util.Map;

import com.kh.baseball.index.model.vo.PlayData;

public interface IndexService {
	
	public List<PlayData> selectData(String[] dateList);
	public int insertService(List<Map<String, String>> datalist);

	
}
