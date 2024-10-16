package lk.ijse.possystembackendv2.repository;

import lk.ijse.possystembackendv2.entity.impl.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
