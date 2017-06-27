package com.tmobile.poc.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tmobile.poc.vo.BillingAccountSummaryVO;

public interface BillingRepositoryDAO extends CrudRepository<BillingAccountSummaryVO, Integer> {
	@Modifying
	@Transactional
	@Query(value = "update BillingCustomerVO b set b.status =:status where b.accountId=:accountId")
	void deleteByaccountId(@Param("accountId") Integer accountId, @Param("status") int status);
	
/*	@Modifying
	@Transactional
	@Query(value = "select * from BillingCustomerVO b where b.phoneNumber=:phoneNumber")
	void findByphoneNumber(@Param("phoneNumber") Integer phoneNumber);
*/
}