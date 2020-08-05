package common.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//interceptor
/* DispatcherServlet이 컨트롤러를 호출하기 전에 요청을 가로채서 원하는 작업을 진행할 수 있다.
* 순서) ServletContainer -> filter(서블릿 호춣하기 전에 요청을 가로챌 수 있음) -> servlet 
* 		-> interceptor(서블릿이 컨트롤러를 호출하기 전에 요청을 가로챔) -> controller
* filter / interceptor 의 차이점 : 호출 시점
* 이벤트 발생 시 Listener가 호출
*/

public class AuthInterceptor implements HandlerInterceptor{

	private Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
	
	// Controller가 수행되기 전
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// return타입이 boolean
		/* true 반환 시 : 컨트롤러 정상적으로 호출
		 * false 반환 시 : 컨트롤러를 호출하지 않음
		 */
	
		logger.info("[interceptor] : preHandle");
		HttpSession session = request.getSession();
		// 도서관련 요청 죽, URL에 book 메소드가 들어있을 때, 로그인 처리가 안되어있을경우  돌려보내
		if(request.getRequestURI().contains("book/") && session.getAttribute("loginInfo") == null ) {
			request.setAttribute("alertMsg", "로그인 후 사용 가능한 기능입니다.");
			request.setAttribute("back", "back");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/result.jsp");
			rd.forward(request, response);
		}
		return true;
	}

	// Controller가 수행되고 View를 호출 하기 전
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		// HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	// View 호출이 완료 된 후
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
	
	

}
