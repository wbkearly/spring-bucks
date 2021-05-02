# Spring Bucks Project (学习项目-线上咖啡馆)

定义Coffee和CoffeeOder实体类

### 实体定义

* Coffee

对应的是T_MENU数据表

```java
@Entity
@Table(name = "T_MENU")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coffee implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount",
            parameters = {@org.hibernate.annotations.Parameter(name = "CurrencyCode", value = "CNY")})
    private Money price;

    @Column(updatable = false)
    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    private Date updateTime;
}

```

* CoffeeOrder

对应的是T_ORDER数据表

```java
@Entity
@Table(name = "T_ORDER")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeOrder implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String customer;

    @ManyToMany
    @JoinTable(name = "T_ORDER_COFFEE")
    private List<Coffee> items;

    /**
     * 订单状态
     */
    @Column(nullable = false)
    private Integer state;

    @Column(updatable = false)
    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    private Date updateTime;
}

```

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

