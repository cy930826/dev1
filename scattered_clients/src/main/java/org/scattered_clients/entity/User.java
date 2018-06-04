package org.scattered_clients.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class User implements UserDetails {

    public  User(){
        super();
    }

    private int id;
    private String login;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    private String password;
    private String userName;
    private String address;
    private String job;
    private long groupId;
    private Date birthDate;
    private String city;
    private String district;
    private String province;
    private String streetAddress;
    private String state;
    private String type;
    private Date lastLoginDate;
    // 用户角色信息
    private List<UserRole> roles;
    // 权限集合数据
    private String roleArray;

}
