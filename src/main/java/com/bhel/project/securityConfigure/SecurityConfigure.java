package com.bhel.project.securityConfigure;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.bhel.project.service.impl.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter {
	@Autowired
	MyUserDetailsService testUserDetailsService;

	@Autowired
	DataSource dataSource;

	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(testUserDetailsService).passwordEncoder(passwordEncoder());

	}

//	antMatchers("/admin").hasRole("ADMIN")
//	  .antMatchers("user").hasAnyRole("ADMIN","USER")

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/static/**","/images/**","/css/**","/js/**","/css/customCss/**")
		.permitAll().antMatchers("/login", "/forget", "/changePassword", "/sendToMail","/resetPassword/{email_id}/{id}/{expirationTime}").permitAll().anyRequest()
				.authenticated().and().formLogin().loginPage("/signin").loginProcessingUrl("/userlogin")
				.defaultSuccessUrl("/projects").usernameParameter("email").passwordParameter("password")
				.failureUrl("/signin?error=true").successForwardUrl("/dashboard").permitAll().and().csrf().disable()
				.formLogin().loginPage("/signin").permitAll().and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/bhelLogin").deleteCookies("remember-me")
				.invalidateHttpSession(false)
				.and().rememberMe()
				.rememberMeCookieName("ducat-remember-me")
				.rememberMeParameter("remember-me-new")
				.key("uniqueAndSecret")
				.tokenValiditySeconds(86400);
		        http.sessionManagement()
		        .invalidSessionUrl("/session/error/invalid")
		        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS).invalidSessionUrl("/signin")
				.maximumSessions(1).maxSessionsPreventsLogin(true).expiredUrl("/bhelLogin?invalid-session=true");
		    }
		
	
	 @Bean
	    public PersistentTokenRepository persistentTokenRepository() {
	        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
	        tokenRepository.setDataSource(dataSource);
	        return tokenRepository;
	    }

	@Bean
	public SessionRegistry sessionRegistry() {
		SessionRegistry sessionRegistry = new SessionRegistryImpl();
		return sessionRegistry;
	}

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

	@Bean
	public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {

		SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
		auth.setTargetUrlParameter("targetUrl");
		return auth;
	}

	// We can control exactly when our session gets created and how Spring Security
	// will interact with it:
	// ifRequired – A session will be created only if required (default).
	/*
	 * @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws
	 * Exception { http.sessionManagement()
	 * .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED); return
	 * http.build(); }
	 */

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userDetailsService());
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}

	/*
	 * invalidate the session on logout. To achieve this, you can add the
	 * WebSessionServerLogoutHandler to your logout configuration,
	 * 
	 * @Bean SecurityWebFilterChain http(ServerHttpSecurity http) throws Exception {
	 * DelegatingServerLogoutHandler logoutHandler = new
	 * DelegatingServerLogoutHandler( new WebSessionServerLogoutHandler(), new
	 * SecurityContextServerLogoutHandler() );
	 * 
	 * http .authorizeExchange((exchange) -> exchange.anyExchange().authenticated())
	 * .logout((logout) -> logout.logoutHandler(logoutHandler));
	 * 
	 * return http.build(); }
	 */

}
