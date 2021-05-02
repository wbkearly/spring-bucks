package priv.wbk.springbucks.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import priv.wbk.springbucks.model.Coffee;
import priv.wbk.springbucks.repository.CoffeeRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

/**
 * Created on 2021/5/2.
 *
 * @author wbk
 * @email 3207264942@qq.com
 */
@Slf4j
@Service
public class CoffeeService {

    @Autowired
    private CoffeeRepository coffeeRepository;

    public List<Coffee> findAllCoffees() {
        return coffeeRepository.findAll();
    }

   public Optional<Coffee> findOneCoffee(String name) {
       ExampleMatcher matcher = ExampleMatcher.matching()
               .withMatcher("name", exact().ignoreCase());
       Optional<Coffee> coffee = coffeeRepository.findOne(
               Example.of(Coffee.builder().name(name).build(), matcher));
       log.info("Coffee found: {}", coffee);
       return coffee;
   }
}
