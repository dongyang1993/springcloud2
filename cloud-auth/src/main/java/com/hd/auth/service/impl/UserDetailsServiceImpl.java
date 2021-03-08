package com.hd.auth.service.impl;

import com.hd.auth.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //TODO findUserByUsername from DB or other else
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        sysUser.setPassword("123456");
        /**
         * AuthorityUtils.commaSeparatedStringToAuthorityList("/consumer/listInfo,/index");
         */
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("/consumer/listInfo", "/index");
        sysUser.setAuthorities(authorities);
        return sysUser;
    }
}
