package ru.socialnet.team29.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.socialnet.team29.answers_interface.CommonAnswer;
import ru.socialnet.team29.payloads.ContactConfirmationPayload;
import ru.socialnet.team29.service.UserDataService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountController {
    private final UserDataService userDataService;

    @PostMapping("/v1/auth/register")
    public String handlerRegisterNewUser(@RequestBody ContactConfirmationPayload contactConfirmationPayload) {
        return "null";
    }

}

