package com.oatss.entity;
import java.sql.Timestamp;
public class Orders {
    private Integer id;
    private Integer ticketId;
    private Integer userId;
    private Timestamp time;
    public Orders() {

    }
    public Orders(Integer id, Integer ticketId, Integer userId, Timestamp time) {
        this.id = id;
        this.ticketId = ticketId;
        this.userId = userId;
        this.time = time;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getTicketId() {
        return ticketId;
    }
    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Timestamp getTime() {
        return time;
    }
    public void setTime(Timestamp time) {
        this.time = time;
    }
}