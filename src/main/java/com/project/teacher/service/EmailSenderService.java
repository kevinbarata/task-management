package com.project.teacher.service;

import com.project.teacher.dto.EmailDetails;

// Interface
public interface EmailSenderService {

    int sendEmail(EmailDetails emailDetails);

}
