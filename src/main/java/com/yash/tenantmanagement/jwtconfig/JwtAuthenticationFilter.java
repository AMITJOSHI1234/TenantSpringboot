package com.yash.tenantmanagement.jwtconfig;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
//@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "Authorization",allowCredentials = "true")
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtHelper jwtHelper;
	
	@Autowired
	private UserDetailService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String requestHeader = request.getHeader("Authorization");
		
		String username = null;
		String token = null;
		System.out.println("RequestHeader:"+requestHeader);
		if(requestHeader!=null && requestHeader.startsWith("Bearer")) {
			token = requestHeader.substring(7);
			System.out.println("Token:"+token);
			try {
				username = this.jwtHelper.getUsernameFromToken(token);
			}catch (IllegalArgumentException e) {
                logger.info("Illegal Argument while fetching the username !!");
                e.printStackTrace();
            } catch (ExpiredJwtException e) {
                logger.info("Given jwt token is expired !!");
                e.printStackTrace();
            } catch (MalformedJwtException e) {
                logger.info("Some changed has done in token !! Invalid Token");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

		}else {
			System.out.println("Invalid Header Value !!");
            //logger.info("Invalid Header Value !! ");
        }
		
		System.out.println("Username:"+username);
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			//fetch user details from username
			System.out.println("Fetching user Details!!!");
			System.out.println("USername in ashasj:"+username);
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
			if(validateToken) {
				
				//set the authentication
				UsernamePasswordAuthenticationToken authentication = 
						new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}else {
				logger.info("Validation fails !!");
			}
		}
	
		filterChain.doFilter(request, response);
	}
	
}
