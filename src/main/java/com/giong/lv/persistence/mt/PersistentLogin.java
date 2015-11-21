package com.giong.lv.persistence.mt;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.giong.lv.persistence.AbstractEntity;


/**
 * The persistent class for the persistent_logins database table.
 * 
 */
@Entity
@Table(name = "persistent_logins")
@NamedQuery(name = "PersistentLogin.findAll", query = "SELECT p FROM PersistentLogin p")
public class PersistentLogin extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String series;
	
	@Column(name = "LAST_USED")
	private Timestamp lastUsed;
	
	private String token;
	
	private String username;
	
	public PersistentLogin() {
	}
	
	public String getSeries() {
		return this.series;
	}
	
	public void setSeries(String series) {
		this.series = series;
	}
	
	public Timestamp getLastUsed() {
		return this.lastUsed;
	}
	
	public void setLastUsed(Timestamp lastUsed) {
		this.lastUsed = lastUsed;
	}
	
	public String getToken() {
		return this.token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
}