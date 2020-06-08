package com.security.jwt.demo.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.jwt.demo.repo.UserRepository;
import com.security.jwt.demo.security.model.JwtUserDetails;
import com.security.jwt.demo.security.model.User;



@Service
public class JwtUserDetailsService implements UserDetailsService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		logger.info("Start of loadUserByUsername");
		logger.debug("username is {} ", username);
		User user = userRepo.findByEmail(username);

		if (user == null) {
			logger.warn("user with username : {} not found ", username);
			logger.info("End of loadUserByUsername");

			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));

		}
		logger.info("End of loadUserByUsername {} ", user.getRoles().iterator().next().getName());

		return new JwtUserDetails(user.getId(), user.getEmail(), user.getPassword(),
				user.getRoles().iterator().next().getName());
	}

}
