package com.jackey.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 *
 * @author jackey
 * @version $Id: LogisticsOrder.java, v 0.1 2018-03-11 下午1:46 jackey Exp $
 */
@Entity
public class LogisticsOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;

    /** 单号 */
    @Column(name = "order_no")
    private String orderNo;

    /** 日期 */
    @Column(name = "date")
    private Date   date;

    /** 目的地省份 */
    @Column(name = "dest_province")
    private String destProvince;

    /** 重量 */
    @Column(name = "weight")
    private Double weight;

    /** 首重 */
    @Column(name = "first_price")
    private Double firstPrice;

    /** 续重 */
    @Column(name = "continue_price")
    private Double continuePrice;

    /** 价格 */
    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "date_create")
    private Date   dateCreate;

    @Column(name = "date_modify")
    private Date   dateModify;

    /**
     * Getter method for property <tt>id</tt>.
     *
     * @return property value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     *
     * @param id value to be assigned to property id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>orderNo</tt>.
     *
     * @return property value of orderNo
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * Setter method for property <tt>orderNo</tt>.
     *
     * @param orderNo value to be assigned to property orderNo
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * Getter method for property <tt>destProvince</tt>.
     *
     * @return property value of destProvince
     */
    public String getDestProvince() {
        return destProvince;
    }

    /**
     * Setter method for property <tt>destProvince</tt>.
     *
     * @param destProvince value to be assigned to property destProvince
     */
    public void setDestProvince(String destProvince) {
        this.destProvince = destProvince;
    }

    /**
     * Getter method for property <tt>firstPrice</tt>.
     *
     * @return property value of firstPrice
     */
    public Double getFirstPrice() {
        return firstPrice;
    }

    /**
     * Setter method for property <tt>firstPrice</tt>.
     *
     * @param firstPrice value to be assigned to property firstPrice
     */
    public void setFirstPrice(Double firstPrice) {
        this.firstPrice = firstPrice;
    }

    /**
     * Getter method for property <tt>continuePrice</tt>.
     *
     * @return property value of continuePrice
     */
    public Double getContinuePrice() {
        return continuePrice;
    }

    /**
     * Setter method for property <tt>continuePrice</tt>.
     *
     * @param continuePrice value to be assigned to property continuePrice
     */
    public void setContinuePrice(Double continuePrice) {
        this.continuePrice = continuePrice;
    }

    /**
     * Getter method for property <tt>totalPrice</tt>.
     *
     * @return property value of totalPrice
     */
    public Double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Setter method for property <tt>totalPrice</tt>.
     *
     * @param totalPrice value to be assigned to property totalPrice
     */
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Getter method for property <tt>weight</tt>.
     *
     * @return property value of weight
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * Setter method for property <tt>weight</tt>.
     *
     * @param weight value to be assigned to property weight
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    /**
     * Setter method for property <tt>date</tt>.
     *
     * @param date value to be assigned to property date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Getter method for property <tt>date</tt>.
     *
     * @return property value of date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Getter method for property <tt>dateCreate</tt>.
     *
     * @return property value of dateCreate
     */
    public Date getDateCreate() {
        return dateCreate;
    }

    /**
     * Setter method for property <tt>dateCreate</tt>.
     *
     * @param dateCreate value to be assigned to property dateCreate
     */
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    /**
     * Getter method for property <tt>dateModify</tt>.
     *
     * @return property value of dateModify
     */
    public Date getDateModify() {
        return dateModify;
    }

    /**
     * Setter method for property <tt>dateModify</tt>.
     *
     * @param dateModify value to be assigned to property dateModify
     */
    public void setDateModify(Date dateModify) {
        this.dateModify = dateModify;
    }
}
