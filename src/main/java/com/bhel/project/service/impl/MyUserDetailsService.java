package com.bhel.project.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bhel.project.entityImpl.BhelLogin;
import com.bhel.project.jpa.repo.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

		BhelLogin user = userRepository.findById(s);

		if (user!=null) {
			//List<SimpleGrantedAuthority> authorities = new ArrayList<>();
			/*
			 * Arrays.asList(user.get().getRole().split(",").length.forEach(authority -> {
			 * authorities.add(new SimpleGrantedAuthority(authority)); }))
			 */;
		} else {

			throw new UsernameNotFoundException("User" + s + "does not exist");
		}
		return new User(user.getEmail_id(), user.getPassword(),new ArrayList<>());

		/*
		 * user.orElseThrow(() -> new UsernameNotFoundException("Not Found" + email));
		 * return new org.springframework.security.core.userdetails.User(
		 * existingUser.getEmail(), existingUser.getPassword(), new ArrayList<>());
		 */
		/* return user.map(MyUserDetails:: new).get(); */

		/*
		 * User existingUser = userRepository.findByEmail(email).orElseThrow(() -> new
		 * UsernameNotFoundException("User not found"));
		 * 
		 * return new org.springframework.security.core.userdetails.User(
		 * existingUser.getEmail(), existingUser.getPassword(), new ArrayList<>());
		 */
		/*
		 * return new User("foo", new BCryptPasswordEncoder().encode("foo"), new
		 * ArrayList<>());
		 */
       }
	
	
}
