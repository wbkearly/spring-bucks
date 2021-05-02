package priv.wbk.springbucks.service;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.wbk.springbucks.model.Coffee;
import priv.wbk.springbucks.model.CoffeeOrder;
import priv.wbk.springbucks.model.OrderState;
import priv.wbk.springbucks.repository.CoffeeOrderRepository;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created on 2021/5/2.
 *
 * @author wbk
 * @email 3207264942@qq.com
 */
@Slf4j
@Service
public class CoffeeOrderService {

    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;

    public CoffeeOrder createOrder(String customer, Coffee ...coffees) {
        CoffeeOrder order = CoffeeOrder.builder()
                .customer(customer)
                .items(new ArrayList<>(Arrays.asList(coffees)))
                .state(OrderState.INIT)
                .build();
        CoffeeOrder savedOrder = coffeeOrderRepository.save(order);
        log.info("saved order: {}", savedOrder);
        return savedOrder;
    }

    public boolean updateState(CoffeeOrder order, OrderState state) {
        if (state.compareTo(order.getState()) <= 0) {
            log.warn("Wrong State Order: {}, {}", state, order.getState());
        }
        order.setState(state);
        CoffeeOrder updatedOrder = coffeeOrderRepository.save(order);
        log.info("updated order: {}", updatedOrder);
        return true;
    }
}
