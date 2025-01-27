package com.yerinden.yerinden.controller.request;

import com.yerinden.yerinden.model.Gender;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserUpdateRequest {
    private String name;
    private String surname;
    private String email;
    private String address;
    private String phoneNumber;
    private Gender gender;
}
