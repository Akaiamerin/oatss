package com.oatss.service;
import com.oatss.entity.Ticket;
import java.util.List;
public interface TicketService {
    int insertTicket(Ticket ticket);
    int deleteTicketById(Integer id);
    int updateTicketById(Ticket ticket);
    Ticket selectTicketById(Integer id);
    List<Ticket> selectAllTicket();
    List<Ticket> selectTicketByKeyword(String keyword);
    List<Ticket> selectCurrentTicket();
    List<Ticket> selectCurrentTicketByKeyword(String keyword);
    List<Ticket> selectBuyTicketByUserId(Integer userId);
    List<Ticket> selectBuyTicketByUserIdByKeyword(Integer userId, String keyword);
    void truncateTicket();
}