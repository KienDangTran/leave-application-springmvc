package com.giong.web.service;

import java.io.Serializable;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseService<T, ID extends Serializable, R extends JpaRepository<T, ID>> implements GenericService<T, ID> {
	
	protected final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	protected R repository;
	
	@Resource
	public void setRepository(R repository) {
		this.repository = repository;
	}
}
