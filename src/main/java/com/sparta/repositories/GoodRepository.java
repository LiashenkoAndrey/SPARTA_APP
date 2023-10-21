package com.sparta.repositories;

import com.sparta.domain.Good;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GoodRepository extends JpaRepository<Good, Long> {

    @Modifying
    @Transactional
    @Query(value = "insert into good_mark(client_id, good_id, mark) values (:clientId, :good_id, :mark)", nativeQuery = true)
     void markGood(@Param("clientId") Long clientId,
                   @Param("good_id") Long good_id,
                   @Param("mark") Boolean mark
    );
}
