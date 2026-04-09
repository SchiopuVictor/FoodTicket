package foodticket.foodticket.repositpory;

import foodticket.foodticket.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRespository extends JpaRepository<Order,Long> {
}
