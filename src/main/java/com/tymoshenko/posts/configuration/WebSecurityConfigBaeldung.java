package com.tymoshenko.posts.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
//@Profile("!https")
public class WebSecurityConfigBaeldung extends WebSecurityConfigurerAdapter {

    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }


    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        // @formatter:off
        auth.inMemoryAuthentication()
                .withUser("user1").password("user1Pass").roles("USER")
                .and()
                .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
                .and()
                .withUser("admin@admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
        // @formatter:on
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        /*http.csrf().disable().cors().disable().authorizeRequests().anyRequest().
                .authorizeRequests().anyRequest().authenticated()
                .and().httpBasic();

        http.cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/anonymous*").anonymous()
                .antMatchers(HttpMethod.GET, "/index*", "/static/**", "/*.js", "/*.json", "/*.ico","/*").permitAll()
                .antMatchers(HttpMethod.OPTIONS,"/*").permitAll()
                .anyRequest().authenticated().and().httpBasic()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/hello",true)
                .failureUrl("/error?error=true")
                .and().httpBasic()
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID");*/

        http.httpBasic()
                .authenticationEntryPoint(new NoPopupBasicAuthenticationEntryPoint())
                .and()
                .csrf().disable()
                .authorizeRequests()
                /*.antMatchers(HttpMethod.OPTIONS,"/*").permitAll()//allow CORS option calls*/
                .antMatchers(HttpMethod.POST,"/posts*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                /*.and()
                .formLogin()
                .loginPage("/login666")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/logined_succesfully",true)
                .failureUrl("/error?error=true")*/
        ;




    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}