package com.itnews.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author f.brishtan
 * @since 18.10.18.
 */
//@Component Указывает, что аннотированный класс является «компонентом».
//Такие классы рассматриваются как кандидаты на автоматическое обнаружение
// при использовании конфигурации на основе аннотаций и сканирования пути к классам.
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    //Commences an authentication scheme.
    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");

    }
}
