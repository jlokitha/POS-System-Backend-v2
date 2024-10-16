package lk.ijse.possystembackendv2.repository;

import lk.ijse.possystembackendv2.entity.impl.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    @Query("SELECT od FROM OrderDetail od WHERE od.order.id=:orderId")
    List<OrderDetail> getAllByOrderId(@Param("orderId") Long orderId);
}
