package com.labsync.dto;

import com.labsync.entity.Role;
import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Role role;
}
