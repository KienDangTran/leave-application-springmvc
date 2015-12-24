package com.giong.web.persistence;

import java.io.Serializable;
import java.lang.reflect.Field;

import javax.persistence.Transient;

public abstract class AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public AbstractEntity(AbtractPk pk) {
	}
	
	public AbstractEntity() {
	}
	
	@Transient
	protected boolean selected;
	
	public boolean isSelected() {
		return this.selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public abstract Object getPk();
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
		sb.append("@").append(this.hashCode()).append("[").append(this.getPk().toString()).append("]");
		return sb.toString();
	}
	
	
	/**
	 * 
	 */
	public abstract class AbtractPk implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			Field f;
			final Object value = null;
			for (int i = 0; i < this.getClass().getFields().length; i++) {
				f = this.getClass().getFields()[i];
				sb.append(f.getName()).append("=");
				try {
					sb.append(f.get(value).toString());
				}
				catch (final Exception e) {
					sb.append("n/a");
				}
			}
			return sb.toString();
		}
	}
}
