package com.project.task.service;

import com.project.task.dto.EmailDetails;

// Interface
public interface EmailSenderService {

    int sendEmail(EmailDetails emailDetails);

}
