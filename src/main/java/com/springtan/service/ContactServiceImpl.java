package com.springtan.service;

import com.springtan.dto.ContactRequestDto;
import com.springtan.entity.Contact;
import com.springtan.entity.User;
import com.springtan.exception.ContactNotFoundException;
import com.springtan.exception.UserNotFoundException;
import com.springtan.mapper.ContactMapper;
import com.springtan.repository.ContactRepository;
import com.springtan.repository.UserRepository;
import com.springtan.util.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final UserRepository userRepository;
    private final ContactMapper contactMapper;

    public ContactServiceImpl(ContactRepository contactRepository, UserRepository userRepository, ContactMapper contactMapper) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
        this.contactMapper = contactMapper;
    }

    @Override
    @Transactional
    public Contact saveContact(Long userId,
                               ContactRequestDto contactRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error("User not found with id={}", userId);
                    return new UserNotFoundException(AppConstants.USER_NOT_FOUND);
                });
        Contact contact = contactMapper.toEntity(contactRequestDto);

        user.addContact(contact);

        return contactRepository.save(contact);
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getContactById(Long id) {
        return contactRepository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("Contact not found with id={}", id);
                    return new ContactNotFoundException(AppConstants.CONTACT_NOT_FOUND);
                });
    }

    @Override
    @Transactional
    public void deleteContact(Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Contact not found with id={} while deleting the contact", id);
                    return new ContactNotFoundException(AppConstants.CONTACT_NOT_FOUND);
                });
        contactRepository.delete(contact);
    }

    @Override
    @Transactional
    public Contact updateContact(ContactRequestDto contactRequestDto, Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Contact not found with id={} while updating the contact", id);
                    return new ContactNotFoundException(AppConstants.CONTACT_NOT_FOUND);
                });
        contactMapper.updateContactFromRequestDto(contactRequestDto, contact);
        return contactRepository.save(contact);
    }
}
