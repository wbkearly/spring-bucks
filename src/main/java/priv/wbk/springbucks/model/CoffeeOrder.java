package priv.wbk.springbucks.model;

import lombok.*;
import javax.persistence.*;
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
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
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
