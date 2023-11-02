package com.sparta.repositories;

import com.sparta.domain.Good;
import com.sparta.domain.dto.Mark;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GoodRepository extends JpaRepository<Good, Long> {

    @Modifying
    @Transactional
    @Query(value = "insert into good_mark(client_id, good_id, mark) values (:clientId, :good_id, :mark)", nativeQuery = true)
    void markGood(@Param("clientId") Long clientId,
                  @Param("good_id") Long good_id,
                  @Param("mark") Boolean mark
    );


    @Query(value = "select exists(select * from good_amount where good_id = :goodId)", nativeQuery = true)
    boolean isPresentOnClientOrders(@Param("goodId") Long id);


    List<Good> findAllByIsDeletedFalse();
}
