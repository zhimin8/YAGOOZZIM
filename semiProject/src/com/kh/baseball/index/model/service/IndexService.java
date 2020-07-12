package com.kh.baseball.index.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kh.baseball.index.model.dao.IndexDao;
import com.kh.baseball.index.model.vo.PlayData;
import com.kh.common.JDBCTemplate;

public class IndexService {

	JDBCTemplate jdt = JDBCTemplate.getInstance();
	IndexDao id = new IndexDao();

	public int insertService(List<Map<String, String>> datalist) {
		int res = 0;
		
		Connection conn = jdt.getConnection();
		System.out.println("컨트롤러에게 값을 받아온 서비스입니다." + datalist);
		try {
			res = id.insertData(conn, datalist);
			System.out.println("컨트롤러에게 넘기기 직전인 서비스 입니다.");
			jdt.commit(conn);

		} catch (SQLException e) {
			e.printStackTrace();
			jdt.rollback(conn);
		} finally {
			jdt.close(conn);
		}

		return res;
	}

	public List<PlayData> selectData(String sysdate, String tomorrow, String aftertomorrow) {
		List<PlayData> res = new ArrayList<>();
		
		Connection conn = jdt.getConnection();

		try {
			res = id.selectData(conn, sysdate, tomorrow, aftertomorrow);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdt.close(conn);
		}
		return res;

	}

}
