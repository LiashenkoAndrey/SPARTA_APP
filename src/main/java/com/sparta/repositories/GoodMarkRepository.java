package com.sparta.repositories;

import com.sparta.domain.GoodMark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GoodMarkRepository extends JpaRepository<GoodMark, Long> {

    List<GoodMark> getGoodMarksByClientId(Long clientId);

    GoodMark getGoodMarksByClientIdAndGoodId(Long clientId, Long goodId);
}
