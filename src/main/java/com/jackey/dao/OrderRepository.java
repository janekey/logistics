package com.jackey.dao;

import com.jackey.model.LogisticsOrder;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 *
 * @author jackey
 * @version $Id: OrderRepository.java, v 0.1 2018-03-11 下午2:01 jackey Exp $
 */
public interface OrderRepository extends JpaSpecificationExecutor<LogisticsOrder>,
                                PagingAndSortingRepository<LogisticsOrder, Long> {

}
