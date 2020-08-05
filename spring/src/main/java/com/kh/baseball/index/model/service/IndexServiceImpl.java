package com.kh.baseball.index.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.baseball.index.model.dao.IndexDao;
import com.kh.baseball.index.model.vo.PlayData;

@Service
public class IndexServiceImpl implements IndexService {
	
	@Autowired
	IndexDao iDao;
	
	Connection con = null;
	
	@Autowired
	public IndexServiceImpl() {
		
	}
	
	public List<PlayData> selectData(String[] dateList) {
		return iDao.selectData(dateList);
	}
	
	public int insertService(List<Map<String, String>> datalist) {
		return iDao.insertData(datalist);
	}
	
	
	
	
	
	
	

}
