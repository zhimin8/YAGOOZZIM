package com.kh.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DataSetting extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DataSetting() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		// 클라이언트가 요청 보낸 uri를 저장
		String conPath = request.getContextPath();
		// 컨택스트 패스를 저장

		String command = uri.substring(conPath.length());
		System.out.println(command);
		if (command.equals("/data/json")) {
//			addJsonData(request, response);
		} else if (command.equals("/data/webCrawling")) {
			webCrawling();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void webCrawling() {
		Document doc;
		
		WebDriver driver = new ChromeDriver();

		try {
			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
			WebDriverWait wait = new WebDriverWait(driver, 1000);

			driver.get("https://www.koreabaseball.com/Schedule/Schedule.aspx");
//	        System.out.println(driver.getPageSource());

			doc = Jsoup.parse(driver.getPageSource());

			/* System.out.println(doc); */
			// 무엇을 갖고 오는지 확인

			Elements day = doc.select("#tblSchedule tr");
			// 우리가 f12를 이용해서 원하는 데이터부분 우클릭하여 카피셀렉터
//	         System.out.println(tblSchedule);

			// Elements time = doc.select(".time");

			// Elements play = doc.select(".play");
			String textdata = "";

			List<Map<String, String>> res = new ArrayList<>();
			String matchDay = ""; 
			for (Element data : day) {

				Map<String, String> mapData = new LinkedHashMap<>();

				if (!(data.select(".day").text().equals(""))) {
					matchDay = data.select(".day").text();
					/*
					 * System.out.println("담김"); System.out.println(matchDay);
					 */
				}

				mapData.put("day", matchDay);
				mapData.put("time", data.select(".time").text());
				mapData.put("play", data.select(".play").text());
				res.add(mapData);

			}

			for (Map<String, String> comeondata : res) {
				System.out.println(comeondata);
				// {day=, time=, play=}s
			}
			
			

		} finally {
			driver.quit();
		}
	}

}
