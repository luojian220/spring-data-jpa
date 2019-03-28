package com.luno.softone.springdatajpa.service;

import com.luno.softone.springdatajpa.model.entity.UserInfo;
import com.luno.softone.springdatajpa.respository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author luojian
 * @version 1.0
 * @ClassName: UserInfoService
 * @Reason:
 * @date: 2019年03月28日 15:03
 * @since JDK 1.7
 */
@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;



    public UserInfo save(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }

    public UserInfo findById(Long id) {
        Optional<UserInfo> userInfo = userInfoRepository.findById(id);
        return userInfo.orElse(null);
    }

}
