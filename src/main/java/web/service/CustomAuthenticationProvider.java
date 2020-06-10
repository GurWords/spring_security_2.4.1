package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    UsersService service;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String password = (String) authentication.getCredentials();
        String name = (String) authentication.getPrincipal();

        User user = service.loadUserByUsername(name);

        if (user == null){
            throw new BadCredentialsException("Unknown user");
        }
        if (!user.getPassword().equals(password)){
            throw new BadCredentialsException("password bad");
        }
        UserDetails principal = new User.Builder()
                .withName(user.getName())
                .withPassword(user.getPassword())
                .withAge(user.getAge())
                .withId(user.getId())
                .withRole(user.getRole())
                .build();
        return new UsernamePasswordAuthenticationToken(principal,password,principal.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
