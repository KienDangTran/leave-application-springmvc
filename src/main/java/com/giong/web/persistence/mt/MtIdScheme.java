package com.giong.web.persistence.mt;

import com.giong.constant.MasterDataStatus;
import com.giong.web.persistence.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "MT_ID_SCHEME")
@NamedQuery(name = "MtIdScheme.findAll", query = "SELECT m FROM MtIdScheme m")
public class MtIdScheme extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "seq_no")
	private int seq_no;

	@Column(name = "scheme_name")
	private String schemeName;

	@Column(name = "prefix")
	private String prefix;

	@Column(name = "suffix")
	private String suffix;

	@Column(name = "length")
	private int length = 10;

	@Column(name = "filled_char")
	private String filledChar = "0";

	@Column(name = "last_gen_no")
	private long lastGenNo = 0;

	@Column(name = "status")
	private String status = MasterDataStatus.ACTIVE;

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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
