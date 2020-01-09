package com.tangwh.config;

import com.tangwh.pojo.Menu;
import com.tangwh.pojo.Role;
import com.tangwh.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2020/1/9 0:15
 */


/**
 * 先进
 */
@Component
public class MyFilter implements FilterInvocationSecurityMetadataSource {

    /**
     *   路径匹配
     */
    AntPathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    MenuService menuService;

    /**
     * 过滤器配置  代码核心  分析出来你的请求地址  根据请求地址分析出来这个地址需要哪个角色
     *  每次请求都会经过这个地址 根绝请求的地址算出需要的角色去匹配  匹配上了 直接把角色返回回去
     * @param o
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        // 获取请求的地址
        String url = ((FilterInvocation) o).getRequestUrl();
        System.out.println("请求的地址:" + url);
    // 优化建议 可以存在redis缓存中
        List<Menu> allMenus = menuService.getAllMenus();
       allMenus.forEach(add->{
           System.out.println("调用接口返回的数据"+ add.toString());
       });


        for (Menu allMenu : allMenus) {
            System.out.println("allMenu.getPattern():"+allMenu.getPattern());
            System.out.println("allMenu.getPattern()>>>:"+pathMatcher.match(allMenu.getPattern(), url));
            // 如果对上了 就拿到角色
            if (pathMatcher.match(allMenu.getPattern(), url)){


                  // 拿到对应的角色 并返回Collection
                List<Role> roles = allMenu.getRoles();



                String[] rolesStr = new String[roles.size()];
                for (int i = 0; i <roles.size() ; i++) {
                 rolesStr[i] = roles.get(i).getName();
                }

              //  System.out.println( "返回的值"+SecurityConfig.createList(rolesStr));
                return SecurityConfig.createList(rolesStr);

            }
        }
        // 如果匹配不上 那么久给个标记符号
        return SecurityConfig.createList("ROLE_login");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
