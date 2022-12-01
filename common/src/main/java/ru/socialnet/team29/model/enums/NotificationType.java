package ru.socialnet.team29.model.enums;

public class NotificationType{

    private final static String [] notificationType = new String[]{"Post", "POST_COMMENT","COMMENT_COMMENT", "FRIEND_REQUEST","MESSAGE"};

   public static String getNotificationType(int number) {
        return notificationType[number];
    }
}
