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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
		insertData();
		
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
		
		Map<String, Object> res = iService.selectData(sysdate, tomorrow, aftertomorrow);
		
		mav.addObject("dataList", res);
		mav.setViewName("index/index");
		return mav;
	}
	
	public int insertData() {
		List<Map<String, String>> datalist = webCrawling();
		 
	    System.out.println(datalist);
	    System.out.println("서비스에게 넘기기 직전인 컨트롤러 입니다.");
	    int result = iService.insertData(datalist);
	    System.out.println("서비스에게 값을 받아온 컨트롤러입니다.");
	    
	    if (result >= 1) {
	        System.out.println("db입력 성공");
	    } else {
	        System.out.println("db입력 실패");
	    }
	    
	   return result;
	}
	
	public List<Map<String, String>> webCrawling() {
		  System.out.println("webcrawling 접근");
	      System.setProperty("webdriver.chrome.driver","/chromedriver.exe");
	      Document doc;
	      System.out.println("Document 접근");   
	      ChromeOptions options = new ChromeOptions();
	      options.addArguments("--start-maximized"); // 전체화면으로 실행
	      options.addArguments("--disable-popup-blocking"); // 팝업 무시
	      options.addArguments("--disable-default-apps");

	      List<Map<String, String>> res = null;
	      // Webdriver 객체생성
	      ChromeDriver driver = new ChromeDriver(options);
	      System.out.println("WebDriver 접근");

	      try {
	         
	         WebDriverWait wait = new WebDriverWait(driver, 1000);

	         driver.get("https://www.koreabaseball.com/Schedule/Schedule.aspx");
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
