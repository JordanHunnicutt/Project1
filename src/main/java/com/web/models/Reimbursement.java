package com.web.models;

import java.io.File;
import java.sql.Timestamp;

public class Reimbursement {

	private int reimbursementId;
	private double amount;
	private Timestamp submitDate;
	private Timestamp resolveDate;
	private String description;
	private byte[] receipt;
	private int authorId;
	private int resolverId;
	private int statusId;
	private int typeId;
	
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
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public int getResolverId() {
		return resolverId;
	}
	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	
	public Reimbursement(int reimbursementId, double amount, Timestamp submitDate, Timestamp resolveDate,
			String description, byte[] receipt, int authorId, int resolverId, int statusId, int typeId) {
		super();
		this.reimbursementId = reimbursementId;
		this.amount = amount;
		this.submitDate = submitDate;
		this.resolveDate = resolveDate;
		this.description = description;
		this.receipt = receipt;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.statusId = statusId;
		this.typeId = typeId;
	}
	
	public Reimbursement(double amount, Timestamp submitDate, Timestamp resolveDate,
			String description, byte[] receipt, int authorId, int resolverId, int statusId, int typeId) {
		super();
		this.amount = amount;
		this.submitDate = submitDate;
		this.resolveDate = resolveDate;
		this.description = description;
		this.receipt = receipt;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.statusId = statusId;
		this.typeId = typeId;
	}
	
	public Reimbursement() {
		super();
	}
	
}
