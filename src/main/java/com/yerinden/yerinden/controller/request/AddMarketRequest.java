package com.yerinden.yerinden.controller.request;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class AddMarketRequest {
    private String vkn;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Boolean isActive;
    private List<Long> marketAdminUserIds;
}
