package com.web.models;

import com.web.repository.ReimbursementDao;
import com.web.repository.UserDao;

public class ReimbursementBuilt {

	private static final UserDao ud = new UserDao();
	private static final ReimbursementDao rd = new ReimbursementDao();
	
	private int reimbursementId;
	private double amount;
	private String submitDate;
	private String resolveDate;
	private String description;
	private byte[] receipt;
	private String author;
	private String resolver;
	private String status;
	private String type;
	
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
	public String getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}
	public String getResolveDate() {
		return resolveDate;
	}
	public void setResolveDate(String resolveDate) {
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getResolver() {
		return resolver;
	}
	public void setResolver(String resolver) {
		this.resolver = resolver;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public ReimbursementBuilt(int reimbursementId, double amount, String submitDate, String resolveDate,
			String description, byte[] receipt, String author, String resolver, String status,
			String type) {
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
	
	public ReimbursementBuilt(double amount, String submitDate, String resolveDate, String description,
			byte[] receipt, String author, String resolver, String status, String type) {
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
	
	public ReimbursementBuilt(Reimbursement r) {
		super();
		
		this.amount = r.getAmount();
		this.reimbursementId = r.getReimbursementId();
		
		try {
			int subDay = r.getSubmitDate().getDay();
			int subMonth = r.getSubmitDate().getMonth();
			int subYear = r.getSubmitDate().getYear() + 1900;
			this.submitDate = subMonth+"/"+subDay+"/"+subYear;
		} catch (NullPointerException e) {
			this.submitDate = "None provided";
		}
		try {
			int resDay = r.getResolveDate().getDay();
			int resMonth = r.getResolveDate().getMonth();
			int resYear = r.getResolveDate().getYear();
			this.resolveDate = resMonth+"/"+resDay+"/"+resYear;
		} catch (NullPointerException e) {
			this.resolveDate = "None provided";
		}
		
		
		this.description = r.getDescription();
		this.receipt = r.getReceipt();
		
		try {
			this.author = ud.findById(r.getAuthorId()).getUsername();
		} catch (NullPointerException e) {
			this.author = "None provided";
		}
		try {
			this.resolver = ud.findById(r.getAuthorId()).getUsername();
		} catch (NullPointerException e) {
			this.resolver = "None provided";
		}
		try {
			this.status = rd.findStatusById(r.getStatusId()).getStatus();
		} catch (NullPointerException e) {
			this.status = "None provided";
		}
		try {
			this.type = rd.findTypeById(r.getTypeId()).getType();
		} catch (NullPointerException e) {
			this.type = "None provided";
		}
		
	}
	
	public ReimbursementBuilt() {
		super();
	}
	
	
	
}
