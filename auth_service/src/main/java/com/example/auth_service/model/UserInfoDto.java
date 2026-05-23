package com.example.auth_service.model;

import com.example.auth_service.entities.UserInfo;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming ()

public class UserInfoDto extends UserInfo
{

    private String firstName; // first_name

    private String lastName; //last_name

    private Long phoneNumber;

    private String email; // email


}
