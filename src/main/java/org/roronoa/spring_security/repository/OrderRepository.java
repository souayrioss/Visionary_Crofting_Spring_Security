package org.roronoa.spring_security.repository;

import org.roronoa.spring_security.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByUuid(String reference);
}
