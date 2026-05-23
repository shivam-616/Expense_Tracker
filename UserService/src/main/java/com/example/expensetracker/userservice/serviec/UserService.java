package com.example.expensetracker.userservice.serviec;


import com.example.expensetracker.userservice.entites.UserInfo;
import com.example.expensetracker.userservice.entites.userinfoDto;
import com.example.expensetracker.userservice.repostiory.userservicerepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final userservicerepo userservicerepo;

    public userinfoDto createOrUpdateUser(userinfoDto eventdata){
        // two method kafka call to creata a blank templeate
            // no need to write as kafka doesn't need it
        //and a controller for method
        UnaryOperator<UserInfo> updatingUser = user -> {
            return userservicerepo.save(eventdata.transformToUserInfo());
        };
        Supplier<UserInfo> createUser = () -> {
            return userservicerepo.save(eventdata.transformToUserInfo());
        };
        // check that the user is present already
        // Execute the logic
        UserInfo savedUser = userservicerepo.findByUserId(eventdata.userId())
                .map(updatingUser)
                .orElseGet(createUser);
        return new userinfoDto(savedUser.getFirstName() ,savedUser.getLastName() , savedUser.getUserId(), savedUser.getEmail(), savedUser.getPhoneNumber(), savedUser.getProfilPic());
    }
    public userinfoDto getUser(userinfoDto userInfoDto) throws Exception{
        Optional<UserInfo> userInfoDtoOpt = userservicerepo.findByUserId(userInfoDto.userId());
        if(userInfoDtoOpt.isEmpty()){
            throw new Exception("User not found");
        }
        UserInfo userInfo = userInfoDtoOpt.get();
        return new userinfoDto(
                userInfo.getUserId(),
                userInfo.getFirstName(),
                userInfo.getLastName(),
                userInfo.getEmail(),
                userInfo.getPhoneNumber(),
                userInfo.getProfilPic()
        );
    }
}
