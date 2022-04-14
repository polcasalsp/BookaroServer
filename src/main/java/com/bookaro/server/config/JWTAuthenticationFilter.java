package com.bookaro.server.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bookaro.server.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        
        setFilterProcessesUrl("/api/services/controller/user/login"); 
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
    											HttpServletResponse res)
    											throws AuthenticationException {
        try {
            User creds = new ObjectMapper()
            		.readValue(req.getInputStream(), User.class);
            
            return authenticationManager
            		.authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword()));
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {
    	    	
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		ArrayList<String> roles = new ArrayList<String>();
		for(GrantedAuthority authority:authorities) {
			roles.add(authority.toString());
		}
    	String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())    
                .withClaim("authorities", roles)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));

        res.getWriter().write(token);
        res.getWriter().flush();
    }
}
