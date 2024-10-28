package com.example.mebelshik.Repository;

import com.example.mebelshik.Entitiy.Filter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilterRepository extends JpaRepository<Filter, Long> {
}
