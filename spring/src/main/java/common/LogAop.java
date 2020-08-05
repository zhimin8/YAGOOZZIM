package common;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogAop {

   public void before(JoinPoint join) {
      // JoinPoint에서는 타객객체의 객체정보와 조인포인트에 해다아는 메서드 정보를 들고옴
      // 그래서 무슨 객체의 어떤 메서드가 실행될건지를 알 수 있다.

      Logger logger = LoggerFactory.getLogger(join.getTarget() + "");
      logger.info("------------------- LogAop Start -----------------");
      Object[] args = join.getArgs();
      // 매개변수들을 가져올 수 있어..

      if (args != null) {
         logger.info("method : " + join.getSignature().getName());
         // 메서드 이름찍기
         for (int i = 0; i < args.length; i++) {

            logger.info(i + "번째 : " + args[i]);

         }
      }

   }

   public void after(JoinPoint join) {

      Logger logger = LoggerFactory.getLogger(join.getTarget() + "");
      logger.info("-------------------- LogAop END -------------------");

   }

   public void afterThrowing(JoinPoint join) {

      Logger logger = LoggerFactory.getLogger(join.getTarget() + "");
      logger.info("-------------------- err log ---------------------");
      logger.info("ERROR : " + join.getArgs());
      logger.info("ERROR : " + join.toString());
   }
}