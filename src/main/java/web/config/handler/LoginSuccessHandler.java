package web.config.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import web.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        List<String> stringList = new ArrayList<>();
        for (GrantedAuthority s: authentication.getAuthorities()){
            stringList.add(s.getAuthority());
        }
        if (stringList.contains("ROLE_ADMIN")){
            httpServletResponse.sendRedirect("/admin");
        }else {
            httpServletResponse.sendRedirect("/user");
        }

        //httpServletResponse.sendRedirect("/admin");
    }
}