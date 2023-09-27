package me.droidzed.springmailerapp.service.mailer;

import me.droidzed.springmailerapp.types.MailInput;
import me.droidzed.springmailerapp.types.Response;

import javax.mail.MessagingException;

public interface MailerService {
    Response sendMailToMaster(MailInput input) throws MessagingException;
}
