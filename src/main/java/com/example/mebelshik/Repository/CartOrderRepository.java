package com.example.mebelshik.Repository;

import com.example.mebelshik.Entitiy.CartOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartOrderRepository extends JpaRepository<CartOrder, Long> {

}
