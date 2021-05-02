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

对应的是T_MENU数据表

```java
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "T_MENU")
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

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

