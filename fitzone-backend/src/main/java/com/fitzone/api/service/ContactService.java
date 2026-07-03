package com.fitzone.api.service;

import com.fitzone.api.dto.request.ContactRequest;
import com.fitzone.api.exception.ResourceNotFoundException;
import com.fitzone.api.model.Contact;
import com.fitzone.api.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final EmailService emailService;

    @Transactional
    public Contact submitContactMessage(ContactRequest request) {
        Contact contact = Contact.builder()
                .name(request.getName())
                .email(request.getEmail())
                .subject(request.getSubject())
                .message(request.getMessage())
                .build();

        Contact savedContact = contactRepository.save(contact);

        // Send an email to the admin
        String mailText = String.format("New contact message from %s (%s):\n\n%s",
                request.getName(), request.getEmail(), request.getMessage());
        try {
            emailService.sendSimpleMessage("admin@fitzone.com", "New Contact Form Submission: " + request.getSubject(), mailText);
        } catch (Exception e) {
            // Log error, but don't fail the submission
            System.err.println("Failed to send email: " + e.getMessage());
        }

        return savedContact;
    }

    public List<Contact> getAllMessages() {
        return contactRepository.findAll();
    }

    @Transactional
    public Contact markAsRead(Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found"));
        contact.setIsRead(true);
        return contactRepository.save(contact);
    }
}
