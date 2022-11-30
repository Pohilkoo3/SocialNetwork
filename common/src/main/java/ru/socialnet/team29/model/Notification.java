package ru.socialnet.team29.model;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    private int id;
    private int typeId;
    private LocalDateTime sentTime;
    private int personId;
    private int entityId;
    private String contact;

}
