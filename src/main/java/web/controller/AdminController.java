package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Role;
import web.model.User;
import web.service.UsersService;

@Controller
public class AdminController {
    @Autowired
    UsersService usersService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String printIndexPage() {
        return "admin";
    }

    @RequestMapping(value = "/admin/insert", method = RequestMethod.POST)
    public String insertUser(@RequestParam String name, String password, int age, String role) {
        Role roleObject = new Role.Builder().withRole(role).build();
        User user = new User.Builder()
                .withName(name)
                .withPassword(password)
                .withAge(age)
                .withRole(roleObject)
                .build();
        usersService.insertUser(user);
        return "admin";
    }
}
