package com.example.demo.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired(required = true)
    UserPrincipleDetailSerivce userPrincipleDetailSerivce ;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/","/resources/**",
                "/static/**","/styles/**","/css/**","/vendor/**","/js/**","/images/**").permitAll()
                .and().authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/hello")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(daoAuthenticationProvider());
    }


    DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userPrincipleDetailSerivce);
        return authenticationProvider;
   }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
