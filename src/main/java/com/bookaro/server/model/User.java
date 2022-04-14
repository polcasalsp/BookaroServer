package com.bookaro.server.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
@Entity
@Table(name = "userbookaro")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements UserDetails {

	@Id
	@GeneratedValue
	@Column(name="user_id")
	private Long id;
	
	@Column(name="user_name")
	private String username;
	
	@Column(name="user_passwd")
	private String password;
	
	@Column(name="user_email")
	private String email;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;

	@Column(name="user_role")
	private String role;

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		role = this.getRole();
		ArrayList<GrantedAuthority> authorities = new ArrayList<>();		
		if (role.equals("ADMIN")) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			authorities.add(new SimpleGrantedAuthority("CREATE"));
			authorities.add(new SimpleGrantedAuthority("READ"));
			authorities.add(new SimpleGrantedAuthority("UPDATE"));
			authorities.add(new SimpleGrantedAuthority("DELETE"));
		} else if (role.equals("EMPLOYEE")) {
			authorities.add(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));
			authorities.add(new SimpleGrantedAuthority("CREATE"));
			authorities.add(new SimpleGrantedAuthority("READ"));
		} else {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}		
		return authorities;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRoles(String role) {
		this.role = role;
	}

	public User(Long id, String username, String password, String email, String firstName, String lastName,
			String role) {		
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}

	public User(String username, String password, String role) {	
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public User() {		
	}
	
}
