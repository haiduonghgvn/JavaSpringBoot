package com.example.demo.model.dto;

import com.example.demo.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private long id;
    private String name;
    private String email;
    private String phone;
    private String avatar;
    private String password;
    private List<Role> roleList;
}
