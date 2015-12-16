package com.giong.web.persistence.mt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.giong.web.persistence.AbstractEntity;

@Entity
@Table(name = "MT_ID_SCHEME")
@NamedQuery(name = "MtIdScheme.findAll", query = "SELECT m FROM MtIdScheme m")
public class MtIdScheme extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "SEQ_NO")
	private int seq_no;
	
	@Column(name = "SCHEME_NAME")
	private String schemeName;
	
	@Column(name = "PREFIX")
	private String prefix;
	
	@Column(name = "SUFFIX")
	private String suffix;
	
	@Column(name = "LENGTH")
	private int length = 10;
	
	@Column(name = "FILLED_CHAR")
	private String filledChar = "0";
	
	@Column(name = "LAST_GEN_NO")
	private long lastGenNo = 0;
	
	/* CONTRUCTORS */
	public MtIdScheme() {
	}
	
	@Override
	public Object getPk() {
		return this.seq_no;
	}
	
	public int getSeq_no() {
		return this.seq_no;
	}
	
	public String getSchemeName() {
		return this.schemeName;
	}
	
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	
	public String getPrefix() {
		return this.prefix;
	}
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public String getSuffix() {
		return this.suffix;
	}
	
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	public int getLength() {
		return this.length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
	public String getFilledChar() {
		return this.filledChar;
	}
	
	public void setFilledChar(String filledChar) {
		this.filledChar = filledChar;
	}
	
	public long getLastGenNo() {
		return this.lastGenNo;
	}
	
	public void setLastGenNo(long lastGenNo) {
		this.lastGenNo = lastGenNo;
	}
	
}
