package ru.socialnet.team29.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.socialnet.team29.domain.tables.records.PersonRecord;
import ru.socialnet.team29.model.FriendForFront;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FriendMapper {
    List<FriendForFront> PersonRecordToFriendForFront(List<PersonRecord> friendshipRecord);
}
