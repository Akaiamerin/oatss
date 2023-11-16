package com.oatss.service;
import com.oatss.pojo.Orders;
import java.util.List;
public interface OrdersService {
    int insertOrders(Orders orders);
    int deleteOrdersByTicketId(Integer ticketId);
    List<Orders> selectAllOrders();
    List<List<String>> selectOrdersRecord();
}