package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Role;
import web.model.User;
import web.service.usersservice.UsersService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {
    @Autowired
    UsersService usersService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String printIndexPage(Model model) {
        List<User> userList = usersService.getAllUsers();
        model.addAttribute("userList", userList);
        return "admin";
    }

    @RequestMapping(value = "/admin/insert", method = RequestMethod.POST)
    public String insertUser(@RequestParam String name, String password, int age, String role, HttpServletRequest request) {
        String[] rolearray = role.split(",");
        if (rolearray.length == 2) {
            Role role1 = usersService.getRole(rolearray[0]);
            Role role2 = usersService.getRole(rolearray[1]);
            User user = new User.Builder()
                    .withName(name)
                    .withPassword(password)
                    .withAge(age)
                    .withRole(role1)
                    .withRole(role2)
                    .build();
            role1.setUser(user);
            role2.setUser(user);
            usersService.insertUser(user);
            String referer = request.getHeader("Referer");
            return "redirect:" + referer;
        } else {
            Role role3 = usersService.getRole(role);
            User user = new User.Builder()
                    .withName(name)
                    .withPassword(password)
                    .withAge(age)
                    .withRole(role3)
                    .build();
            role3.setUser(user);
            usersService.insertUser(user);
            String referer = request.getHeader("Referer");
            return "redirect:" + referer;
        }
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
    String deleteUsers(@RequestParam int idDelete, HttpServletRequest request) {
        usersService.deleteUser(idDelete);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @RequestMapping(value = "/admin/update", method = RequestMethod.GET)
    String updateUser(Model model, @RequestParam int idUpdate) {
        User user = usersService.getUserByID(idUpdate);
        List<Role> roleList = new ArrayList<>();
        for (Role role : user.getRoleSet()) {
            roleList.add(role);
        }
        model.addAttribute("user", user);
        model.addAttribute("roleList", roleList);
        return "update";
    }

    @RequestMapping(value = "/admin/update", method = RequestMethod.POST)
    String updateUserPost(@RequestParam int id, String name, String password, String role, int age, HttpServletRequest request) {
        String[] role_array = role.split(",");
        if (role_array.length == 2) {
            Role role1 = usersService.getRole(role_array[0]);
            Role role2 = usersService.getRole(role_array[1]);
            User user = usersService.getUserByID(id);
            Set<Role> newroleSet = new HashSet<>();
            newroleSet.add(role1);
            newroleSet.add(role2);
            user.setRoleSet(newroleSet);
            if (!role1.getUserList().contains(user)) {
                role1.setUser(user);
            }
            if (!role2.getUserList().contains(user)) {
                role2.setUser(user);
            }
            user.setName(name);
            user.setPassword(password);
            user.setAge(age);
            usersService.updateUser(user);
            return "redirect:/admin";
        } else {
            Role role23 = usersService.getRole(role);
            User user = usersService.getUserByID(id);
            Set<Role> newroleSet = new HashSet<>();
            newroleSet.add(role23);
            user.setRoleSet(newroleSet);
            if (role23.getUserList().contains(user)) {
                role23.setUser(user);
            }
            user.setName(name);
            user.setPassword(password);
            user.setAge(age);
            usersService.updateUser(user);
            return "redirect:/admin";
        }
    }
}
