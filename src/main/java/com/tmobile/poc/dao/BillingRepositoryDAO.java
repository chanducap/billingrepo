package com.tmobile.poc.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tmobile.poc.vo.BillingAccountSummaryVO;

public interface BillingRepositoryDAO extends CrudRepository<BillingAccountSummaryVO, Integer> {

	@Transactional
	@Query(value = "Select b from BillingAccountSummaryVO b where b.customerId=?")
	BillingAccountSummaryVO findByCustomerId(@Param("customerId") Integer customerId);

	/*@Transactional
	@Query("Update  BillingAccountSummaryVO v set  v.customerId = :customerId ")

	BillingAccountSummaryVO updateCustomerIdforAccount(@Param("customerId") Integer customerId);
*/
}