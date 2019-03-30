package com.luno.softone.springdatajpa.respository;

import com.luno.softone.springdatajpa.model.entity.UserAccountLog;
import org.springframework.data.repository.CrudRepository;

/**
 * @author luojian
 * @version 1.0
 * @ClassName: UserAccountLogRepository
 * @Reason:
 * @date: 2019年03月30日 11:20
 * @company:warWolf
 * @since JDK 1.8
 */
public interface UserAccountLogRepository extends CrudRepository<UserAccountLog,Long> {



}
