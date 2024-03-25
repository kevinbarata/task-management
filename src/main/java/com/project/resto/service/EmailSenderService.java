package com.project.resto.service;

import com.project.resto.dto.EmailDetails;

import java.util.List;

// Interface
public interface EmailSenderService {

    int sendEmail(EmailDetails emailDetails);

}
