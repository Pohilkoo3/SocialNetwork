package ru.socialnet.team29.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.socialnet.team29.exception.ErrorResponse;
import ru.socialnet.team29.model.ProfileResponse;

import java.io.IOException;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {


    @GetMapping("/v1/account/me")
    @ApiOperation(value = "Получение текущего пользователя")
    @ApiResponses({@ApiResponse(code = 200, message = "Успешное получение текущего пользователя"),
            @ApiResponse(code = 401, message = "unauthorized", response = ErrorResponse.class)})
    @ResponseBody
    public Object getProfile(ProfileResponse profileResponse) throws IOException {
        return "{\"id\":0,\"email\":\"test@mail.ru\",\"phone\":\"88888888\",\"photo\":\"string\",\"about\":\"Родился и крестися\",\"city\":\"Москва\",\"country\":\"Россия\",\"token\":\"string\",\"statusCode\":\"FRIEND\",\"firstName\":\"Олег\",\"lastName\":\"Похилько\",\"regDate\":\"2022-11-22T14:08:01.270Z\",\"birthDate\":\"2022-11-22T14:08:01.270Z\",\"messagePermission\":\"string\",\"lastOnlineTime\":\"2022-11-22T14:08:01.270Z\",\"isOnline\":true,\"isBlocked\":true,\"isDeleted\":true,\"photoId\":\"string\",\"photoName\":\"string\",\"role\":\"USER\",\"createdOn\":\"2022-11-22T14:08:01.270Z\",\"updatedOn\":\"2022-11-22T14:08:01.270Z\",\"password\":\"string\"}";

    }


}
