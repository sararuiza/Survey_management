package Riwi.Survey_management.infrastructure.helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EmialHelper {

    private final JavaMailSender mailSender;

    public void sendEmail(String destinity) {
        MimeMessage message = mailSender.createMimeMessage();

       
        }
    
}
