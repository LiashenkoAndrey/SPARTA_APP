package com.sparta.repositories;

import com.sparta.domain.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {


    @Query(value = "select * from client_order where DATE(created_on) = current_date order by created_on desc", nativeQuery = true)
    List<Order> findForToday();

}
