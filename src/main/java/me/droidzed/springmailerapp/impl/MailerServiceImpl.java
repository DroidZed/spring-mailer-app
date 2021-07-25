package me.droidzed.springmailerapp.impl;

import lombok.extern.slf4j.Slf4j;
import me.droidzed.springmailerapp.service.MailerService;
import me.droidzed.springmailerapp.types.MailInput;
import me.droidzed.springmailerapp.types.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;


import static java.text.MessageFormat.format;

@Service
@Slf4j
public class MailerServiceImpl implements MailerService {

    @Value(value = "${app.master}")
    private String appMaster;

    @Value(value = "${spring.mail.username}")
    private String appMail;

    private final JavaMailSender javaMailSender;

    private final SpringTemplateEngine templateEngine;

    public MailerServiceImpl(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public Response sendMailToMaster(MailInput input) throws MessagingException {

        log.info("Sending email...");

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Context context = new Context();

        context.setVariable("contact", input);

        String html = templateEngine.process("contact", context);

        helper.setFrom(this.appMail);
        helper.setTo(this.appMaster);
        helper.setSubject(input.getSubject());
        helper.setText(html, true);
        javaMailSender.send(mimeMessage);

        return new Response(format("Message sent to {0}", this.appMaster));
    }
}
