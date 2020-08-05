package com.kh.common.frontcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HandlerMapping hm = new HandlerMapping();
	private HandlerAdapter ha = new HandlerAdapter();

	public DispatcherServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String[] uriArr = request.getRequestURI().split("/");

		System.out.println("어디를 타고 있나요?" + Arrays.toString(uriArr));

		Controller ctr = hm.getController(uriArr);
		String methodName = hm.getMethod(uriArr);

		ModelAndView mav = ha.excute(ctr, methodName, request);

		ViewResolver vr = new ViewResolver(mav.getView());
		
		

		if (mav.getView().equals("ajax")) {

			PrintWriter pw = response.getWriter();
			String res = "";
			if (mav.getData().containsKey("id")) {
				res = (String) mav.getData().get("id");
			} else if (mav.getData().containsKey("userId")) {
				res = (String) mav.getData().get("userId");

			}

			pw.write(res);

		} else if (mav.getView().equals("file")) {
		} else {

			request.setAttribute("data", mav.getData());

			vr = new ViewResolver(mav.getView());

			RequestDispatcher rd = request.getRequestDispatcher(vr.getView());
			rd.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
