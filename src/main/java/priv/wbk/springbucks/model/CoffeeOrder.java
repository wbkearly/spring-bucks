package priv.wbk.springbucks.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.joda.money.Money;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created on 2021/5/2.
 *
 * @author wbk
 * @email 3207264942@qq.com
 * 咖啡订单实体类---对应数据库中的TABLE_ORDER数据表
 */
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
