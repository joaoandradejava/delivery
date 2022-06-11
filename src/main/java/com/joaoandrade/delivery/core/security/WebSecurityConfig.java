package com.joaoandrade.delivery.core.security;


import com.joaoandrade.delivery.core.jwt.JwtAuthenticationFilter;
import com.joaoandrade.delivery.core.jwt.JwtAuthorizationFilter;
import com.joaoandrade.delivery.core.jwt.JwtUtil;
import com.joaoandrade.delivery.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] PUBLIC_POST = {"/clientes", "/administradores"};
    private static final String[] PUBLIC_GET = {"/produtos/disponivel", "/produtos/*"};
    private static final String[] PUBLIC_PUT = {"/usuarios/esqueceu-senha"};

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MessageSource messageSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors();
        http.authorizeRequests().antMatchers(HttpMethod.POST, PUBLIC_POST).permitAll().antMatchers(HttpMethod.GET, PUBLIC_GET).permitAll().antMatchers(HttpMethod.PUT, PUBLIC_PUT).permitAll().anyRequest().authenticated();
        http.addFilter(new JwtAuthenticationFilter(jwtUtil, super.authenticationManager(), messageSource));
        http.addFilter(new JwtAuthorizationFilter(super.authenticationManager(), jwtUtil, usuarioRepository, userDetailsService, messageSource));
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


}
