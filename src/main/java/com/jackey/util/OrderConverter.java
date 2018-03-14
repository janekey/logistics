package com.jackey.util;

import com.jackey.model.LogisticsOrder;
import com.jackey.model.OrderVO;

import java.text.SimpleDateFormat;

/**
 *
 * @author jackey
 * @version $Id: OrderConverter.java, v 0.1 2018-03-13 下午11:21 jackey Exp $
 */
public class OrderConverter {

    public static OrderVO convertToVO(LogisticsOrder source) {
        if (source == null) {
            return null;
        }
        OrderVO orderVO = new OrderVO();
        orderVO.setId(source.getId());
        orderVO.setOrderNo(source.getOrderNo());
        SimpleDateFormat sdf = new SimpleDateFormat("y/M/d");
        if (source.getDate() != null) {
            orderVO.setDate(sdf.format(source.getDate()));
        }
        orderVO.setDestProvince(source.getDestProvince());
        orderVO.setWeight(source.getWeight());
        orderVO.setFirstPrice(source.getFirstPrice());
        orderVO.setContinuePrice(source.getContinuePrice());
        orderVO.setTotalPrice(source.getTotalPrice());
        return orderVO;
    }

}
