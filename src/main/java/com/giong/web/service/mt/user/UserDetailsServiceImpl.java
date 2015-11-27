package com.giong.web.service.mt.user;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giong.web.persistence.mt.MtUser;
import com.giong.web.repository.mt.UserRepository;

@Service("userDetailsService")
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Resource
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final TypedQuery<MtUser> query = this.em.createQuery("SELECT e FROM MtUser e WHERE e.username = :username", MtUser.class).setParameter("username", username);
		final List<MtUser> users = query.getResultList();
		final MtUser user = users.isEmpty() ? null : users.get(0);
		if (user == null) throw new UsernameNotFoundException("Username Is Not Found");
		return user;
	}
	
}
