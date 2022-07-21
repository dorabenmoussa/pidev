package tn.esprit.pidev.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tn.esprit.pidev.entities.Utilisateur;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        //setFilterProcessesUrl("/api/services/controller/user/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            Utilisateur creds = new ObjectMapper()
                    .readValue(req.getInputStream(), Utilisateur.class);

            System.out.println("cred :: "+ creds.getUsername() + creds.getPassword());

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            System.err.println( "err" + e.getMessage());
            throw new RuntimeException(e);
        }catch (Exception ex){
            System.err.println( "err" + ex.getMessage());
            // throw new Exception();
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {
        String token = JWT.create()
                .withSubject(((Utilisateur) auth.getPrincipal()).getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));

        Utilisateur user = ((Utilisateur) auth.getPrincipal());

        res.setContentType("application/json;charset=UTF-8");
        res.getWriter().write(new JSONObject()
                .put("token", token)
                        .put("id", user.getId())
                        .put("email", user.getEmail())
                        .put("firstName", user.getFirstName())
                        .put("lastName",user.getLastName())
                        .put("jobTitle",user.getJobTitle())
                        .put("phone",user.getPhone())
                        .put("role",user.getRole())
                .toString());
        res.getWriter().flush();
    }
}