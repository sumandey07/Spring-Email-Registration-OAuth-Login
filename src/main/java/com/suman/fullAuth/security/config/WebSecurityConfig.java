package com.suman.fullAuth.security.config;

import com.suman.fullAuth.appuser.AppUserService;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig{

    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth-> auth.requestMatchers("/api/v*/registration/**","/register","/home","/about","/terms","/privacy","/forget").permitAll().anyRequest().authenticated()).oauth2Login(oauth->{oauth.loginPage("/login");oauth.defaultSuccessUrl("/dashboard");}).formLogin(form->form.loginPage("/login").defaultSuccessUrl("/dashboard",true).permitAll()).logout(logout->{logout.logoutUrl("/logout").deleteCookies("JSESSIONID");logout.logoutSuccessUrl("/home");}).httpBasic(Customizer.withDefaults()).headers(head->{head.defaultsDisabled();head.disable();}).build();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }
}