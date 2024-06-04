package com.oatss.mapper;
import com.oatss.entity.Orders;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface OrdersMapper {
    @Insert("INSERT INTO orders (id, ticket_id, user_id, time) VALUES (NULL, #{ticketId}, #{userId}, #{time})")
    int insertOrders(Orders orders);
    @Delete("DELETE FROM orders WHERE ticket_id = #{ticketId}")
    int deleteOrdersByTicketId(Integer ticketId);
    @Select("SELECT * FROM orders")
    @Results({
            @Result(column = "ticket_id", property = "ticketId"),
            @Result(column = "user_id", property = "userId")
    })
    List<Orders> selectAllOrders();
    @Update("TRUNCATE TABLE orders")
    void truncateOrders();
}