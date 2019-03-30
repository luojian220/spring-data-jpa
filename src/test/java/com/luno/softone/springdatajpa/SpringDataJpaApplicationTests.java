package com.luno.softone.springdatajpa;

import com.luno.softone.springdatajpa.model.entity.UserAccount;
import com.luno.softone.springdatajpa.model.entity.UserAccountLog;
import com.luno.softone.springdatajpa.model.entity.UserInfo;
import com.luno.softone.springdatajpa.respository.UserAccountLogRepository;
import com.luno.softone.springdatajpa.service.UserAccountService;
import com.luno.softone.springdatajpa.service.UserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataJpaApplicationTests {

	private Logger logger = LoggerFactory.getLogger("SpringDataJpaApplicationTests");
	@Autowired
	UserInfoService userInfoService;

	@Autowired
	private UserAccountService userAccountService;

	@Autowired
	private UserAccountLogRepository userAccountLogRepository;

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


		UserInfo userInfo = userInfoService.findById(1L);
		userInfo.setJobNumber("1006");
		userInfoService.save(userInfo);
		logger.info(userInfo.toString());
	}

	/**
	 * 使用jpa 自带的 @Version注解时， 该字段不赋值，就会当成一个新的记录，执行insert
	 * 若该字段有值，但是值与数据库最新值不一致时，会抛出乐观锁异常：org.hibernate.StaleObjectStateException
	 * 只有主键 及 version 一致时，才会更新成功。结论：如果是更新，主键及version字段必须赋值
	 */
	@Test
	public void userAccount_version_save() {

		UserAccount userAccount = new UserAccount();
		userAccount.setAccountCode("luno_1101");
		userAccount.setAmount(BigDecimal.ZERO);
//		userAccount.setId(1L);
//		userAccount.setVersion(6L);
		userAccountService.save(userAccount);
		logger.info(userAccount.toString());
	}

	@Test
	public void userAccount_version_update() {

		UserAccount userAccount = userAccountService.findById(1L);
		if (userAccount != null) {
			userAccount.setAmount(userAccount.getAmount().add(BigDecimal.ONE));
			userAccountService.save(userAccount);
			logger.info(userAccount.toString());
		}
	}

	/**
	 * 更新UserAccount表字段
	 */
	@Test
	public void userAccount_customer_get_method() {

		String accountCode = "luno_0001";
		UserAccount userAccount = userAccountService.getByAccountCode(accountCode);

		userAccount.setAmount(userAccount.getAmount().add(BigDecimal.ONE));
		userAccountService.save(userAccount);
		logger.info(userAccount.toString());
	}


	/**
	 * 测试 oneToOne
	 */
	@Test
	public void userAccount_userInfo_oneToOne() {

		UserInfo userInfo = new UserInfo() ;
		userInfo.setJobNumber("1009");
		userInfo.setName("joo");
		userInfo.setCreateTime(new Date());
		userInfo.setCreateTime(new Date());
		userInfoService.save(userInfo);

		UserAccount userAccount = new UserAccount();
		String accountCode = "luno_000" + userInfo.getId();
		userAccount.setAccountCode(accountCode);
		userAccount.setAmount(BigDecimal.ZERO);
//		userAccount.setId(1L);
//		userAccount.setVersion(6L);
		userAccount.setUserInfo(userInfo);

		userAccountService.save(userAccount);

		UserAccount userAccountBO = userAccountService.getByAccountCode(accountCode);

		userAccountBO.setAmount(userAccountBO.getAmount().add(BigDecimal.ONE));
		userAccountService.save(userAccountBO);
		logger.info(userAccountBO.toString());
	}

	@Test
	public void getUserInfo_of_userAccount() {

		UserInfo userInfo = userInfoService.findById(1L);
		UserAccount userAccount = userInfo.getUserAccount();
		logger.info("员工工号为：{} , 账户code:{} ,余额：{}", userInfo.getJobNumber() ,
				userAccount.getAccountCode(), userAccount.getAmount() );
	}

	@Test
	public void getUserAccount_of_userInfo() {

		UserAccount userAccount = userAccountService.findById(1L);
		UserInfo userInfo = userAccount.getUserInfo();
		logger.info("员工工号为：{} , 账户code:{} ,余额：{}", userInfo.getJobNumber() ,
				userAccount.getAccountCode(), userAccount.getAmount() );
	}

	@Test
	public void getUserAccountLog_of_userAccount(){

		UserInfo userInfo = new UserInfo() ;
		UserAccount userAccount = new UserAccount();
		beforeInitDate(userInfo,userAccount);

		UserAccountLog userAccountLog = new UserAccountLog();
		userAccountLog.setUserAccount(userAccount);
		userAccountLog.setOperateAmount(BigDecimal.ONE);
		userAccountLog.setBeforeAmount(userAccount.getAmount());
		userAccountLog.setAfterAmount(userAccount.getAmount().add(BigDecimal.ONE));
		userAccountLog.setCreateTime(new Date());
		userAccountLogRepository.save(userAccountLog);

		logger.info(userAccountLog.toString());

	}

	@Test
	public void getUserAccount_of_userAccountLog() {

		UserAccount userAccount = userAccountService.getByAccountCode("luno_0001");

		logger.info("log size : " + userAccount.getUserAccountLogList().size());

	}

	/**
	 * 初始化数据 UserInfo UserAccount
	 */
	private void beforeInitDate(UserInfo userInfo,UserAccount userAccount) {

		userInfo.setJobNumber("1009");
		userInfo.setName("joo");
		userInfo.setCreateTime(new Date());
		userInfo.setCreateTime(new Date());
		userInfoService.save(userInfo);

		String accountCode = "luno_000" + userInfo.getId();
		userAccount.setAccountCode(accountCode);
		userAccount.setAmount(BigDecimal.ZERO);
//		userAccount.setId(1L);
//		userAccount.setVersion(6L);
		userAccount.setUserInfo(userInfo);

		userAccountService.save(userAccount);
	}
}
