package com.springtan.service;

import com.springtan.dto.ContactRequestDto;
import com.springtan.dto.ContactResponseDto;
import com.springtan.entity.Contact;

import java.util.List;

public interface ContactService {

    ContactResponseDto saveContact(Long userId, ContactRequestDto contactRequestDto);

    List<ContactResponseDto> getAllContacts();

    ContactResponseDto getContactById(Long id);

    void deleteContact(Long id);

    ContactResponseDto updateContact(ContactRequestDto contactRequestDto, Long id);
}
