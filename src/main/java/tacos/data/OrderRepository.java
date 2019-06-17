package tacos.data;

import org.springframework.core.annotation.Order;

public interface OrderRepository{
    Order save(Order order);
}