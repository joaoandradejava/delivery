package com.joaoandrade.delivery.core.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaoandrade.delivery.core.security.LoginDTO;
import com.joaoandrade.delivery.core.security.UsuarioAutenticado;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        setAuthenticationManager(authenticationManager);

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginDTO loginDTO = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getSenha());

            return getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
            UsuarioAutenticado usuarioAutenticado = (UsuarioAutenticado) authResult.getPrincipal();
            String tokenJwt = "Bearer " + jwtUtil.gerarTokenJwt(usuarioAutenticado.getId());

            response.setHeader("Authorization", tokenJwt);
            response.getWriter().write(json(usuarioAutenticado, tokenJwt));
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

    }

    private String json(UsuarioAutenticado usuarioAutenticado, String tokenJwt) {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        builder.append("\"id\": " + usuarioAutenticado.getId() + ",\n");
        builder.append("\"nome\": \"" + usuarioAutenticado.getNome() + "\",\n");
        builder.append("\"email\": \"" + usuarioAutenticado.getUsername() + "\",\n");
        builder.append("\"isAdmin\": " + usuarioAutenticado.isAdmin() + ",\n");
        builder.append("\"tokenJwt\": \"" + tokenJwt + "\"\n");

        builder.append("}");

        return builder.toString();
    }
}
