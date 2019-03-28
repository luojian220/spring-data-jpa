package com.luno.softone.springdatajpa;

import com.luno.softone.springdatajpa.model.entity.UserAccount;
import com.luno.softone.springdatajpa.model.entity.UserInfo;
import com.luno.softone.springdatajpa.respository.UserInfoRepository;
import com.luno.softone.springdatajpa.service.UserAccountService;
import com.luno.softone.springdatajpa.service.UserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataJpaApplicationTests {

	private Logger logger = LoggerFactory.getLogger("SpringDataJpaApplicationTests");
	@Autowired
	UserInfoService userInfoService;

	@Autowired
	private UserAccountService userAccountService;
	@Test
	public void userInfo_save() {

		UserInfo userInfo = new UserInfo() ;
		userInfo.setId(1L);
		userInfo.setJobNumber("1009");
		userInfo.setName("joo");
		userInfo.setCreateTime(new Date());
		userInfoService.save(userInfo);
		logger.info(userInfo.toString());
	}

	@Test
	public void userInfo_get() {


		UserInfo userInfo = userInfoService.findById(1L);
		logger.info(String.valueOf(userInfo));
	}

	@Test
	public void userInfo_update() {


		UserInfo userInfo = userInfoService.findById(3L);
		userInfo.setJobNumber("1006");
		userInfoService.save(userInfo);
		logger.info(userInfo.toString());
	}

	/**
	 * 使用jpa 自带的 @Version注解时， 该字段不赋值，就会当成一个新的记录，执行insert
	 * 若该字段有值，但是值与数据库最新值不一致时，会抛出乐观锁异常：org.hibernate.StaleObjectStateException
	 * 只有主键 及 version 一致时，才会更新成功。结论：version字段必须赋值
	 */
	@Test
	public void userAccount_version_save() {

		UserAccount userAccount = new UserAccount();
		userAccount.setAccountCode("luno_0001");
		userAccount.setAmount(BigDecimal.ZERO);
		userAccount.setId(1L);
		userAccount.setVersion(6L);
		userAccountService.save(userAccount);
		logger.info(userAccount.toString());
	}

	@Test
	public void userAccount_version_update() {

		UserAccount userAccount = userAccountService.findById(1L);

		userAccount.setAmount(userAccount.getAmount().add(BigDecimal.ONE));
		userAccountService.save(userAccount);
		logger.info(userAccount.toString());
	}
}
