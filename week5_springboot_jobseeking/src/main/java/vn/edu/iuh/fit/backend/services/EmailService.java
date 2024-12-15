package vn.edu.iuh.fit.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    public void sendInvitationEmail(String candidateEmail, String companyName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(candidateEmail);
        message.setSubject("Lời Mời Tham Gia Tuyển Dụng");
        message.setText("Chúng tôi, " + companyName + ", muốn mời bạn tham gia quá trình tuyển dụng. Xin vui lòng phản hồi nếu bạn quan tâm.");

        emailSender.send(message);
    }
}
