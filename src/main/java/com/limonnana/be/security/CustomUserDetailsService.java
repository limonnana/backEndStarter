package com.limonnana.be.security;

import java.util.ArrayList;
import java.util.Collection;

import com.limonnana.be.entity.Admin;
import com.limonnana.be.repository.AdminRepository;
import com.limonnana.be.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.limonnana.be.entity.UserType;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
	private UserType userType;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(userType);
		if(userType==UserType.ADMIN) {

			Admin adminEntity = adminRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Admin Username "+ username+ "not found"));

			SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority(UserType.ADMIN.toString());
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(adminAuthority);
			return new User(adminEntity.getUsername(), adminEntity.getPassword(), authorities);
		} else if(userType == UserType.USER) {
			com.limonnana.be.entity.User user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User Email "+ username+ "not found"));

			SimpleGrantedAuthority userAuthority = new SimpleGrantedAuthority(UserType.USER.toString());
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(userAuthority);
			return new com.limonnana.be.entity.User(user.getUsername(), user.getPassword(), authorities);
		} else if(userType == UserType.STUDENT) {
			StudentEntity studentEntity = studentRepo.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Student Email "+ username+ "not found"));

			SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority(UserType.STUDENT.toString());
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(adminAuthority);
			return new User(studentEntity.getEmail(), studentEntity.getPassword(), authorities);
		}
		return null;
	}

}
