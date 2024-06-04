package com.oatss.controller;
import com.oatss.entity.User;
import com.oatss.service.UserService;
import java.util.Objects;
import javax.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class AuthController {
    @Resource
    private UserService userService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @RequestMapping(
            value = {"/", "/auth/login"},
            method = RequestMethod.GET
    )
    public String login() {
        return "/login";
    }
    @RequestMapping(
            value = "/auth/register",
            method = RequestMethod.GET
    )
    public String register() {
        return "/register";
    }
    @RequestMapping(
            value = "/auth/register",
            method = RequestMethod.POST
    )
    public String register(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("idCard") String idCard,
            @RequestParam("telephone") String telephone
    ) {
        if (userService.selectUserByUsername(username) != null) {
            return "/register";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setName(name);
        user.setIdCard(idCard);
        user.setTelephone(telephone);
        user.setRole("user");
        userService.insertUser(user);
        return "redirect:/auth/login";
    }
    @RequestMapping(
            value = "/auth/forget",
            method = RequestMethod.GET
    )
    public String forget() {
        return "/forget";
    }
    @RequestMapping(
            value = "/auth/forget",
            method = RequestMethod.POST
    )
    public String forget(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("idCard") String idCard,
            @RequestParam("telephone") String telephone
    ) {
        User user = userService.selectUserByUsername(username);
        if (user == null || Objects.equals(name, user.getName()) == false || Objects.equals(idCard, user.getIdCard()) == false || Objects.equals(telephone, user.getTelephone()) == false) {
            return "/forget";
        }
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setName(name);
        user.setIdCard(idCard);
        user.setTelephone(telephone);
        userService.updateUserById(user);
        return "redirect:/auth/login";
    }
}