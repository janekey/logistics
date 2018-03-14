package com.jackey.model;

/**
 *
 * @author jackey
 * @version $Id: OrderVO.java, v 0.1 2018-03-13 下午11:19 jackey Exp $
 */
public class OrderVO {

    private Long   id;

    /** 单号 */
    private String orderNo;

    /** 日期 */
    private String date;

    /** 目的地省份 */
    private String destProvince;

    /** 重量 */
    private Double weight;

    /** 首重 */
    private Double firstPrice;

    /** 续重 */
    private Double continuePrice;

    /** 价格 */
    private Double totalPrice;

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
     * Getter method for property <tt>date</tt>.
     *
     * @return property value of date
     */
    public String getDate() {
        return date;
    }

    /**
     * Setter method for property <tt>date</tt>.
     *
     * @param date value to be assigned to property date
     */
    public void setDate(String date) {
        this.date = date;
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
}
