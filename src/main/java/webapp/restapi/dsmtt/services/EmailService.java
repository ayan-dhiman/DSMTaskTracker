package webapp.restapi.dsmtt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import webapp.restapi.dsmtt.models.User;
import webapp.restapi.dsmtt.repo.UserRepository;

@Slf4j
@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private UserRepository userRepo;

	@Value("${spring.mail.username}")
	private String taskifyMail;

	private void sendMail(String userEmail, String subject, String HTMLCONTENT) {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		MimeMessageHelper mimeMessageHelper;

		try {

			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

			mimeMessageHelper.setFrom(taskifyMail);
			mimeMessageHelper.setTo(userEmail);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(HTMLCONTENT, true);

			javaMailSender.send(mimeMessage);

			log.info("Mail Sent");

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	public void sendWelcomeMail(String email, String name) {

		if (email != null) {

			String WELCOME_HTML_CONTENT = getHtmlContent(name);

			sendMail(email, "Welcome to TASKIFY! Let's Streamline Your Tasks & Boost Productivity", WELCOME_HTML_CONTENT);

		} else {
			log.error("User is null while sending welcome mail");
		}

	}

	public void sendOTPMail(String email, String OTP) {

		User user = userRepo.findByEmail(email);

		if (user != null) {

			String OTP_HTML_CONTENT = getOTPHtmlContent(user.getName(), OTP);

			sendMail(email, "Reset Your TASKIFY Password and Get Back on Track", OTP_HTML_CONTENT);

		} else {
			log.error("User is null while sending otp mail");
		}

	}
	
	private String getHtmlContent(String name) {
        return "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "    <title>TASKIFY</title>" +
                "    <style>" +
                "        body {" +
                "            color: white;" +
                "            background-color: #171717;" +
                "            width: 100%;" +
                "            margin: 0;" +
                "            padding: 0;" +
                "            font-family: Arial, sans-serif;" +
                "        }" +
                "        .container {" +
                "            width: 100%;" +
                "            max-width: 600px;" +
                "            margin: 0 auto;" +
                "            padding: 20px;" +
                "            background-color: #1d1d1d;" +
                "        }" +
                "        .header {" +
                "            text-align: center;" +
                "            background-color: #161b22;" +
                "            padding: 20px;" +
                "            border-top: 4px solid #5a4c8d;" +
                "        }" +
                "        h1{" +
                "            color: #ffffff" +
                "          }" +
                "        .content {" +
                "            padding: 20px;" +
                "            background-color: #000000;" +
                "        }" +
                "        .content p {" +
                "            font-size: 14px;" +
                "            line-height: 20px;" +
                "            margin: 10px 0;" +
                "            color: #ffffff;" +
                "        }" +
                "        .footer {" +
                "            text-align: center;" +
                "            font-size: 12px;" +
                "            color: #cccccc;" +
                "            padding: 20px;" +
                "            border-top: 1px solid #333333;" +
                "            background-color: #000000;" +
                "        }" +
                "        a {" +
                "            color: #4493f8;" +
                "            text-decoration: none;" +
                "        }" +
                "        a:hover {" +
                "            text-decoration: underline;" +
                "        }" +
                "    </style>" +
                "</head>" +
                "<body>" +
                "    <div class=\"container\">" +
                "        <div class=\"header\">" +
                "            <h1>Taskify</h1>" +
                "        </div>" +
                "        <div class=\"content\">" +
                "            <p>Hi "+ name +",</p>" +
                "            <p>Welcome to <strong>TASKIFY</strong>!</p>" +
                "            <p>We’re excited to have you on board. TASKIFY is here to help you streamline your tasks and boost your productivity. Get ready to enjoy a more organized and efficient way to manage your tasks.</p>" +
                "            <p>Get Started : <a href=\"https://taskifyz.vercel.app/\">Login to Your Account</a></p>" +
                "            <p>Happy tasking!</p>" +
                "            <p>Best regards,</p>" +
                "            <p>The TASKIFY Team</p>" +
                "        </div>" +
                "        <div class=\"footer\">" +
                "            <p>If you have any questions, feel free to contact us. We're here to help!</p>" +
                "            <p>If you did not create an account with TASKIFY, please ignore this email.</p>" +
                "        </div>" +
                "    </div>" +
                "</body>" +
                "</html>";
    }
    
	private String getOTPHtmlContent(String name, String OTP) {
        return "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "    <title>TASKIFY</title>" +
                "    <style>" +
                "        body {" +
                "            color: white;" +
                "            background-color: #171717;" +
                "            width: 100%;" +
                "            margin: 0;" +
                "            padding: 0;" +
                "            font-family: Arial, sans-serif;" +
                "        }" +
                "        .container {" +
                "            width: 100%;" +
                "            max-width: 600px;" +
                "            margin: 0 auto;" +
                "            padding: 20px;" +
                "            background-color: #1d1d1d;" +
                "        }" +
                "        .header {" +
                "            text-align: center;" +
                "            background-color: #161b22;" +
                "            padding: 20px;" +
                "            border-top: 4px solid #5a4c8d;" +
                "        }" +
                "        h1{" +
                "            color: #ffffff" +
                "          }" +
                "        .content {" +
                "            padding: 20px;" +
                "            background-color: #000000;" +
                "        }" +
                "        .content p {" +
                "            font-size: 14px;" +
                "            line-height: 20px;" +
                "            margin: 10px 0;" +
                "            color: #ffffff;" +
                "        }" +
                "        .footer {" +
                "            text-align: center;" +
                "            font-size: 12px;" +
                "            color: #cccccc;" +
                "            padding: 20px;" +
                "            border-top: 1px solid #333333;" +
                "            background-color: #000000;" +
                "        }" +
                "        a {" +
                "            color: #4493f8;" +
                "            text-decoration: none;" +
                "        }" +
                "        a:hover {" +
                "            text-decoration: underline;" +
                "        }" +
                "        .otp{" +
                "            background-color: #ffffff;" +
                "            color: #000000;" +
                "            padding: 15px;" +
                "            text-align: center;" +
                "            font-size: 16px;" +
                "        }" +
                "    </style>" +
                "</head>" +
                "<body>" +
                "    <div class=\"container\">" +
                "        <div class=\"header\">" +
                "            <h1>Taskify</h1>" +
                "        </div>" +
                "        <div class=\"content\">" +
                "            <p>Hi "+name+",</p>" +
                "            <p>Welcome to <strong>TASKIFY</strong>!</p>" +
                "            <p>We're thrilled to have you join us. TASKIFY is your go-to platform for task management, designed to make your life easier and more productive.</p>" +
                "            <p>To ensure the security of your account, we need to verify your email address. Please use the following OTP (One-Time Password) to reset your password:</p>" +
                "            <p></p>" +
                "            <div class=\"otp\"><strong>"+OTP+"</strong></div>" +
                "            <p></p>" +
                "            <p>Simply enter this code when prompted during the Forgot Password process.</p>" +
                "            <p>If you have any questions or need assistance, feel free to reach out to our support team.</p>" +                
                "            <p>Happy tasking!</p>" +
                "            <p>Best regards,</p>" +
                "            <p>The TASKIFY Team</p>" +
                "        	 <div class=\"footer\">" +
                "            <p>If you have any questions, feel free to contact us. We're here to help!</p>" +
                "            <p>If you did not create an account with TASKIFY, please ignore this email.</p>" +
                "        </div>" +
                "        </div>" +
                "    </div>" +
                "</body>" +
                "</html>";
    }

}
