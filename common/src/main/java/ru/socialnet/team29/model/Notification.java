package ru.socialnet.team29.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    private Integer id;
    private int typeId;
    private LocalDateTime sentTime;
    private int personId;
    private int entityId;
    private String contact;

}
