package com.example.school.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.school.security.PermissionEnum.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RoleEnum {
    ADMIN(Set.of(COURSE_READ, COURSE_WRITE, REGISTRATION_WRITE, REGISTRATION_READ,USER_WRITE,USER_READ)),
    STUDENT(Set.of(COURSE_READ)),
    TEACHER(Set.of(COURSE_READ, COURSE_WRITE, REGISTRATION_WRITE, REGISTRATION_READ));
    private Set<PermissionEnum> permissions;

    public Set<SimpleGrantedAuthority> getAuthorities(){
        Set<SimpleGrantedAuthority> grantedAuthorities = this.permissions.stream()
                .map(permissionEnum -> new SimpleGrantedAuthority(permissionEnum.getDescription()))
                .collect(Collectors.toSet());
        SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_"+this.name());
        grantedAuthorities.add(role);
        return grantedAuthorities;
    }

}
