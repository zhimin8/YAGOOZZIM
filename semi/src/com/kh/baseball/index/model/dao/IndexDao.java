package com.kh.baseball.index.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kh.baseball.index.model.vo.PlayData;
import com.kh.common.JDBCTemplate;

public class IndexDao {
	JDBCTemplate jdt = JDBCTemplate.getInstance();

	public int insertData(Connection conn, List<Map<String, String>> datalist) throws SQLException {
		int res = 0;
		System.out.println("서비스에게 값을 받아온 다오입니다." + datalist);
		PreparedStatement pstm = null;
		PlayData pd = null;
		String sql = "insert into bb_playdata values(p_no.nextval, ?,?,?)";
		try {
			pstm = conn.prepareStatement(sql);

			for (Map<String, String> data : datalist) {
				pd = new PlayData();
				String day = data.get("day");
				String time = data.get("time");
				String play = data.get("play");

				pstm.setString(1, day);
				pstm.setString(2, time);
				pstm.setString(3, play);
				res = pstm.executeUpdate();
			}
			System.out.println("서비스에게 넘기기 직전인 다오 입니다.");

		} finally {
			jdt.close(pstm);
		}
		return res;

	}

	public List<PlayData> selectData(Connection conn, String sysdate, String tomorrow, String aftertomorrow)
			throws SQLException {
		List<PlayData> res = new ArrayList<>();
		PlayData pd = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from bb_playdata where p_day like '%" + sysdate + "%' or p_day like '%" + tomorrow
				+ "%' or p_day like '%" + aftertomorrow + "%'";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				pd = new PlayData();
				pd.setP_no(rs.getInt(1));
				pd.setP_day(rs.getString(2));
				pd.setP_time(rs.getString(3));
				pd.setP_play(rs.getString(4));

				res.add(pd);
			}
		
		} finally {
			jdt.close(rs, stmt);
		}
		return res;
	}

}
