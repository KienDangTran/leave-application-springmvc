package com.giong.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final int TOKEN_VALIDITY_SECONDS = 60 * 60 * 24;
	private final String APPLICATION_SECURITY_KEY = "giong.security-key";
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsService).passwordEncoder(this.passwordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		
		// @formatter:off
		http
			.csrf().disable().httpBasic()
			.and()
				.headers().cacheControl().and()
			.and()
				.sessionManagement().sessionFixation().none()
			.and()
				.authorizeRequests().anyRequest().authenticated()
			.and()
				.formLogin().loginProcessingUrl("/login")
							.permitAll()
			.and()
				.logout().invalidateHttpSession(true)
						 .deleteCookies("JSESSIONID", AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY)
			.and()
				.rememberMe().rememberMeServices(this.rememberMeServices())
//			.and()
//				.exceptionHandling().accessDeniedPage("/error/403.xhtml")
		;		
		// @formatter:on
	}
	
	@Override
	@Bean(name = "authenticationManager")
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordEncoder() {
		final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder;
	}
	
	@Bean(name = "rememberMeServices")
	public RememberMeServices rememberMeServices() {
		
		final JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setDataSource(this.dataSource);
		
		final PersistentTokenBasedRememberMeServices rememberMeServices = new PersistentTokenBasedRememberMeServices(this.APPLICATION_SECURITY_KEY, this.userDetailsService, jdbcTokenRepository);
		rememberMeServices.setTokenValiditySeconds(this.TOKEN_VALIDITY_SECONDS);
		rememberMeServices.setAlwaysRemember(false);
		
		return rememberMeServices;
	}
	
}

/*                         														   _
														  _._ _..._ .-',     _.._(`))
														 '-. `     '  /-._.-'    ',/
														    )         \            '.
														   / _    _    |             \
														  |  a    a    /              |
														  \   .-.                     ;  
														   '-('' ).-'       ,'       ;
														      '-;           |      .'
														         \           \    /
														         | 7  .__  _.-\   \
														         | |  |  ``/  /`  /
														        /,_|  |   /,_/   /
														           /,_/      '`-'
*/
