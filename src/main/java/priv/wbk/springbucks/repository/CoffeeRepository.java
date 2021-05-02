package priv.wbk.springbucks.repository;

import org.springframework.data.repository.CrudRepository;
import priv.wbk.springbucks.model.Coffee;

/**
 * Created on 2021/5/2.
 *
 * @author wbk
 * @email 3207264942@qq.com
 */
public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
}
