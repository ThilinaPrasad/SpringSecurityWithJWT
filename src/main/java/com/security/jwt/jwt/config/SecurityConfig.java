package com.security.jwt.jwt.config;

import com.security.jwt.jwt.services.AuthProvider;
import com.security.jwt.jwt.services.AuthUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@ComponentScan
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    AuthProvider authProvider;


    @Autowired
    AuthenticationEntryPoint authEntryPoint;

    @Autowired
    AuthUserDetails authUserDetails;

/*

@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf()
                .disable()
                .cors()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/login")
                .permitAll()
        .anyRequest().authenticated()
        .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().cacheControl();
    }
*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       // auth.authenticationProvider(authProvider);
        auth.userDetailsService(authUserDetails).passwordEncoder(passwordEncoderBean());
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
       return new BCryptPasswordEncoder();
    }

   /* @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }*/
}
