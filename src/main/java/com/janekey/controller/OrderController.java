package com.janekey.controller;

import com.janekey.model.LogisticsOrder;
import com.janekey.dao.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author janekey
 * @version $Id: TestController.java, v 0.1 2018-03-09 下午8:49 janekey Exp $
 */
@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
                           Model model) {
        return "greeting";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<LogisticsOrder> getAllUsers() {
        // This returns a JSON or XML with the users
        return orderRepository.findAll();
    }

    @GetMapping(path = "/query")
    public @ResponseBody Object query() {
        Page page = orderRepository.findAll(new Specification<LogisticsOrder>() {
            @Override
            public Predicate toPredicate(Root<LogisticsOrder> root, CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                predicateList.add(criteriaBuilder.equal(root.get("orderNo"), "43"));

                query.where(predicateList.toArray(new Predicate[predicateList.size()]));

                return null;
            }
        }, new PageRequest(0, 10));
        return page;
    }
}
