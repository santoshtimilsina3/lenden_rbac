package com.lenden.rbac.controller;

import com.lenden.rbac.dtos.SignupDto;
import com.lenden.rbac.enums.Permission;
import com.lenden.rbac.enums.RoleName;
import com.lenden.rbac.models.Authorities;
import com.lenden.rbac.models.Role;
import com.lenden.rbac.models.Token;
import com.lenden.rbac.models.User;
import com.lenden.rbac.repository.AuthoritiesRepository;
import com.lenden.rbac.repository.RoleRepository;
import com.lenden.rbac.repository.TokenRepository;
import com.lenden.rbac.repository.UserRepository;
import com.lenden.rbac.security.JwtService;
import com.lenden.rbac.token.TokenType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;
 @Autowired
 AuthoritiesRepository authoritiesRepository;

    @Autowired
    private JwtService jaJwtService;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(Authentication authentication) {
        System.out.println("Login success full " + authentication.getName());
        return new ResponseEntity<>(authentication.getName(),HttpStatus.OK);
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
//
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		User userDetails = (User) authentication.getPrincipal();
//        String jwt = jaJwtService.generateToken(userDetails);
//		List<String> roles = userDetails.getAuthorities().stream()
//				.map(item -> item.getAuthority())
//				.collect(Collectors.toList());
//		return ResponseEntity.ok(new JwtResponse(jwt,
//												 userDetails.getId(),
//												 userDetails.getEmail()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupDto signUpDto) {

        // add check for email exists in DB
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }


        Authorities readAuthority = new Authorities();
        readAuthority.setPermission(Permission.ADMIN_READ);
        authoritiesRepository.save(readAuthority);

        Authorities updateAuthority = new Authorities();
        updateAuthority.setPermission(Permission.ADMIN_DELETE);
        authoritiesRepository.save(updateAuthority);


        Role adminRole = new Role(RoleName.ROLE_ADMIN);
        adminRole.setAuthorities(List.of(readAuthority,updateAuthority));
        roleRepository.save(adminRole);


        // create user object
        User user = new User();
        user.setEmail(signUpDto.getEmail());
        user.setRoles(List.of(adminRole));
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        // Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        // user.setRoles(Collections.singleton(roles));

        User users = userRepository.save(user);
        String token = jaJwtService.generateToken(users);
        Token token1 = new Token();
        token1.user = users;
        token1.expired = false;
        token1.revoked = false;
        token1.token= token;
        token1.tokenType = TokenType.BEARER;
        tokenRepository.save(token1);
        return new ResponseEntity<>(token, HttpStatus.OK);

    }


}
