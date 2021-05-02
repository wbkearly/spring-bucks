package priv.wbk.springbucks.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.joda.money.Money;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created on 2021/5/2.
 *
 * @author wbk
 * @email 3207264942@qq.com
 * 咖啡实体类----对应数据表中的T_MENU数据表
 */
@Entity
@Table(name = "T_MENU")
@Builder
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Coffee extends BaseEntity {

    private String name;

    /**
     * 注意类型，在数据库中是作为bigint存放
     */
    @Column
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
            parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
    private Money price;
}
