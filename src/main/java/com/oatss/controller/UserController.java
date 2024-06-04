package com.oatss.controller;
import com.oatss.entity.Orders;
import com.oatss.entity.User;
import com.oatss.service.OrdersService;
import com.oatss.service.TicketService;
import com.oatss.service.UserService;
import com.oatss.utils.ValidUtils;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class UserController {
    @Resource
    private TicketService ticketService;
    @Resource
    private OrdersService ordersService;
    @Resource
    private UserService userService;
    @RequestMapping(
            value = "/user/select-ticket",
            method = RequestMethod.GET
    )
    public String selectTicket(
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model
    ) {
        if (ValidUtils.isValidText(keyword) == true) {
            model.addAttribute("ticketList", ticketService.selectCurrentTicketByKeyword('%' + keyword.trim() + '%'));
        }
        else {
            model.addAttribute("ticketList", ticketService.selectCurrentTicket());
        }
        return "/user/select-ticket";
    }
    @RequestMapping(
            value = "/user/my-ticket",
            method = RequestMethod.GET
    )
    public String myTicket(
            @RequestParam(value = "keyword", required = false) String keyword,
            HttpSession session,
            Model model
    ) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        if (ValidUtils.isValidText(keyword) == true) {
            model.addAttribute("ticketList", ticketService.selectBuyTicketByUserIdByKeyword(user.getId(), '%' + keyword.trim() + '%'));
        }
        else {
            model.addAttribute("ticketList", ticketService.selectBuyTicketByUserId(user.getId()));
        }
        return "/user/my-ticket";
    }
    @RequestMapping(
            value = "/user/buy-ticket",
            method = RequestMethod.GET
    )
    public String buyTicket(
            @RequestParam("id") Integer ticketId,
            HttpSession session
    ) {
        User user = (User) session.getAttribute("user");
        Orders orders = new Orders();
        orders.setTicketId(ticketId);
        orders.setUserId(user.getId());
        orders.setTime(Timestamp.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern(ValidUtils.DATA_TIME_PATTERN1))));
        ordersService.insertOrders(orders);
        return "redirect:/user/my-ticket";
    }
    @RequestMapping(
            value = "/user/refund-ticket",
            method = RequestMethod.GET
    )
    public String refundTicket(@RequestParam("id") Integer id) {
        ordersService.deleteOrdersByTicketId(id);
        return "redirect:/user/my-ticket";
    }
    @RequestMapping(
            value = "/user/my-user",
            method = RequestMethod.GET
    )
    public String myUser(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "/user/my-user";
    }
    @RequestMapping(
            value = "/user/update-user",
            method = RequestMethod.POST
    )
    public String updateUser(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "idCard", required = false) String idCard,
            @RequestParam(value = "telephone", required = false) String telephone
    ) {
        User user = userService.selectUserById(id);
        if (ValidUtils.isValidText(name) == true) {
            user.setName(name);
        }
        if (ValidUtils.isValidText(idCard) == true) {
            user.setIdCard(idCard);
        }
        if (ValidUtils.isValidText(telephone) == true) {
            user.setTelephone(telephone);
        }
        userService.updateUserById(user);
        return "redirect:/user/my-user";
    }
}