package com.example.demo.mapper;

import com.example.demo.entity.User;
import com.example.demo.model.RegisterResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    RegisterResponse toRegisterResponse(User user);

}
