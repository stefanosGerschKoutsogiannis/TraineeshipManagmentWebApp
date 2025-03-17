package com.stefanosgersch.traineeship.security;

import com.stefanosgersch.traineeship.domain.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
public class CustomSecuritySuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    protected void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws java.io.IOException {
        String targetUrl = determineTargetUrl(authentication);
        if(response.isCommitted()) return;
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private String determineTargetUrl(Authentication authentication) {
        String url = "/login?error=true";
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<>();

        for(GrantedAuthority a : authorities){
            roles.add(a.getAuthority());
        }

        url = returnTargetUrl(roles);
        return url;
    }

    private String returnTargetUrl(List<String> roles) {
        if (roles.contains(Role.STUDENT.toString())) {
            return "/student/dashboard";
        } else if (roles.contains(Role.COMPANY.toString())) {
            return "/company/dashboard";
        } else if (roles.contains(Role.PROFESSOR.toString())) {
            return "/professor/dashboard";
        } else if (roles.contains(Role.COMMITTEE_MEMBER.toString())) {
            return "/committee/dashboard";
        } else {
            return "/login?error=true";
        }
    }

}
