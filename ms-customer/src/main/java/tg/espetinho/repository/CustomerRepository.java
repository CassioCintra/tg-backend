package tg.espetinho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tg.espetinho.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
}
