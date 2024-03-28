package phamiz.ecommerce.backend.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.Key;
import java.util.Base64;
import java.util.List;

// ensures the filter will be excuted only once per request
public class JwtValidator extends OncePerRequestFilter {
    private final static String TOKEN_PREFIX = "Bearer ";
//    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);


    @Override
    // HttpServletRequest: object contains info about current http request
    // HttpServletResponse: object contains info about the return http response
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // value of Authorization commnly is Bearer <token>
        String jwt = request.getHeader(JwtConstant.JWT_HEADER);

        if (jwt != null && jwt.startsWith(TOKEN_PREFIX)) {
            // Bearer <token>
            jwt = jwt.substring(TOKEN_PREFIX.length());

            try {
//                String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";
//                Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret), SignatureAlgorithm.HS256.getJcaName());
                //  use HMAC-SHA based on SECRET_KEY.getBytes() after can used for asign and authen code jwt
                SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

                // Jwts.parserBuilder(): allow create a builder for build a jwt decrypt
                // setSigningKey(key): key is the SecretKey object for decrupt signed of JWT
                //.parseClaimsJwt(jwt).getBody(): decrypt jwt String and return Claims object extract from JWT decrypted
                // Claims are (username, role, expired, .etc)
                Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
                String email = String.valueOf(claims.get("email"));
                String authorities = String.valueOf(claims.get("authorities"));
                // GrantedAuthority: is a role that a person can have
                List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
                // UsernamePasswordAuthenticationToken: represent authen using username and password
                // email: represents ìnfo user, null: common is a password, auths: is role of user
                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, auths);
                // set authentication created into security context.
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (ExpiredJwtException | MalformedJwtException e) {
                throw new BadCredentialsException("invalid token from jwt validator", e);
            } catch (Exception e) {
                throw new BadCredentialsException("error while procesing tokne", e);
            }
        }

        //1. transmission request and response to next filter in string
        //2. It allowed current filter complete that handle and return result in response for browser
        //3. When last filter handle string complete, control permission return app for complete request handle and send response browser
        filterChain.doFilter(request, response);
    }
}