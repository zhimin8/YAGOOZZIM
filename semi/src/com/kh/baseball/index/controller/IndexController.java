package com.kh.baseball.index.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kh.baseball.index.model.service.IndexService;
import com.kh.baseball.index.model.vo.PlayData;
import com.kh.common.frontcontroller.Controller;
import com.kh.common.frontcontroller.ModelAndView;

public class IndexController implements Controller {

	IndexService is = new IndexService();
	// handlerMapping에서 index.do -> index로 넘어온 상태

	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MM.dd");
		String sysdate = sdf.format(cal.getTime());
		cal.add(cal.DATE, 1);
		String tomorrow = sdf.format(cal.getTime());
		cal.add(cal.DATE, 1);
		String aftertomorrow = sdf.format(cal.getTime());

		System.out.println(sysdate);
		System.out.println(tomorrow);
		System.out.println(aftertomorrow);

		List<PlayData> res = is.selectData(sysdate, tomorrow, aftertomorrow);
		/* mav.addObject(key, value); */

		System.out.println(res);
		// 여기 직전에 데이터를 담아서 보내자.
		mav.addObject("dataList", res);
		mav.setView("index/index");

		return mav;
	}

	// about.do의 aboutUs 메소드 실행 화면.
	public ModelAndView aboutUs(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setView("index/about");
		return mav;
	}

	public ModelAndView insertData(HttpServletRequest request) {
		System.out.println("왔는가");
		ModelAndView mav = new ModelAndView();
		List<Map<String, String>> res = webCrawling();
		System.out.println(res);
		System.out.println("서비스에게 넘기기 직전인 컨트롤러 입니다.");
		int result = is.insertService(res);
		System.out.println("서비스에게 값을 받아온 컨트롤러입니다.");
		if (result >= 1) {
			System.out.println("db입력 성공");
		} else {
			System.out.println("db입력 실패");
		}

		return mav;

	}

	public List<Map<String, String>> webCrawling() {
		Document doc;
		WebDriver driver = new ChromeDriver();
		List<Map<String, String>> res = null;

		try {
			System.setProperty("webdriver.chrome.driver", "/chromedriver.exe");
			WebDriverWait wait = new WebDriverWait(driver, 1000);

			driver.get("https://www.koreabaseball.com/Schedule/Schedule.aspx");
	        System.out.println(driver.getPageSource());

			doc = Jsoup.parse(driver.getPageSource());

			/* System.out.println(doc); */
			// 무엇을 갖고 오는지 확인

			Elements day = doc.select("#tblSchedule tr");
			// 우리가 f12를 이용해서 원하는 데이터부분 우클릭하여 카피셀렉터
//	         System.out.println(tblSchedule);

			// Elements time = doc.select(".time");

			// Elements play = doc.select(".play");
			res = new ArrayList<>();
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

			/*
			 * for (Map<String, String> comeondata : res) { System.out.println(comeondata);
			 * // {day=, time=, play=}s }
			 */

		} finally {
			driver.quit();
		}
		return res;
	}

}
