package com.kh.common.frontcontroller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

public class HandlerAdapter {
	public ModelAndView excute(Controller ctr, String methodName, HttpServletRequest request) {
		ModelAndView mav = null;
		Class c = ctr.getClass();
		try {

			Method exMethod = c.getDeclaredMethod(methodName, HttpServletRequest.class);

			mav = (ModelAndView) exMethod.invoke(ctr, request);

		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return mav;
	}

}
