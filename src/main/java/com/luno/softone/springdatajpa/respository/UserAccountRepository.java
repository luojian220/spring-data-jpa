package com.luno.softone.springdatajpa.respository;

import com.luno.softone.springdatajpa.model.entity.UserAccount;
import org.springframework.data.repository.CrudRepository;

/**
 * @author luojian
 * @version 1.0
 * @ClassName: UserAccountRepository
 * @Reason:
 * @date: 2019年03月28日 15:49
 * @since JDK 1.7
 */
public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {


    /**
     * 获取 UserAccount对象
     * @param accountCode
     * @return
     */
    public UserAccount getByAccountCode(String accountCode);

}
