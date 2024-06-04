package com.oatss.entity;
import com.oatss.utils.ValidUtils;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
public class Ticket {
    private Integer id;
    private String origin;
    private String destination;
    private Timestamp takeoff;
    private Timestamp land;
    private Double price;
    public Ticket() {

    }
    public Ticket(Integer id, String origin, String destination, Timestamp takeoff, Timestamp land, Double price) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.takeoff = takeoff;
        this.land = land;
        this.price = price;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public Timestamp getTakeoff() {
        return takeoff;
    }
    public String getTakeoffString() {
        return takeoff.toLocalDateTime().format(DateTimeFormatter.ofPattern(ValidUtils.DATA_TIME_PATTERN1));
    }
    public void setTakeoff(Timestamp takeoff) {
        this.takeoff = takeoff;
    }
    public Timestamp getLand() {
        return land;
    }
    public String getLandString() {
        return land.toLocalDateTime().format(DateTimeFormatter.ofPattern(ValidUtils.DATA_TIME_PATTERN1));
    }
    public void setLand(Timestamp land) {
        this.land = land;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}