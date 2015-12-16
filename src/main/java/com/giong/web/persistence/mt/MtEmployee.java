package com.giong.web.persistence.mt;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.giong.web.persistence.AbstractEntity;


/**
 * The persistent class for the mt_employee database table.
 * 
 */
@Entity
@Table(name = "MT_EMPLOYEE")
@NamedQuery(name = "MtEmployee.findAll", query = "SELECT m FROM MtEmployee m")
public class MtEmployee extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "EMPLOYEE_CODE")
	private String employeeCode;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_OF_BIRTH")
	private Date dateOfBirth;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "EMPLOYEE_NAME")
	private String employeeName;
	
	@Column(name = "PHONE_NO")
	private String phoneNo;
	
	private String status;
	
	public MtEmployee() {
	}
	
	@Override
	public Object getPk() {
		return this.employeeCode;
	}
	
	public String getEmployeeCode() {
		return this.employeeCode;
	}
	
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmployeeName() {
		return this.employeeName;
	}
	
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	public String getPhoneNo() {
		return this.phoneNo;
	}
	
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
}