package com.bootcamp.security.service;

import com.bootcamp.security.dto.AuthResponse;
import com.bootcamp.security.dto.AuthenticationRequest;
import com.bootcamp.security.dto.RegisterRequest;

public interface AuthService {
	AuthResponse register(RegisterRequest request);
	
	AuthResponse authenticate(AuthenticationRequest request);

}
