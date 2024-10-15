package lk.ijse.possystembackendv2.repository;

import lk.ijse.possystembackendv2.entity.impl.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
