package com.kh.baseball.index.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.baseball.index.model.vo.PlayData;

@Repository
public class IndexDao {
	
	@Autowired
	SqlSessionTemplate session;
	/* JDBCTemplate jdt = JDBCTemplate.getInstance(); */
	
	public List<PlayData> selectData(String[] dateList) {
		return session.selectList("Index.dates", dateList);
	}
	
	public int insertData(List<Map<String, String>> datalist) {
		System.out.println("dao의 insertData() : " + datalist);
		return session.insert("Index.schedule", datalist);
	}

}
