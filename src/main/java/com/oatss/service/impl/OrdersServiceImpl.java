package com.oatss.service.impl;
import com.oatss.mapper.OrdersMapper;
import com.oatss.mapper.UserMapper;
import com.oatss.entity.Orders;
import com.oatss.service.OrdersService;
import com.oatss.utils.ValidUtils;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
@Service
public class OrdersServiceImpl implements OrdersService {
    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private UserMapper userMapper;
    @Override
    public int insertOrders(Orders orders) {
        return ordersMapper.insertOrders(orders);
    }
    @Override
    public int deleteOrdersByTicketId(Integer ticketId) {
        return ordersMapper.deleteOrdersByTicketId(ticketId);
    }
    @Override
    public List<Orders> selectAllOrders() {
        return ordersMapper.selectAllOrders();
    }
    @Override
    public List<List<String>> selectOrdersRecord() {
        List<List<String>> ordersRecordList = new ArrayList<>();
        List<Orders> ordersList = ordersMapper.selectAllOrders();
        for (int i = 0; i < ordersList.size(); i++) {
            Orders orders = ordersList.get(i);
            ordersRecordList.add(new ArrayList<>(
                            Arrays.asList(
                                    String.valueOf(orders.getId()),
                                    String.valueOf(orders.getTicketId()),
                                    userMapper.selectUserById(orders.getUserId()).getUsername(),
                                    orders.getTime().toLocalDateTime().format(DateTimeFormatter.ofPattern(ValidUtils.DATA_TIME_PATTERN1))
                            )
                    )
            );
        }
        return ordersRecordList;
    }
}