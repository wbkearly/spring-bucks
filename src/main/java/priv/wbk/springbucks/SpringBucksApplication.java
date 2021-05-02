package priv.wbk.springbucks;

import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import priv.wbk.springbucks.service.CoffeeService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;

/**
 * Created on 2021/5/2.
 *
 * @author wbk
 * @email 3207264942@qq.com
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@Slf4j
public class SpringBucksApplication implements ApplicationRunner {

    @Autowired
    private CoffeeService coffeeService;

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private JedisPoolConfig jedisPoolConfig;

    @Bean
    @ConfigurationProperties("redis")
    public JedisPoolConfig getJedisPoolConfig() {
        return new JedisPoolConfig();
    }

    @Bean(destroyMethod = "close")
    public JedisPool getJedisPool(@Value("${redis.host}") String host) {
        return new JedisPool(getJedisPoolConfig(), host);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBucksApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info(jedisPoolConfig.toString());
        try (Jedis jedis = jedisPool.getResource()) {
            coffeeService.findAllCoffees().forEach(coffee -> {
                jedis.hset("springbucks-menu",
                        coffee.getName(),
                        Long.toString(coffee.getPrice().getAmountMinorLong()));
            });
            Map<String, String> menu = jedis.hgetAll("springbucks-menu");
            log.info("Menu: {}", menu);

            String price = jedis.hget("springbucks-menu", "espresso");
            log.info("espresso - {}",
                    Money.of(CurrencyUnit.of("CNY"), Long.parseLong(price)));
        }
    }

}
