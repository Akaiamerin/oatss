package com.oatss.controller;
import com.oatss.entity.Ticket;
import com.oatss.entity.User;
import com.oatss.service.OrdersService;
import com.oatss.service.TicketService;
import com.oatss.service.UserService;
import com.oatss.utils.RandomTicket;
import com.oatss.utils.ValidUtils;
import java.sql.Timestamp;
import javax.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class AdminController {
    @Resource
    private TicketService ticketService;
    @Resource
    private OrdersService ordersService;
    @Resource
    private UserService userService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @RequestMapping(
            value = "/admin/insert-ticket",
            method = RequestMethod.GET
    )
    public String insertTicket() {
        return "/admin/insert-ticket";
    }
    @RequestMapping(
            value = "/admin/insert-ticket",
            method = RequestMethod.POST
    )
    public String insertTicket(
            @RequestParam("origin") String origin,
            @RequestParam("destination") String destination,
            @RequestParam("takeoff") String takeoff,
            @RequestParam("land") String land,
            @RequestParam("price") String price
    ) {
        if (ValidUtils.isValidText(origin, destination, takeoff, land, price) == true && (ValidUtils.isValidDateTime(takeoff, ValidUtils.DATA_TIME_PATTERN1) == true || ValidUtils.isValidDateTime(takeoff, ValidUtils.DATA_TIME_PATTERN2) == true) && (ValidUtils.isValidDateTime(takeoff, ValidUtils.DATA_TIME_PATTERN1) == true && ValidUtils.isValidDateTime(takeoff, ValidUtils.DATA_TIME_PATTERN2) == true) && ValidUtils.isValidPrice(price) == true) {
            Ticket ticket = new Ticket();
            ticket.setOrigin(origin);
            ticket.setDestination(destination);
            ticket.setTakeoff(Timestamp.valueOf(takeoff));
            ticket.setLand(Timestamp.valueOf(land));
            ticket.setPrice(Double.valueOf(price));
            ticketService.insertTicket(ticket);
        }
        return "redirect:/admin/select-ticket";
    }
    @RequestMapping(
            value = "/admin/delete-ticket",
            method = RequestMethod.GET
    )
    public String deleteTicket(@RequestParam("id") Integer id) {
        ticketService.deleteTicketById(id);
        return "redirect:/admin/select-ticket";
    }
    @RequestMapping(
            value = "/admin/select-update-ticket",
            method = RequestMethod.GET
    )
    public String updateTicket(
            @RequestParam("id") Integer id,
            Model model
    ) {
        model.addAttribute("ticket", ticketService.selectTicketById(id));
        return "/admin/update-ticket";
    }
    @RequestMapping(
            value = "/admin/update-ticket",
            method = RequestMethod.POST
    )
    public String updateTicket(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "origin", required = false) String origin,
            @RequestParam(value = "destination", required = false) String destination,
            @RequestParam(value = "takeoff", required = false) String takeoff,
            @RequestParam(value = "land", required = false) String land,
            @RequestParam(value = "price", required = false) String price
    ) {
        Ticket ticket = ticketService.selectTicketById(id);
        if (ValidUtils.isValidText(origin) == true) {
            ticket.setOrigin(origin);
        }
        if (ValidUtils.isValidText(destination) == true) {
            ticket.setDestination(destination);
        }
        if (ValidUtils.isValidText(takeoff) == true && (ValidUtils.isValidDateTime(takeoff, ValidUtils.DATA_TIME_PATTERN1) == true || ValidUtils.isValidDateTime(takeoff, ValidUtils.DATA_TIME_PATTERN2) == true)) {
            ticket.setTakeoff(Timestamp.valueOf(takeoff));
        }
        if (ValidUtils.isValidText(land) == true && (ValidUtils.isValidDateTime(land, ValidUtils.DATA_TIME_PATTERN1) == true || ValidUtils.isValidDateTime(land, ValidUtils.DATA_TIME_PATTERN2) == true)) {
            ticket.setLand(Timestamp.valueOf(land));
        }
        if (ValidUtils.isValidText(price) == true && ValidUtils.isValidPrice(price) == true) {
            ticket.setPrice(Double.valueOf(price));
        }
        ticketService.updateTicketById(ticket);
        return "redirect:/admin/select-ticket";
    }
    @RequestMapping(
            value = "/admin/select-ticket",
            method = RequestMethod.GET
    )
    public String selectTicket(
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model
    ) {
        if (ValidUtils.isValidText(keyword) == true) {
            model.addAttribute("ticketList", ticketService.selectTicketByKeyword('%' + keyword.trim() + '%'));
        }
        else {
            model.addAttribute("ticketList", ticketService.selectAllTicket());
        }
        return "/admin/select-ticket";
    }
    @RequestMapping(
            value = "/admin/record",
            method = RequestMethod.GET
    )
    public String record(Model model) {
        model.addAttribute("ordersRecordList", ordersService.selectOrdersRecord());
        return "/admin/record";
    }
    @RequestMapping(
            value = "/admin/insert-user",
            method = RequestMethod.GET
    )
    public String insertUser() {
        return "/admin/insert-user";
    }
    @RequestMapping(
            value = "/admin/insert-user",
            method = RequestMethod.POST
    )
    public String insertUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("idCard") String idCard,
            @RequestParam("telephone") String telephone,
            @RequestParam("role") String role
    ) {
        User user = new User();
        if (ValidUtils.isValidText(username, password, name, idCard, telephone, role) == true) {
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setName(name);
            user.setIdCard(idCard);
            user.setTelephone(telephone);
            user.setRole(role);
        }
        userService.insertUser(user);
        return "redirect:/admin/select-user";
    }
    @RequestMapping(
            value = "/admin/delete-user",
            method = RequestMethod.GET
    )
    public String deleteUser(@RequestParam("id") Integer id) {
        userService.deleteUserById(id);
        return "redirect:/admin/select-user";
    }
    @RequestMapping(
            value = "/admin/select-update-user",
            method = RequestMethod.GET
    )
    public String updateUser(
            @RequestParam("id") Integer id,
            Model model
    ) {
        model.addAttribute("user", userService.selectUserById(id));
        return "/admin/update-user";
    }
    @RequestMapping(
            value = "/admin/update-user",
            method = RequestMethod.POST
    )
    public String updateUser(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "idCard", required = false) String idCard,
            @RequestParam(value = "telephone", required = false) String telephone,
            @RequestParam(value = "role", required = false) String role
    ) {
        User user = userService.selectUserById(id);
        if (ValidUtils.isValidText(password) == true) {
            user.setPassword(passwordEncoder.encode(password));
        }
        if (ValidUtils.isValidText(name) == true) {
            user.setName(name);
        }
        if (ValidUtils.isValidText(idCard) == true) {
            user.setIdCard(idCard);
        }
        if (ValidUtils.isValidText(telephone) == true) {
            user.setTelephone(telephone);
        }
        if (ValidUtils.isValidText(role) == true) {
            user.setRole(role);
        }
        userService.updateUserById(user);
        return "redirect:/admin/select-user";
    }
    @RequestMapping(
            value = "/admin/select-user",
            method = RequestMethod.GET
    )
    public String selectUser(
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model
    ) {
        if (ValidUtils.isValidText(keyword) == true) {
            model.addAttribute("userList", userService.selectUserByKeyword('%' + keyword.trim() + '%'));
        }
        else {
            model.addAttribute("userList", userService.selectAllUser());
        }
        return "/admin/select-user";
    }
    @RequestMapping(
            value = "/admin/random-ticket",
            method = RequestMethod.GET
    )
    public String randomTicket() {
        ticketService.truncateTicket();
        for (int i = 0; i < 10; i++) {
            Ticket ticket = RandomTicket.getRandomTicket();
            ticketService.insertTicket(ticket);
        }
        return "redirect:/admin/select-ticket";
    }
}