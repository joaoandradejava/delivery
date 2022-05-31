package com.joaoandrade.delivery.core.jwt;

import com.joaoandrade.delivery.domain.exception.SistemaException;
import com.joaoandrade.delivery.domain.model.Usuario;
import com.joaoandrade.delivery.domain.repository.UsuarioRepository;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private JwtUtil jwtUtil;
    private UsuarioRepository repository;
    private UserDetailsService userDetailsService;
    private MessageSource messageSource;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UsuarioRepository usuarioRepository, UserDetailsService userDetailsService, MessageSource messageSource) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.repository = usuarioRepository;
        this.userDetailsService = userDetailsService;
        this.messageSource = messageSource;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String tokenJwt = request.getHeader("Authorization");

        if (StringUtils.hasLength(tokenJwt) && tokenJwt.startsWith("Bearer ")) {
            tokenJwt = tokenJwt.replaceAll("Bearer ", "");
            if (jwtUtil.isTokenJwtValido(tokenJwt)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = getAuthentication(tokenJwt);
                if (usernamePasswordAuthenticationToken != null) {
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String tokenJwt) {
        Long id = jwtUtil.getSubject(tokenJwt);

        if (id != null) {
            String[] args = {"Usuario", id.toString()};
            Usuario usuario = repository.findById(id).orElseThrow(() -> new SistemaException(messageSource.getMessage("entidade.nao.esta.associada.substantivo.masculino", args, LocaleContextHolder.getLocale())));
            if (usuario != null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(usuario.getEmail());
                if (userDetails != null) {
                    return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                }
            }
        }

        return null;
    }
}
