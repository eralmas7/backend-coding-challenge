package com.calculator.expense.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

/**
 * Security configuration for the expense calculator application.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    private JdbcUserDetailsManager userDetailsManager;

    /*
     * Allow just the static or public contents like css/js/image without being authentication but
     * for the rest of the resources, lets authenticate the user. (non-Javadoc)
     * 
     * @see
     * org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
     * #configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
     */
    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/static", "/public").permitAll().anyRequest().authenticated();
        httpSecurity.httpBasic();
        httpSecurity.csrf().disable();
    }

    /*
     * Configure authentication manager, such that it knows what SQL to call when it needs to find
     * user by email id. We store the password encoded within DB, hence when we get a request for
     * authentication, let manager hash it and compare hased version with the password hased in
     * database.
     */
    @Autowired
    public void configAuthentication(final AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        this.userDetailsManager = authenticationManagerBuilder.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select email,password_hash,enabled from USER where email=?").authoritiesByUsernameQuery("select email, role from USER where email=?").getUserDetailsService();
        authenticationManagerBuilder.userDetailsService(userDetailsManager).passwordEncoder(new BCryptPasswordEncoder());
    }
}
