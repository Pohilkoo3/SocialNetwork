package ru.socialnet.team29.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class FriendForFront {
    private Integer id;
    private LocalDateTime birthDate;
    private String city;
    private String country;
    private String firstName;
    private Boolean isOnline;
    private String lastName;
    private String photo;
    private String statusCode;
}
