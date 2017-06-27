package com.tmobile.poc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tmobile.poc.common.IConstants;
import com.tmobile.poc.dao.BillingRepositoryDAO;
import com.tmobile.poc.vo.BillingAccountSummaryVO;
import com.tmobile.poc.vo.CustomerVO;

@Service
public class BillingAccountService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${customerInfo.uri}")
	private String customerInfoUri;

	@Autowired
	private BillingRepositoryDAO billRepo;

	/**
	 * Method is used for getting the Billing Account Balances.
	 * 
	 * @param phoneNumber
	 * @return
	 */
	public BillingAccountSummaryVO getAccountBalance(String phoneNumber) {

		// CustomerVO customer =cusRepo.findOne(phoneNumber);
		// // CustomerVO customer = customerMap.get(phoneNumber);
		// if (customer == null) {
		// customer = getCustomerInfo(phoneNumber);
		// }
		//// return accountMap.get(customer.getCustomerId());
		//
		// return cusRepo.findOne(customer.getCustomerId());
		CustomerVO customer = null;
		customer = restTemplate.getForObject(customerInfoUri + phoneNumber, CustomerVO.class);
		if (customer == null) {
			customer = getCustomerInfo(phoneNumber);
		}
		return billRepo.findOne(Integer.parseInt(phoneNumber));

	}

	private CustomerVO getCustomerInfo(String phoneNumber) {
		CustomerVO customer = null;
		BillingAccountSummaryVO account = null;
		try {
			customer = restTemplate.getForObject(customerInfoUri + phoneNumber, CustomerVO.class);

			account = billRepo.findOne(Integer.parseInt(phoneNumber));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return customer;
	}

	private void updateAccountMap(CustomerVO customer, BillingAccountSummaryVO account) {
		if (account == null) {
			account = new BillingAccountSummaryVO();
			// account.setAcctId(accountMap.size() + 1);
			account.setCustomerId(customer.getCustomerId());
			account.setPhoneNumber(customer.getPhoneNumber());
			account.setCurrentBal(0.00);
			// account.setStatementBalance(0.00);
			account.setUnbilledCredits(0.00);
			account.setUnbilledDebits(0.00);
			account.setUnbilledPayments(0.00);
		}
		// accountMap.put(account.getCustomerId(), account);
		billRepo.save(account);

	}

	/**
	 * Method used to update the Balances by Customer Id.
	 * 
	 * @param customerId
	 * @param transAmt
	 * @param transType
	 * @return
	 */

	public BillingAccountSummaryVO updateBalances(Integer customerId, Double transAmt, Integer transType) {
		// BillingAccountSummaryVO account = accountMap.get(customerId);
		BillingAccountSummaryVO account = billRepo.findOne(customerId);
		if (account != null) {
			switch (transType) {
			case IConstants.DEBIT_ADJUSTMENT:
				account.setUnbilledDebits(account.getUnbilledDebits() + transAmt);
				account.setCurrentBal(account.getCurrentBal() + transAmt);
				break;
			case IConstants.CREDIT_ADJUSTMENT:
				account.setUnbilledCredits(account.getUnbilledCredits() + transAmt);
				account.setCurrentBal(account.getCurrentBal() - transAmt);
				break;
			case IConstants.PAYMENT:
				account.setUnbilledCredits(account.getUnbilledPayments() + transAmt);
				account.setCurrentBal(account.getCurrentBal() - transAmt);
				break;
			}
			// accountMap.put(customerId, account);
			billRepo.save(account);
		}
		return account;

	}

}
