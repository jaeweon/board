package com.board.board.aop;

import com.board.board.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
@Order(Ordered.LOWEST_PRECEDENCE)
@Log4j2
public class LoginCheckAspect {
    @Around("@annotation(com.board.board.aop.LoginCheck) && @annotation(loginCheck)")
    public Object adminLoginCheck(ProceedingJoinPoint proceedingJoinPoint, LoginCheck loginCheck) throws Throwable {
        HttpSession session = (HttpSession) ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
        String id = null;

        String userType = loginCheck.type().toString();
        // LoginCheck의 userType에 따라 처리
        switch (loginCheck.type()) {
            case ADMIN -> id = SessionUtil.getLoginAdminId(session);
            case USER -> id = SessionUtil.getLoginMemberId(session);
        }

        if (id == null) {
            log.debug("Unauthorized access attempt: {}", proceedingJoinPoint.toShortString());
            throw new HttpStatusCodeException(HttpStatus.UNAUTHORIZED, "로그인한 ID 값을 확인해주세요.") {};
        }

        return proceedingJoinPoint.proceed();
    }
}
