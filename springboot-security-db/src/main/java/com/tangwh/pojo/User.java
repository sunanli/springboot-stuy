package com.tangwh.pojo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2020/1/8 21:27
 */

public class User implements UserDetails {
    /**
     *
     */
    private Integer id;

    /**
     *
     */
    private String username;

    /**
     *
     */
    private String password;

    /**
     * 是否可用
     */
    private Boolean enabled;

    /**
     *  账户是否锁定
     */
    private Boolean locked;

    /**
     * 一个用户多个角色
     */
    private List<Role> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Boolean getEnabled() {
//        return enabled;
//    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
//
//    public Boolean getLocked() {
//        return locked;
//    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * 返回用户的所有角色
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 将返回的所有角色 添加再List<Role>中 注意Security所有的角色必须ROLE_开始
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role:roles){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
        }
        return authorities;
    }

    /**
     * 账户是否未过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        // 是 没过期  由于数据库中没有这个字段 所以就返回true
        return true;
    }

    /**
     * 账户是否锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        // 注意要删除上边的get 方法 取反
        return !locked;
    }

    /**
     * 密码是否过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     *  账户是否可用
     * @return
     */
    @Override
    public boolean isEnabled() {

        // 注意删除上面的  字段get方法
        return enabled;
    }
}