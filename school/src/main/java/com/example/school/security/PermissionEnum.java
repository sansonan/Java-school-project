package com.example.school.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public enum PermissionEnum {
    REGISTRATION_WRITE("registration:write"),
    REGISTRATION_READ("registration:read"),
    USER_WRITE("user:write"),
    USER_READ("user:read"),
    COURSE_WRITE("course:write"),
    COURSE_READ("course:read"),
    PROMOTION_WRITE("promotion:write"),
    PROMOTION_READ("promotion:read");
    private String description;

}
