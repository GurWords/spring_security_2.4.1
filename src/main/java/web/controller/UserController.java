package web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.model.Role;
import web.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
	@RequestMapping(value = "user", method = RequestMethod.GET)
	public String printUser(Authentication auth, Model model) {
		User user = (User) auth.getPrincipal();
		for (GrantedAuthority role : auth.getAuthorities()) {
			model.addAttribute("role", role.getAuthority());
		}
		model.addAttribute(user);
		return "user";
	}

}