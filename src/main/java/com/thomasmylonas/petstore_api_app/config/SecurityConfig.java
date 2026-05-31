package com.thomasmylonas.petstore_api_app.config;

//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig {

    /*public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authorize -> authorize
                .anyRequest().authenticated()
        );
        http.cors(Customizer.withDefaults())
                .csrf(CsrfConfigurer::disable);
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer
                .jwt(Customizer.withDefaults())
        );

        //
        // Usually, it is mandatory to use this configuration (HttpSecurity::oauth2Client).
        // Its seems that with KeycloakServer with don't need to add this configuration at all
        //http.oauth2Client(Customizer.withDefaults());
        //http.oauth2Login(oauth2login -> oauth2login.loginPage("/home"));
        //
        return http.build();
    }*/
}
