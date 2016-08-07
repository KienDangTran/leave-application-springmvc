package com.giong.web.persistence.mt;

import com.giong.constant.MasterDataStatus;
import com.giong.web.persistence.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the MT_EMPLOYEE database table.
 */
@Entity
@Table(name = "MT_EMPLOYEE")
@NamedQuery(name = "MtEmployee.findAll", query = "SELECT m FROM MtEmployee m")
public class MtEmployee extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "employee_code")
	private String employeeCode;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_birth")
	private Date dateOfBirth;

	@Column(name = "sex")
	private String sex;

	@Column(name = "email")
	private String email;

	@Column(name = "employee_name")
	private String employeeName;

	@Column(name = "phone_no")
	private String phoneNo;

	@Column(name = "status")
	private String status = MasterDataStatus.ACTIVE;

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

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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