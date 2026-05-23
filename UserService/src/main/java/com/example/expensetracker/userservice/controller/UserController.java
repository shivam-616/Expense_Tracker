package com.example.expensetracker.userservice.controller;

import com.example.expensetracker.userservice.entites.UserInfo;
import com.example.expensetracker.userservice.entites.userinfoDto;
import com.example.expensetracker.userservice.repostiory.userservicerepo;
import com.example.expensetracker.userservice.serviec.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private userservicerepo userservicerepo;

    @PostMapping("/user/v1/createUpdate")
    public ResponseEntity<userinfoDto> createUpdateUser(userinfoDto eventdata){
        try {
            userinfoDto user = userService.createOrUpdateUser(eventdata);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/all")
    public ResponseEntity<List<UserInfo>> getAllUsers() {
        List<UserInfo> users = userservicerepo.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/user/v1/getUser")
    public ResponseEntity<userinfoDto> getUser(@RequestBody userinfoDto userInfoDto){
        try{
            userinfoDto user = userService.getUser(userInfoDto);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/health")
    public ResponseEntity<Boolean> checkHealth(){
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
