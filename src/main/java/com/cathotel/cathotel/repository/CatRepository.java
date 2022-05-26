package com.cathotel.cathotel.repository;

import com.cathotel.cathotel.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat, Long> {
}
