package com.jackey.controller;

import com.jackey.model.LogisticsOrder;
import com.jackey.dao.OrderRepository;
import com.jackey.util.CommonException;
import com.jackey.util.Invoke;
import com.jackey.util.PageResult;
import com.jackey.util.Result;
import com.jackey.util.ResultEnum;
import com.jackey.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author jackey
 * @version $Id: TestController.java, v 0.1 2018-03-09 下午8:49 jackey Exp $
 */
@Controller
public class OrderController extends AbstractController {

    private static String   format = "((19|20)[0-9]{2})/(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])";

    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping("/order/save")
    public @ResponseBody Result save(Long id, String orderNo, String date, String destProvince,
                                     Double weight, Double firstPrice, Double continuePrice,
                                     Double totalPrice) {
        return process(new Invoke<LogisticsOrder>() {
            @Override
            public void paramValidate() {
                if (!date.matches(format)) {
                    throw new CommonException(ResultEnum.DATE_INVALID);
                }
            }

            public LogisticsOrder invoke() {
                LogisticsOrder order;
                if (id == null || id <= 0) {
                    order = new LogisticsOrder();
                    order.setDateCreate(new Date());
                } else {
                    Optional<LogisticsOrder> optional = orderRepository.findById(id);
                    if (!optional.isPresent()) {
                        throw new CommonException(ResultEnum.ID_NOT_EXIST);
                    }
                    order = optional.get();
                }

                order.setOrderNo(orderNo);
                order.setDestProvince(destProvince);
                order.setWeight(weight);
                order.setFirstPrice(firstPrice);
                order.setContinuePrice(continuePrice);
                order.setTotalPrice(totalPrice);
                order.setDateModify(new Date());

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("y/M/d");
                    order.setDate(sdf.parse(date));
                } catch (ParseException e) {
                    throw new CommonException(ResultEnum.DATE_INVALID);
                }

                return orderRepository.save(order);
            }
        });
    }

    @RequestMapping("/order/delete")
    public @ResponseBody Result delete(Long id) {
        return process(new Invoke<Object>() {
            @Override
            public Object invoke() {
                if (!orderRepository.existsById(id)) {
                    throw new CommonException(ResultEnum.ID_NOT_EXIST);
                }
                orderRepository.deleteById(id);
                return null;
            }
        });
    }

    @RequestMapping(path = "/order/query")
    public @ResponseBody PageResult<List<LogisticsOrder>> query(Long id, String orderNo,
                                                                String dateStart, String dateEnd,
                                                                String destProvince, Double weight,
                                                                Double firstPrice,
                                                                Double continuePrice,
                                                                Double totalPrice, Integer page,
                                                                Integer pageSize) {
        PageResult<List<LogisticsOrder>> pageResult = new PageResult<>();
        if (page == null || page <= 0) {
            page = 0;
        } else {
            page--;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 10;
        }
        try {
            if (!StringUtil.isEmpty(dateStart) && !dateStart.matches(format)) {
                throw new CommonException(ResultEnum.DATE_INVALID);
            }
            if (!StringUtil.isEmpty(dateEnd) && !dateStart.matches(dateEnd)) {
                throw new CommonException(ResultEnum.DATE_INVALID);
            }

            Page<LogisticsOrder> queryList = orderRepository
                .findAll(
                    (Root<LogisticsOrder> root, CriteriaQuery<?> query,
                     CriteriaBuilder criteriaBuilder) -> {

                        List<Predicate> predicateList = new ArrayList<>();

                        if (id != null && id > 0) {
                            predicateList.add(criteriaBuilder.equal(root.get("id"), id));
                        }
                        if (!StringUtil.isEmpty(orderNo)) {
                            predicateList.add(criteriaBuilder.equal(root.get("orderNo"), orderNo));
                        }
                        if (!StringUtil.isEmpty(destProvince)) {
                            predicateList.add(criteriaBuilder.equal(root.get("destProvince"),
                                destProvince));
                        }
                        if (weight != null) {
                            predicateList.add(criteriaBuilder.equal(root.get("weight"), weight));
                        }
                        if (firstPrice != null) {
                            predicateList.add(criteriaBuilder.equal(root.get("firstPrice"),
                                firstPrice));
                        }
                        if (continuePrice != null) {
                            predicateList.add(criteriaBuilder.equal(root.get("continuePrice"),
                                continuePrice));
                        }
                        if (totalPrice != null) {
                            predicateList.add(criteriaBuilder.equal(root.get("totalPrice"),
                                totalPrice));
                        }
                        SimpleDateFormat sdf = new SimpleDateFormat("y/M/d");
                        if (!StringUtil.isEmpty(dateStart)) {
                            try {
                                Date start = sdf.parse(dateStart);
                                predicateList.add(criteriaBuilder.greaterThanOrEqualTo(
                                    root.get("date"), start));
                            } catch (ParseException e) {
                                throw new CommonException(ResultEnum.DATE_INVALID);
                            }

                        }
                        if (!StringUtil.isEmpty(dateEnd)) {
                            try {
                                predicateList.add(criteriaBuilder.lessThanOrEqualTo(
                                    root.get("date"), sdf.parse(dateEnd)));
                            } catch (ParseException e) {
                                throw new CommonException(ResultEnum.DATE_INVALID);
                            }
                        }

                        query.where(predicateList.toArray(new Predicate[predicateList.size()]));
                        return null;

                    }, PageRequest.of(page, pageSize, Sort.by("id").descending()));
            long total = queryList.getTotalElements();
            if (total > 0) {
                pageResult.setData(queryList.getContent());
            }
            pageResult.setTotal(total);
            pageResult.setResult(ResultEnum.SUCCESS);
        } catch (CommonException e) {
            pageResult.setResult(e.getResult());
        } catch (Throwable th) {
            pageResult.setResult(ResultEnum.SYSTEM_ERROR);
        }
        return pageResult;
    }
}
