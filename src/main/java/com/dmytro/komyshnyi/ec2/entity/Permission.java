package com.dmytro.komyshnyi.ec2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Permission {
    READ("read"),
    WRITE("write");

    private final String permission;
}
