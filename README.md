# Spring Bucks Project (学习项目-线上咖啡馆)

定义Coffee和CoffeeOder实体类

### 实体定义

* BaseEntity

实体基类，定义一些相同属性

```java
@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(updatable = false)
    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    private Date updateTime;
}

```

* Coffee

对应的是T_COFFEE数据表

```java
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "T_COFFEE")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coffee extends BaseEntity {

    private String name;

    @Column
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount",
            parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
    private Money price;

}

```

* OrderState

订单状态枚举类

```java
public enum OrderState {
    INIT, PAID, BREWING, BREWED, TAKEN, CANCELLED
}

```

* CoffeeOrder

对应的是T_ORDER数据表

```java
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "T_ORDER")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeOrder extends BaseEntity {
    
    private String customer;

    @ManyToMany
    @JoinTable(name = "T_ORDER_COFFEE")
    @OrderBy("id")
    private List<Coffee> items;

    /**
     * 订单状态
     */
    @Column(nullable = false)
    private OrderState state;
    
}

```

### Repository用法示例

```java
 public class SpringBucksApplication implements ApplicationRunner {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private CoffeeOrderRepository orderRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBucksApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initOrders();
    }

    private void initOrders() {
        Coffee espresso = Coffee.builder().name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .build();
        coffeeRepository.save(espresso);
        log.info("Coffee: {}", espresso);

        Coffee latte = Coffee.builder().name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .build();
        coffeeRepository.save(latte);
        log.info("Coffee: {}", latte);

        CoffeeOrder order = CoffeeOrder.builder()
                .customer("Li Lei")
                .items(Collections.singletonList(espresso))
                .state(OrderState.INIT)
                .build();
        orderRepository.save(order);
        log.info("Order: {}", order);

        order = CoffeeOrder.builder()
                .customer("Li Lei")
                .items(Arrays.asList(espresso, latte))
                .state(OrderState.INIT)
                .build();
        orderRepository.save(order);
        log.info("Order: {}", order);
    }

}

```

### Service定义示例

* CoffeeService类

```java
@Slf4j
@Service
public class CoffeeService {

    @Autowired
    private CoffeeRepository coffeeRepository;

   public Optional<Coffee> findOneCoffee(String name) {
       ExampleMatcher matcher = ExampleMatcher.matching()
               .withMatcher("name", exact().ignoreCase());
       Optional<Coffee> coffee = coffeeRepository.findOne(
               Example.of(Coffee.builder().name(name).build(), matcher));
       log.info("Coffee found: {}", coffee);
       return coffee;
   }
}

```


### Guides

The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

