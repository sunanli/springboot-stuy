package com.tangwh.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import sun.font.FontManagerNativeLibrary;

import java.util.Collection;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2020/1/9 0:47
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {

    /**
     *
     * @param authentication  当前登录用户信息
     * @param o 当前请求对象
     * @param collection 返回值
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {

        //  我需要的角色
        for (ConfigAttribute configAttribute : collection) {
                // 如果你需要的角色 是ROLE_login 说明你和上面的都没匹配上 说明这个请求是登录之后才能访问的
            if ("ROLE_login".equals(configAttribute.getAttribute())){
           // 查看是否登录   如果是匿名登录
                if (authentication instanceof AnonymousAuthenticationToken){

                    throw new AccessDeniedException("非法请求");
                }else {
                    return;
                }
            }

            // 我现在具备的角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                // 如果你需要ABC 那我只有一个 就可以了
                if (authority.equals(configAttribute.getAttribute())){
                    return;
                }

            }
        }
        throw new AccessDeniedException("非法请求");

    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
