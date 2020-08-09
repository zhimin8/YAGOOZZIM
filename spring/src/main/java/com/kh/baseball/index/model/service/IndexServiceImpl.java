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
	
	public Map<String, Object> selectData(String sysdate, String tomorrow, String aftertomorrow) {
		 Map<String, Object> res = new HashMap<>();
	     List<PlayData> plist = iDao.selectData(sysdate, tomorrow, aftertomorrow);
	     res.put("plist", plist);
		return res;
	}

	@Override
	public int insertData(List<Map<String, String>> datalist) {
		return iDao.insertData(datalist);
	}
	
	
	
	
	
	
	
	
	

}
