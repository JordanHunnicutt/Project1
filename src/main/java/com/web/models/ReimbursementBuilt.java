package com.web.models;

import java.sql.Timestamp;

public class ReimbursementBuilt {

	private int reimbursementId;
	private double amount;
	private Timestamp submitDate;
	private Timestamp resolveDate;
	private String description;
	private byte[] receipt;
	private UserBuilt author;
	private UserBuilt resolver;
	private ReimbursementStatus status;
	private ReimbursementType type;
	
	public int getReimbursementId() {
		return reimbursementId;
	}
	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Timestamp getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Timestamp submitDate) {
		this.submitDate = submitDate;
	}
	public Timestamp getResolveDate() {
		return resolveDate;
	}
	public void setResolveDate(Timestamp resolveDate) {
		this.resolveDate = resolveDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public byte[] getReceipt() {
		return receipt;
	}
	public void setReceipt(byte[] receipt) {
		this.receipt = receipt;
	}
	public UserBuilt getAuthor() {
		return author;
	}
	public void setAuthor(UserBuilt author) {
		this.author = author;
	}
	public UserBuilt getResolver() {
		return resolver;
	}
	public void setResolver(UserBuilt resolver) {
		this.resolver = resolver;
	}
	public ReimbursementStatus getStatus() {
		return status;
	}
	public void setStatus(ReimbursementStatus status) {
		this.status = status;
	}
	public ReimbursementType getType() {
		return type;
	}
	public void setType(ReimbursementType type) {
		this.type = type;
	}
	
	public ReimbursementBuilt(int reimbursementId, double amount, Timestamp submitDate, Timestamp resolveDate,
			String description, byte[] receipt, UserBuilt author, UserBuilt resolver, ReimbursementStatus status,
			ReimbursementType type) {
		super();
		this.reimbursementId = reimbursementId;
		this.amount = amount;
		this.submitDate = submitDate;
		this.resolveDate = resolveDate;
		this.description = description;
		this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}
	
	public ReimbursementBuilt(double amount, Timestamp submitDate, Timestamp resolveDate, String description,
			byte[] receipt, UserBuilt author, UserBuilt resolver, ReimbursementStatus status, ReimbursementType type) {
		super();
		this.amount = amount;
		this.submitDate = submitDate;
		this.resolveDate = resolveDate;
		this.description = description;
		this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}
	
	public ReimbursementBuilt() {
		super();
	}
	
	
	
}
