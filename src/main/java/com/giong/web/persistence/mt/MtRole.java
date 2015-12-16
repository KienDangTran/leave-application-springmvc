package com.giong.web.persistence.mt;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.giong.web.persistence.AbstractEntity;


/**
 * The persistent class for the mt_role database table.
 * 
 */
@Entity
@Table(name = "MT_ROLE")
@NamedQuery(name = "MtRole.findAll", query = "SELECT m FROM MtRole m")
public class MtRole extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ROLE_CODE")
	private String roleCode;
	
	@Column(name = "ROLE_DESC")
	private String roleDesc;
	
	private String status;
	
	//bi-directional many-to-many association to MtPermission
	@ManyToMany(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinTable(name = "mt_role_permission_granted", joinColumns = { @JoinColumn(name = "ROLE_CODE") }, inverseJoinColumns = { @JoinColumn(name = "PERMISSION_CODE") })
	private List<MtPermission> mtPermissions;
	
	//bi-directional many-to-many association to MtUser
	@ManyToMany(mappedBy = "mtRoles")
	private List<MtUser> mtUsers;
	
	public MtRole() {
	}
	
	@Override
	public Object getPk() {
		return this.getRoleCode();
	}
	
	public String getRoleCode() {
		return this.roleCode;
	}
	
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	public String getRoleDesc() {
		return this.roleDesc;
	}
	
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<MtPermission> getMtPermissions() {
		return this.mtPermissions;
	}
	
	public void setMtPermissions(List<MtPermission> mtPermissions) {
		this.mtPermissions = mtPermissions;
	}
	
	public List<MtUser> getMtUsers() {
		return this.mtUsers;
	}
	
	public void setMtUsers(List<MtUser> mtUsers) {
		this.mtUsers = mtUsers;
	}
	
}