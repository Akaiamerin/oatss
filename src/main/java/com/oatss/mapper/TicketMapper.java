package com.oatss.mapper;
import com.oatss.entity.Ticket;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface TicketMapper {
    @Insert("INSERT INTO ticket (id, origin, destination, takeoff, land, price) VALUES (NULL, #{origin}, #{destination}, #{takeoff}, #{land}, #{price})")
    int insertTicket(Ticket ticket);
    @Delete("DELETE FROM ticket WHERE id = #{id}")
    int deleteTicketById(Integer id);
    @Update("UPDATE ticket SET origin = #{origin}, destination = #{destination}, takeoff = #{takeoff}, land = #{land}, price = #{price} WHERE id = #{id}")
    int updateTicketById(Ticket ticket);
    @Select("SELECT * FROM ticket WHERE id = #{id}")
    Ticket selectTicketById(Integer id);
    @Select("SELECT * FROM ticket")
    List<Ticket> selectAllTicket();
    @Select("SELECT * FROM ticket WHERE CONCAT (id, origin, destination, takeoff, land, price) LIKE CONCAT ('%', #{keyword},'%')")
    List<Ticket> selectTicketByKeyword(String keyword);
    @Select("SELECT * FROM ticket, orders WHERE ticket.id = orders.ticket_id AND user_id = #{userId}")
    List<Ticket> selectBuyTicketByUserId(Integer userId);
    @Update("SET FOREIGN_KEY_CHECKS = 0")
    void closeForeignKeyChecks();
    @Update("TRUNCATE TABLE ticket")
    void truncateTicket();
    @Update("SET FOREIGN_KEY_CHECKS = 1")
    void openForeignKeyChecks();
}