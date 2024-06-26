package com.contold.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.contold.entity.User;

public class AppUserDetails implements UserDetails {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;

	    public AppUserDetails(User user) {
	        this.user = user;
	    }

	    public Long getId() {
	        return user.getId();
	    }

	    @Override
	    public String getUsername() {
	        return user.getUsername();
	    }

	    @Override
	    public String getPassword() {
	        return user.getPassword();
	    }

	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {

	        return null;
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isEnabled() {
	        return true;
	    }
}
