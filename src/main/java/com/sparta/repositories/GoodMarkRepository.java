package com.sparta.repositories;

import com.sparta.domain.GoodMark;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;

public interface GoodMarkRepository extends JpaRepository<GoodMark, Long> {

    List<GoodMark> getGoodMarksByClientId(Long clientId);

    @Transactional
    @Modifying
    void deleteAllByGoodId(Long goodId);

    GoodMark getGoodMarksByClientIdAndGoodId(Long clientId, Long goodId);
}
