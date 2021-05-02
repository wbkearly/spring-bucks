package priv.wbk.springbucks;

import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import priv.wbk.springbucks.model.Coffee;
import priv.wbk.springbucks.model.CoffeeOrder;
import priv.wbk.springbucks.model.OrderState;
import priv.wbk.springbucks.repository.CoffeeOrderRepository;
import priv.wbk.springbucks.repository.CoffeeRepository;
import priv.wbk.springbucks.service.CoffeeOrderService;
import priv.wbk.springbucks.service.CoffeeService;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

/**
 * Created on 2021/5/2.
 *
 * @author wbk
 * @email 3207264942@qq.com
 */
@SpringBootApplication
@EnableJpaRepositories
@Slf4j
public class SpringBucksApplication implements ApplicationRunner {

    @Autowired
    private CoffeeService coffeeService;

    @Autowired
    private CoffeeOrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBucksApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Optional<Coffee> latte = coffeeService.findOneCoffee("Latte");
        if (latte.isPresent()) {
            CoffeeOrder order = orderService.createOrder("Li Lei", latte.get());
            log.info("{}", orderService.updateState(order, OrderState.PAID));
            log.info("{}", orderService.updateState(order, OrderState.INIT));
        }
    }

}
