package com.kh.baseball.index.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baseball.index.model.service.IndexService;
import com.kh.baseball.index.model.vo.PlayData;

@Controller
public class IndexController {
	
	@Autowired
	public IndexService iService;
	
	@RequestMapping(value= "/", method = RequestMethod.GET )
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		
		//오늘/내일/모레 날짜 넣어주기
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MM.dd");
		String sysdate = sdf.format(cal.getTime());
		cal.add(cal.DATE, 1);
		String tomorrow = sdf.format(cal.getTime());
		cal.add(cal.DATE, 1);
		String aftertomorrow = sdf.format(cal.getTime());
		/*
		 * // 날짜데이터 잘 들어갔나 확인 
		 * System.out.println("컨트롤러_index() = " + sysdate);
		 * System.out.println("컨트롤러_index() = "+ tomorrow);
		 * System.out.println("컨트롤러_index() = " + aftertomorrow);
		 * 
		 * List<String> dates = new ArrayList<>(); dates.add(sysdate);
		 * dates.add(tomorrow); dates.add(aftertomorrow);
		 */
		
		String[] dateList = { sysdate, tomorrow, aftertomorrow };
		List<PlayData> res = iService.selectData(dateList);
		
		mav.addObject("dataList", res);
		mav.setViewName("index/index");
		return mav;
	}
	
	public ModelAndView insertData() throws SQLException {
		ModelAndView mav = new ModelAndView();
		
		// 크롤링의 결과값을 가져와서 전달
		List<Map<String, String>> res = webCrawling();
		System.out.println("컨트롤러_insertData()" +  res);
	
		int result = iService.insertService(res);
		
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
			Elements day = doc.select("#tblSchedule tr");

			res = new ArrayList<>();
			String matchDay = "";
			for (Element data : day) {

				Map<String, String> mapData = new LinkedHashMap<>();

				if (!(data.select(".day").text().equals(""))) {
					matchDay = data.select(".day").text();
				}

				mapData.put("day", matchDay);
				mapData.put("time", data.select(".time").text());
				mapData.put("play", data.select(".play").text());
				res.add(mapData);
			}
			System.out.println("컨트롤러에서 crawling의 결과값이 잘 담겨있나  : " + res);

		} finally {
			driver.quit();
		}
		return res;
	}

	
	@RequestMapping(value="/index/about.do")
	public ModelAndView aboutUs() throws SQLException {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index/about");
		return mav;
	}

	
	
	

}
