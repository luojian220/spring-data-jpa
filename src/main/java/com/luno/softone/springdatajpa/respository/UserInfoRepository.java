package com.luno.softone.springdatajpa.respository;

import com.luno.softone.springdatajpa.model.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * @author luojian
 * @version 1.0
 * @ClassName: UserInfoRespository
 * @Reason:
 * @date: 2019年03月28日 15:08
 * @since JDK 1.7
 */
public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {


}
