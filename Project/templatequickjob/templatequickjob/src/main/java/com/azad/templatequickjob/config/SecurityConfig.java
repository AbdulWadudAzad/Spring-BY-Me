package com.azad.templatequickjob.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableJpaRepositories(basePackageClasses = com.azad.templatequickjob.repo.UserRepo.class)
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;

    @Autowired
    CustomUserDetailsService customUserDetailsServicerepo;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //use for encrift and decrift password, which help to hide password from hacker
    }

    @Override  //use for data insert from database
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(customUserDetailsServicerepo)
                .passwordEncoder(passwordEncoder());
    }


    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/assets/**","/images/**","/vendors/**", "/404/**", "/sign-up", "/login","/",
                        "/forgetpassword","/about","/confirm","/role-save", "/user-save").permitAll()
                .antMatchers(
                        "/jobtitles/**", "/employmenttypes/**","/jobfunctions/**","/companyindustrys/**",
                        "/senioritylevels/**","/skills/**","/hearaboutus/**","/workexperiences/**","/certifications/**"
                        ,"/tools/**","/exams/**","/edulevels/**","/edusubjects/**","/results/**","/boards/**","/users/**",
                        "/citys/**","/countries/**","/divisions/**","/addresses/**","/jobcategorys/**","/jobposts/**",
                        "/districs/**","/projects/**","/referances/**"
                ).hasAnyRole("ADMIN","SUPERADMIN")
                .antMatchers("/userprofile/**","/cv/**").hasAnyRole("SUPERADMIN","ADMIN","USER")
                .antMatchers("/roles/**").hasRole(
                "SUPERADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);

    }

//    public UserDetailsService userDetailsService(){
//        UserDetails user= User.withDefaultPasswordEncoder()
//                .username("azad").password("1234").roles("ADMIN").build();
//        return new InMemoryUserDetailsManager(user);
//    }


}