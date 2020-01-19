package com.tangwh.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * 用户每次带着token来访问就放行
 */
public class JwtFilter extends GenericFilterBean {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

     HttpServletRequest request = (HttpServletRequest) servletRequest;
     // 把Token 放在请求头里面
        String jwtToken = request.getHeader("authorization");

        // 解析Token
        Jws<Claims> jws = Jwts.parser().setSigningKey("javaboy@123")
                // 前端传的时候 会多一个Bearer  我们把它替换掉
                .parseClaimsJws(jwtToken.replace("Bearer", ""));

        Claims claims = jws.getBody();
        // 用户名
        String username = claims.getSubject();
        // 用户的角色
        // 把字符串转换成List集合 之前存在sb里面的 当前用户的角色
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
              //校验                                  //第一个参数 :username 第二个是 盐(密码加密的意思) 第三个: 角色
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, authorities);

        //设置在token里面
        SecurityContextHolder.getContext().setAuthentication(token);
        //让过滤器继续往下走
        filterChain.doFilter(servletRequest, servletResponse);


    }
}
