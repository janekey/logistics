package com.jackey.controller;

import com.jackey.model.LogisticsOrder;
import com.jackey.dao.OrderRepository;
import com.jackey.model.OrderVO;
import com.jackey.util.CommonException;
import com.jackey.util.Invoke;
import com.jackey.util.OrderConverter;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    private static String   date_format = "((19|20)[0-9]{2})/(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])";

    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping("/order/save")
    public @ResponseBody Result save(Long id, String orderNo, String date, String destProvince,
                                     Double weight, Double firstPrice, Double continuePrice,
                                     Double totalPrice) {
        return process(new Invoke<LogisticsOrder>() {
            @Override
            public void paramValidate() {
                LOGGER.info("save:{0},{1},{2},{3},{4},{5},{6},{7}", id, orderNo, date,
                    destProvince, weight, firstPrice, continuePrice, totalPrice);
                if (StringUtil.isEmpty(orderNo) || StringUtil.isEmpty(date)
                    || StringUtil.isEmpty(destProvince)) {
                    throw new CommonException(ResultEnum.PARAM_ERROR);
                }
                if (!date.matches(date_format)) {
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
                LOGGER.info("delete:{0}", id);
                if (!orderRepository.existsById(id)) {
                    throw new CommonException(ResultEnum.ID_NOT_EXIST);
                }
                orderRepository.deleteById(id);
                return null;
            }
        });
    }

    @RequestMapping(path = "/order/query")
    public @ResponseBody PageResult<List<OrderVO>> query(Long id, String orderNo, String dateStart,
                                                         String dateEnd, String destProvince,
                                                         Double weight, Double firstPrice,
                                                         Double continuePrice, Double totalPrice,
                                                         Integer page, Integer pageSize) {
        PageResult<List<OrderVO>> pageResult = new PageResult<>();
        if (page == null || page <= 0) {
            page = 0;
        } else {
            page--;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 10;
        }
        try {
            if (!StringUtil.isEmpty(dateStart) && !dateStart.matches(date_format)) {
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
                List<OrderVO> list = new ArrayList<>();
                for (LogisticsOrder order : queryList.getContent()) {
                    list.add(OrderConverter.convertToVO(order));
                }
                pageResult.setData(list);
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

    @RequestMapping(path = "/order/import")
    public @ResponseBody Result importOrder(@RequestParam("file") MultipartFile file) {
        return process(new Invoke<Object>() {

            public void paramValidate() {
                String fileName = file.getOriginalFilename();
                if (StringUtil.isEmpty(fileName) || !fileName.matches(".*\\.csv")) {
                    throw new CommonException(ResultEnum.ONLY_CSV);
                }
            }

            public Object invoke() {
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                        file.getInputStream()));
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] lineColumns = line.split(",");
                        if (lineColumns.length < 3) {
                            LOGGER.info("忽略该行:" + line);
                            continue;
                        }

                        String date = lineColumns[0];
                        String orderNo = lineColumns[1];
                        String destProvince = lineColumns[2];
                        if (!date.matches(date_format)) {
                            LOGGER.info("忽略该行:" + line);
                            continue;
                        }

                        LOGGER.info("保存:" + date + "*" + orderNo + "*" + destProvince);
                    }
                } catch (IOException e) {
                    LOGGER.error("import error", e);
                }
                return null;
            }
        });
    }
}
