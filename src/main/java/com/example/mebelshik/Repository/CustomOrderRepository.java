package com.example.mebelshik.Repository;

import com.example.mebelshik.Entitiy.CustomOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomOrderRepository extends JpaRepository<CustomOrder, Long> {

}
