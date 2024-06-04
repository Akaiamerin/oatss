package com.oatss.service.impl;
import com.oatss.mapper.OrdersMapper;
import com.oatss.mapper.TicketMapper;
import com.oatss.entity.Orders;
import com.oatss.entity.Ticket;
import com.oatss.service.TicketService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
@Service
public class TicketServiceImpl implements TicketService {
    @Resource
    private TicketMapper ticketMapper;
    @Resource
    private OrdersMapper ordersMapper;
    @Override
    public int insertTicket(Ticket ticket) {
        return ticketMapper.insertTicket(ticket);
    }
    @Override
    public int deleteTicketById(Integer id) {
        return ticketMapper.deleteTicketById(id);
    }
    @Override
    public int updateTicketById(Ticket ticket) {
        return ticketMapper.updateTicketById(ticket);
    }
    @Override
    public Ticket selectTicketById(Integer id) {
        return ticketMapper.selectTicketById(id);
    }
    @Override
    public List<Ticket> selectAllTicket() {
        return ticketMapper.selectAllTicket();
    }
    @Override
    public List<Ticket> selectTicketByKeyword(String keyword) {
        return ticketMapper.selectTicketByKeyword(keyword);
    }
    @Override
    public List<Ticket> selectCurrentTicket() {
        List<Ticket> ticketList = ticketMapper.selectAllTicket();
        List<Orders> ordersList = ordersMapper.selectAllOrders();
        for (int i = 0; i < ordersList.size(); i++) {
            for (int j = 0; j < ticketList.size(); j++) {
                if (Objects.equals(ordersList.get(i).getTicketId(), ticketList.get(j).getId()) == true) {
                    ticketList.remove(j);
                    break;
                }
            }
        }
        return ticketList;
    }
    @Override
    public List<Ticket> selectCurrentTicketByKeyword(String keyword) {
        List<Ticket> ticketList = ticketMapper.selectTicketByKeyword(keyword);
        List<Orders> ordersList = ordersMapper.selectAllOrders();
        for (int i = 0; i < ordersList.size(); i++) {
            for (int j = 0; j < ticketList.size(); j++) {
                if (Objects.equals(ordersList.get(i).getTicketId(), ticketList.get(j).getId()) == true) {
                    ticketList.remove(j);
                    break;
                }
            }
        }
        return ticketList;
    }
    @Override
    public List<Ticket> selectBuyTicketByUserId(Integer userId) {
        return ticketMapper.selectBuyTicketByUserId(userId);
    }
    @Override
    public List<Ticket> selectBuyTicketByUserIdByKeyword(Integer userId, String keyword) {
        List<Ticket> ticketList = new ArrayList<>();
        List<Ticket> ticketList1 = ticketMapper.selectBuyTicketByUserId(userId);
        List<Ticket> ticketList2 = ticketMapper.selectTicketByKeyword(keyword);
        for (int i = 0; i < ticketList1.size(); i++) {
            for (int j = 0; j < ticketList2.size(); j++) {
                if (Objects.equals(ticketList1.get(i).getId(), ticketList2.get(j).getId()) == true) {
                    ticketList.add(ticketList1.get(i));
                }
            }
        }
        return ticketList;
    }
    @Override
    public void truncateTicket() {
        ordersMapper.truncateOrders();
        ticketMapper.closeForeignKeyChecks();
        ticketMapper.truncateTicket();
        ticketMapper.openForeignKeyChecks();
    }
}