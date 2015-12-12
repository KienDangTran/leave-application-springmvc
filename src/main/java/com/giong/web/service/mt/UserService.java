package com.giong.web.service.mt;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giong.web.persistence.mt.MtUser;
import com.giong.web.repository.mt.UserRepository;
import com.giong.web.service.BaseService;

@Transactional
@Service("userDetailsService")
public class UserService extends BaseService<MtUser, String, UserRepository> implements UserDetailsService {
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final List<MtUser> users = this.repository.loadUserByUsername(username);
		final MtUser user = users.isEmpty() ? null : users.get(0);
		if (user == null) throw new UsernameNotFoundException("Username Is Not Found");
		return user;
	}
	
}
