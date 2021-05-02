package priv.wbk.springbucks.repository;

import org.springframework.data.repository.CrudRepository;
import priv.wbk.springbucks.model.CoffeeOrder;

/**
 * Created on 2021/5/2.
 *
 * @author wbk
 * @email 3207264942@qq.com
 */
public interface CoffeeOrderRepository extends CrudRepository<CoffeeOrder, Long> {
}
