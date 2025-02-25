package tn.SabatSfakys.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.SabatSfakys.model.Client;
import tn.SabatSfakys.model.ERole;
import tn.SabatSfakys.model.Role;
import tn.SabatSfakys.model.User;
import tn.SabatSfakys.payload.request.LoginRequest;
import tn.SabatSfakys.payload.request.SignupRequest;
import tn.SabatSfakys.payload.response.JwtResponse;
import tn.SabatSfakys.payload.response.MessageResponse;
import tn.SabatSfakys.repository.AdminRepository;
import tn.SabatSfakys.repository.ClientRepository;
import tn.SabatSfakys.repository.FournisseurRepository;
import tn.SabatSfakys.repository.RoleRepository;
import tn.SabatSfakys.repository.UserRepository;
import tn.SabatSfakys.security.jwt.JwtUtils;
import tn.SabatSfakys.security.services.UserDetailsImpl;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	FournisseurRepository fournisseurRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

	    Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    String jwt = jwtUtils.generateJwtToken(authentication);
	    
	    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
	    
	    // Directly get the Role from UserDetailsImpl
	    Role role = userDetails.getRole();

	    return ResponseEntity.ok(new JwtResponse(jwt, 
	                                             userDetails.getId(), 
	                                             userDetails.getUsername(), 
	                                             userDetails.getEmail(), 
	                                           
	                                            
	                                             role)); // Pass the role object
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
	    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
	        return ResponseEntity
	                .badRequest()
	                .body(new MessageResponse("Error: Username is already taken!"));
	    }

	    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
	        return ResponseEntity
	                .badRequest()
	                .body(new MessageResponse("Error: Email is already in use!"));
	    }
	    String username = signUpRequest.getUsername();
	    String email = signUpRequest.getEmail();
	    String password = encoder.encode(signUpRequest.getPassword());
	    // Create new user's account
	   User user=new User(username,email,password);
	    

	    // Get the role from SignupRequest or default to ROLE_USER
	    Role role = signUpRequest.getRole();
	    if (role == null) {
	        role = roleRepository.findByName(ERole.ROLE_CLIENT)
	                             .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	    } else {
	        // Validate role if it's provided
	        role = roleRepository.findById(role.getId().longValue())
	                             .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	    }

	    user.setRole(role); // Set role
	    userRepository.save(user);
	   if("ROLE_CLIENT".equals(role.getName().name())) {
	        Client client = new Client(email,username,password);
	        					  
	        
	    	clientRepository.save(client);
	    }

	    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	/*// --- Inscription Client ---
    @PostMapping("/signup/client")
    public ResponseEntity<?> registerClient(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        User user = new User(signUpRequest.getUsername(), 
                             signUpRequest.getEmail(), 
                             encoder.encode(signUpRequest.getPassword()));

        Role clientRole = roleRepository.findByName(ERole.ROLE_CLIENT)
                           .orElseThrow(() -> new RuntimeException("Error: Client Role is not found."));
        user.setRole(clientRole);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Client registered successfully!"));
    }

    // --- Inscription Fournisseur ---
    @PostMapping("/signup/fournisseur")
    public ResponseEntity<?> registerFournisseur(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        User user = new User(signUpRequest.getUsername(), 
                             signUpRequest.getEmail(), 
                             encoder.encode(signUpRequest.getPassword()));

        Role fournisseurRole = roleRepository.findByName(ERole.ROLE_FOURNISSEUR)
                               .orElseThrow(() -> new RuntimeException("Error: Fournisseur Role is not found."));
        user.setRole(fournisseurRole);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Fournisseur registered successfully!"));
    }

    // --- Inscription Admin ---
    @PostMapping("/signup/admin")
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        User user = new User(signUpRequest.getUsername(), 
                             signUpRequest.getEmail(), 
                             encoder.encode(signUpRequest.getPassword()));

        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                          .orElseThrow(() -> new RuntimeException("Error: Admin Role is not found."));
        user.setRole(adminRole);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Admin registered successfully!"));
    }*/



}
