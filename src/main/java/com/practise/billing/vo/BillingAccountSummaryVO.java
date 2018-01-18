package com.practise.billing.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Component
@Entity
@Table(name = "billing_account")
@JsonInclude(value = Include.NON_NULL)
public class BillingAccountSummaryVO extends CustomerAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "acctId")
	private Integer acctId;
	@Column(name = "customerId")
	private Integer customerId;
	@Column(name = "currentBal")
	private Double currentBal;
	@Column(name = "lastStmtBal")
	private Double lastStmtBal;
	@Column(name = "unbilledDebits")
	private Double unbilledDebits;
	@Column(name = "unbilledCredits")
	private Double unbilledCredits;
	@Column(name = "unbilledPayments")
	private Double unbilledPayments;

	public BillingAccountSummaryVO() {

	}

	/**
	 * @param acctId
	 * @param currentBal
	 * @param lastStmtBal
	 * @param unbilledDebits
	 * @param unbilledCredits
	 * @param unbilledPayments
	 */

	public BillingAccountSummaryVO(Integer acctId, Integer customerId, Double currentBal, Double lastStmtBal,
			Double unbilledDebits, Double unbilledCredits, Double unbilledPayments) {
		super();
		this.acctId = acctId;
		this.customerId = customerId;
		this.currentBal = currentBal;
		this.lastStmtBal = lastStmtBal;
		this.unbilledDebits = unbilledDebits;
		this.unbilledCredits = unbilledCredits;
		this.unbilledPayments = unbilledPayments;
	}

	public Integer getAcctId() {
		return acctId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public void setAcctId(Integer acctId) {
		this.acctId = acctId;
	}

	public Double getCurrentBal() {
		return currentBal;
	}

	public void setCurrentBal(Double currentBal) {
		this.currentBal = currentBal;
	}

	public Double getLastStmtBal() {
		return lastStmtBal;
	}

	public void setLastStmtBal(Double lastStmtBal) {
		this.lastStmtBal = lastStmtBal;
	}

	public Double getUnbilledDebits() {
		return unbilledDebits;
	}

	public void setUnbilledDebits(Double unbilledDebits) {
		this.unbilledDebits = unbilledDebits;
	}

	public Double getUnbilledCredits() {
		return unbilledCredits;
	}

	public void setUnbilledCredits(Double unbilledCredits) {
		this.unbilledCredits = unbilledCredits;
	}

	public Double getUnbilledPayments() {
		return unbilledPayments;
	}

	public void setUnbilledPayments(Double unbilledPayments) {
		this.unbilledPayments = unbilledPayments;
	}

	@Override
	public String toString() {
		return "BillingAccountSummaryVO [acctId=" + acctId + ", customerId=" + customerId + ", currentBal=" + currentBal
				+ ", lastStmtBal=" + lastStmtBal + ", unbilledDebits=" + unbilledDebits + ", unbilledCredits="
				+ unbilledCredits + ", unbilledPayments=" + unbilledPayments + "]";
	}

}
