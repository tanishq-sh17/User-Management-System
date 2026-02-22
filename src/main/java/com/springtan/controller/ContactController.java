package com.springtan.controller;

import com.springtan.dto.ContactRequestDto;
import com.springtan.dto.ContactResponseDto;
import com.springtan.service.ContactService;
import com.springtan.util.AppConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/users/{userId}/contacts")
    public ResponseEntity<ContactResponseDto> addContact(
            @PathVariable Long userId,
            @Valid @RequestBody ContactRequestDto contactRequestDto
    )
    {
        ContactResponseDto contactResponseDto =
                contactService.saveContact(userId, contactRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(contactResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<ContactResponseDto>> getAllContacts(){
        List<ContactResponseDto> contactResponseDtoList
                = contactService
                    .getAllContacts()
                    .stream()
                    .toList();
        return ResponseEntity.ok(contactResponseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactResponseDto> getContactById(@PathVariable Long id){
        return ResponseEntity.ok(contactService.getContactById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id){
        contactService.deleteContact(id);
        return ResponseEntity.ok(AppConstants.CONTACT_DELETED_SUCCESSFULLY);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactResponseDto> updateContact(
            @Valid @RequestBody ContactRequestDto contactRequestDto,
            @PathVariable Long id
    )
    {
        ContactResponseDto contactResponseDto =
                contactService.updateContact(contactRequestDto, id);
        return ResponseEntity.ok(contactResponseDto);
    }
}

