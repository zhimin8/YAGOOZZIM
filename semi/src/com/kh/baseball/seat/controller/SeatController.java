package com.kh.baseball.seat.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.kh.common.frontcontroller.Controller;
import com.kh.common.frontcontroller.ModelAndView;

public class SeatController implements Controller {
	
	public ModelAndView seat(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setView("seat/seat");
		return  mav;
	}

	public ModelAndView seatSelect(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();
		Gson gson = new Gson();

		JsonObject jObj;
		List<String> fileList = new ArrayList<String>();
		List<String> blockList = new ArrayList<>();
		String std = "";
		String str = "";
		int idx = 0;
		
		try {
			jObj = gson.fromJson(
					new FileReader(new File(
							request.getSession().getServletContext().getRealPath("/") + "resources/baseball.json")),
					JsonObject.class);
			JsonArray jsonArray = jObj.getAsJsonArray("DATA");
			for (JsonElement je : jsonArray) {
				// 잠실
				if (request.getParameter("stadium").toLowerCase().equals("jamsil")) {
					std = "jamsil";
					if (je.getAsJsonObject().get("place").getAsString().equals("j")) {
						//////////////////////잠실 - 홈////////////////////////
						if (request.getParameter("homeoraway").equals("home")
								& request.getParameter("selectCondition").equals("cheer")) {
							if (je.getAsJsonObject().get("scene").getAsString().equals("home_cheer")) {
								fileList.add(je.getAsJsonObject().get("image").getAsString());
							}
						} else if (request.getParameter("homeoraway").equals("home")
								& request.getParameter("selectCondition").equals("infield")) {
							if (je.getAsJsonObject().get("scene").getAsString().equals("home_infield")) {
								fileList.add(je.getAsJsonObject().get("image").getAsString());
							}
						} else if (request.getParameter("homeoraway").equals("home")
								& request.getParameter("selectCondition").equals("player")) {
							if (je.getAsJsonObject().get("scene").getAsString().equals("home_player")) {
								fileList.add(je.getAsJsonObject().get("image").getAsString());
							}
						} else if (request.getParameter("homeoraway").equals("home")
								& request.getParameter("selectCondition").equals("table")) {
							if (je.getAsJsonObject().get("scene").getAsString().equals("home_table")) {
								fileList.add(je.getAsJsonObject().get("image").getAsString());
							}
						} else if (request.getParameter("homeoraway").equals("home")
								& request.getParameter("selectCondition").equals("outfield")) {
							if (je.getAsJsonObject().get("scene").getAsString().equals("home_outfield")) {
								fileList.add(je.getAsJsonObject().get("image").getAsString());
							}
						} else if (request.getParameter("homeoraway").equals("home")
								& request.getParameter("selectCondition").equals("up4")) {
							if (je.getAsJsonObject().get("scene").getAsString().equals("home_up4")) {
								fileList.add(je.getAsJsonObject().get("image").getAsString());
							}
						} else if (request.getParameter("homeoraway").equals("home")
								& request.getParameter("selectCondition").equals("down4")) {
							if (je.getAsJsonObject().get("scene").getAsString().equals("home_down4")) {
								fileList.add(je.getAsJsonObject().get("image").getAsString());
							}
						} 
						//////////////////////잠실 - 어웨이////////////////////////
						else if (request.getParameter("homeoraway").equals("away")
								& request.getParameter("selectCondition").equals("cheer")) {
							if (je.getAsJsonObject().get("scene").getAsString().equals("away_cheer")) {
								fileList.add(je.getAsJsonObject().get("image").getAsString());
							}
						} else if (request.getParameter("homeoraway").equals("away")
								& request.getParameter("selectCondition").equals("infield")) {
							if (je.getAsJsonObject().get("scene").getAsString().equals("away_infield")) {
								fileList.add(je.getAsJsonObject().get("image").getAsString());
							}
						} else if (request.getParameter("homeoraway").equals("away")
								& request.getParameter("selectCondition").equals("player")) {
							if (je.getAsJsonObject().get("scene").getAsString().equals("away_player")) {
								fileList.add(je.getAsJsonObject().get("image").getAsString());
							}
						} else if (request.getParameter("homeoraway").equals("away")
								& request.getParameter("selectCondition").equals("table")) {
							if (je.getAsJsonObject().get("scene").getAsString().equals("away_table")) {
								fileList.add(je.getAsJsonObject().get("image").getAsString());
							}
						} else if (request.getParameter("homeoraway").equals("away")
								& request.getParameter("selectCondition").equals("outfield")) {
							if (je.getAsJsonObject().get("scene").getAsString().equals("away_outfield")) {
								fileList.add(je.getAsJsonObject().get("image").getAsString());
							}
						} else if (request.getParameter("homeoraway").equals("away")
								& request.getParameter("selectCondition").equals("up4")) {
							if (je.getAsJsonObject().get("scene").getAsString().equals("away_up4")) {
								fileList.add(je.getAsJsonObject().get("image").getAsString());
							}
						} else if (request.getParameter("homeoraway").equals("away")
								& request.getParameter("selectCondition").equals("down4")) {
							if (je.getAsJsonObject().get("scene").getAsString().equals("away_down4")) {
								fileList.add(je.getAsJsonObject().get("image").getAsString());
							}
						} 
					}
				} else if(request.getParameter("stadium").equals("munhak")) {
					std = "sk";
					if (je.getAsJsonObject().get("place").getAsString().equals("s")) {
						//////////////////////문학 - 홈////////////////////////
						if (request.getParameter("homeoraway").equals("home")
								& request.getParameter("selectCondition").equals("cheer")) {
							if (je.getAsJsonObject().get("scene").getAsString().equals("home_cheer")) {
								fileList.add(je.getAsJsonObject().get("image").getAsString());
							}
						} else if (request.getParameter("homeoraway").equals("home")
								& request.getParameter("selectCondition").equals("infield")) {
							if (je.getAsJsonObject().get("scene").getAsString().equals("home_infield")) {
								fileList.add(je.getAsJsonObject().get("image").getAsString());
							}
						} else if (request.getParameter("homeoraway").equals("home")
								& request.getParameter("selectCondition").equals("player")) {
							if (je.getAsJsonObject().get("scene").getAsString().equals("home_player")) {
								fileList.add(je.getAsJsonObject().get("image").getAsString());
							}
						} else if (request.getParameter("homeoraway").equals("home")
								& request.getParameter("selectCondition").equals("table")) {
							if (je.getAsJsonObject().get("scene").getAsString().equals("home_table")) {
								fileList.add(je.getAsJsonObject().get("image").getAsString());
							}
						} else if (request.getParameter("homeoraway").equals("home")
								& request.getParameter("selectCondition").equals("outfield")) {
							if (je.getAsJsonObject().get("scene").getAsString().equals("home_outfield")) {
								fileList.add(je.getAsJsonObject().get("image").getAsString());
							}
						} else if (request.getParameter("homeoraway").equals("home")
								& request.getParameter("selectCondition").equals("up4")) {
							if (je.getAsJsonObject().get("scene").getAsString().equals("home_up4")) {
								fileList.add(je.getAsJsonObject().get("image").getAsString());
							}
						} else if (request.getParameter("homeoraway").equals("home")
								& request.getParameter("selectCondition").equals("down4")) {
							if (je.getAsJsonObject().get("scene").getAsString().equals("home_down4")) {
								fileList.add(je.getAsJsonObject().get("image").getAsString());
							}
						} 
						//////////////////////문학 - 어웨이////////////////////////
						else if (request.getParameter("homeoraway").equals("away")
								& request.getParameter("selectCondition").equals("cheer")) {
							if (je.getAsJsonObject().get("scene").getAsString().equals("away_cheer")) {
								fileList.add(je.getAsJsonObject().get("image").getAsString());
							}
						} else if (request.getParameter("homeoraway").equals("away")
								& request.getParameter("selectCondition").equals("infield")) {
							if (je.getAsJsonObject().get("scene").getAsString().equals("away_infield")) {
								fileList.add(je.getAsJsonObject().get("image").getAsString());
							}
						} else if (request.getParameter("homeoraway").equals("away")
								& request.getParameter("selectCondition").equals("player")) {
							if (je.getAsJsonObject().get("scene").getAsString().equals("away_player")) {
								fileList.add(je.getAsJsonObject().get("image").getAsString());
							}
						} else if (request.getParameter("homeoraway").equals("away")
								& request.getParameter("selectCondition").equals("table")) {
							if (je.getAsJsonObject().get("scene").getAsString().equals("away_table")) {
								fileList.add(je.getAsJsonObject().get("image").getAsString());
							}
						} else if (request.getParameter("homeoraway").equals("away")
								& request.getParameter("selectCondition").equals("outfield")) {
							if (je.getAsJsonObject().get("scene").getAsString().equals("away_outfield")) {
								fileList.add(je.getAsJsonObject().get("image").getAsString());
							}
						} else if (request.getParameter("homeoraway").equals("away")
								& request.getParameter("selectCondition").equals("up4")) {
							if (je.getAsJsonObject().get("scene").getAsString().equals("away_up4")) {
								fileList.add(je.getAsJsonObject().get("image").getAsString());
							}
						} else if (request.getParameter("homeoraway").equals("away")
								& request.getParameter("selectCondition").equals("down4")) {
							if (je.getAsJsonObject().get("scene").getAsString().equals("away_down4")) {
								fileList.add(je.getAsJsonObject().get("image").getAsString());
							}
						} 
					}
				}

			}

		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			e.printStackTrace();
		}
		
		/*
		 * for(int i=0; i<fileList.size(); i++) { str = fileList.get(i).substring(2);
		 * int strIdx = str.indexOf("_"); str = str.substring(0, strIdx);
		 * blockList.add(str); idx++; }
		 */
		
		//request.setAttribute("blockList", blockList);
		mav.addObject("stadium", std);
		mav.addObject("fileList", fileList);
		//mav.addObject("blockList", blockList);
		//mav.addObject("idx", idx);
		mav.setView("seat/seatResult");

		return mav;
	}

}
