package com.tangwh.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tangwh.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    // 参数一:默认要处理的地址  参数二: 后面校验需要用到他 校验的核心类
    public JwtLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {

        //
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        //校验的核心类
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException {
        // 提取用户和密码 使用JSON上传JSON转换成对象 参数一:输入流 参数二:要转化的对象
        User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),
                user.getPassword()));
    }

    /**
     * 登录成功的回调
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 登录成功的用户信息 :authResult
        // 获取登录用户的角色
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        StringBuffer sb = new StringBuffer();
        // 把角色放在StringBuffer 中
        for (GrantedAuthority authority : authorities) {
            sb.append(authority.getAuthority())
                    .append(",");
        }
        // 生成jwt Token
        String jwt = Jwts.builder()
                // 配置用户的角色
                .claim("authorities", sb)
                // 主题 用户名
                .setSubject(authResult.getName())
                // 过期时间Token 1小时
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                //清零 注意空格
                .signWith(SignatureAlgorithm.HS512,"javaboy@123")
                .compact();

        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        map.put("msg", "登陆成功");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(map));
        out.flush();
        out.close();

    }

    /**
     * 登录失败的回调
     *
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Map<String, String> map = new HashMap<>();
        map.put("msg", "登陆失败");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(map));
        out.flush();
        out.close();
    }
}
