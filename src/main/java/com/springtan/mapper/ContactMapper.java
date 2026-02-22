package com.springtan.mapper;

import com.springtan.dto.ContactRequestDto;
import com.springtan.dto.ContactResponseDto;
import com.springtan.entity.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    @Mapping(target = "user", ignore = true)
    Contact toEntity(ContactRequestDto contactRequestDto);

//    @Mapping(source = "user.id", target = "userId")
    ContactResponseDto toResposeDto(Contact contact);

    ContactRequestDto toRequestDto(Contact contact);

    void updateContactFromRequestDto(
            ContactRequestDto contactRequestDto,
            @MappingTarget Contact contact
    );
}
