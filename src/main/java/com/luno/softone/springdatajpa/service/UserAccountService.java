package com.luno.softone.springdatajpa.service;

import com.luno.softone.springdatajpa.model.entity.UserAccount;
import com.luno.softone.springdatajpa.respository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author luojian
 * @version 1.0
 * @ClassName: UserAccountService
 * @Reason:
 * @date: 2019年03月28日 15:50
 * @since JDK 1.7
 */
@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    public UserAccount save(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }

    public UserAccount findById(Long id) {
        Optional<UserAccount> accountOptional = userAccountRepository.findById(id);
        return accountOptional.orElse(null);
    }


    public UserAccount getByAccountCode(String accountCode) {
        UserAccount userAccount = userAccountRepository.getByAccountCode(accountCode);
        return userAccount;
    }
}
