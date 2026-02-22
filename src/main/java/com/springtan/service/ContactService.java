package com.springtan.service;

import com.springtan.dto.ContactRequestDto;
import com.springtan.entity.Contact;

import java.util.List;

public interface ContactService {

    Contact saveContact(Long userId, ContactRequestDto contactRequestDto);

    List<Contact> getAllContacts();

    Contact getContactById(Long id);

    void deleteContact(Long id);

    Contact updateContact(ContactRequestDto contactRequestDto, Long id);
}
