package Scrap;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;

public class DumpYard {
	/*
	<!-- JWT Depenency-->
    <!-- 1) https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api -->
  <dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.5</version>
  </dependency>
<!-- 2) https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-impl -->
<dependency>
 <groupId>io.jsonwebtoken</groupId>
 <artifactId>jjwt-impl</artifactId>
 <version>0.11.5</version>
 <scope>runtime</scope>
</dependency>
<dependency>
 <groupId>io.jsonwebtoken</groupId>
 <artifactId>jjwt-jackson</artifactId> 
 <!-- or jjwt-gson if Gson is preferred -->
 <version>0.11.5</version>
 <scope>runtime</scope>
</dependency>*/
	/*
	 	@PreAuthorize("hasAuthority('SCOPE_READ')")
  @PostMapping("/token")
  public String token(Authentication authentication) {
      return tokenService.generateToken(authentication);
  }
  
	 * */
	
	
	
	////////////////////////////           JWT
	
	/*
	 * 
	 * private  TokenService tokenService;
    
	public UserAuthController(TokenService tokenService) {
		this.tokenService = tokenService;
	}
	 * 
	 *//*
	 private final String jwtKey = "dkfjrbgvhjabdrsfjbvgakjsbrgh34th256367434tghjsdbfgbreswbg";
	 @Bean
	    public UserDetailsService userDetailsService() {
	        return new InMemoryUserDetailsManager(
	                User.withUsername("dvega")
	                        .password("{noop}password")
	                        .authorities("READ","ROLE_USER")
	                        .build());
	    }

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        return http
	        		.csrf(crsf -> crsf.disable())
	                .authorizeHttpRequests( auth -> auth
	                        .requestMatchers("/api/auth/token").hasRole("USER")
	                        .anyRequest().hasAuthority("SCOPE_READ")
	                )
	                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	                .oauth2ResourceServer(oauth2 -> oauth2
	                        .jwt(jwt -> jwt
	                                .decoder(jwtDecoder())
	                            ))
	                .build();
	    }

	    @Bean
	    JwtEncoder jwtEncoder() {
	        return new NimbusJwtEncoder(new ImmutableSecret<>(jwtKey.getBytes()));
	    }

	    @Bean
	    public JwtDecoder jwtDecoder() {
	        byte[] bytes = jwtKey.getBytes();
	        SecretKeySpec originalKey = new SecretKeySpec(bytes, 0, bytes.length,"RSA");
	        return NimbusJwtDecoder.withSecretKey(originalKey).macAlgorithm(MacAlgorithm.HS512).build();
	    }*/
	/*
	 * */
}
