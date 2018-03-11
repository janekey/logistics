package com.janekey.dao;

import com.janekey.model.LogisticsOrder;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 *
 * @author janekey
 * @version $Id: OrderRepository.java, v 0.1 2018-03-11 下午2:01 janekey Exp $
 */
public interface OrderRepository extends JpaSpecificationExecutor<LogisticsOrder>,
                                PagingAndSortingRepository<LogisticsOrder, Long> {

}
