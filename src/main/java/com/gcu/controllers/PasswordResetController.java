package com.gcu.controllers;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gcu.data.UserDataService;
import com.gcu.data.UserNotFoundException;
import com.gcu.data.entity.UserEntity;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;



@Controller
public class PasswordResetController {

    @Autowired
    private UserDataService userService;

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm(Model model){
        model.addAttribute("pageTitle", "Forgot Password");
        return "forgot-password";
    }

    
    @PostMapping("/forgot-password")
    public String processFrogotPassword(HttpServletRequest request, Model model) throws InterruptedException {
        
        String email = request.getParameter("email");
        String token = UUID.randomUUID().toString();

        try {
            userService.updateResetPasswordToken(token, email);

            //Generate password link
            String resetLink = generateResetLink(request, token);

            //Send email
            sendEmail(email, resetLink);
 
            model.addAttribute("message", "The email was sent successfully.");
            return "message";

        } catch (UserNotFoundException ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", "Error: Email cannot be sent.");
        }
        model.addAttribute("pageTitle", "Forgot Password");
        return "forgot-password";
    }
    

    /**
     * Generate the password reset link
     * @param request 
     * @param token
     * @return
     */ 
    private String generateResetLink(HttpServletRequest request, String token) {
    return ServletUriComponentsBuilder.fromRequestUri(request)
            .replacePath("/reset-password")
            .queryParam("token", token)
            .toUriString();
    }


    private void sendEmail(String email, String resetLink) throws UnsupportedEncodingException, MessagingException{

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("contact@shuqa.com", "Shope support");
        helper.setTo(email);

        String subject = "Reset Password";
        String content = "<p style=\"font-size: 1.2em;\">To reset your password please, click the " + 
                        "<a href=\"" + resetLink + "\"><b>Link.</b></a></p>" + 
                        "<p><b>Please, ignore this email if you have not requested a password reset.</b></p>";
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);

    }


    @GetMapping("/reset-password")
    public String showResetPassword(Model model, @Param(value = "token") String token){
        
        UserEntity user = userService.getPassword(token);
        
        if(user == null){
            model.addAttribute("title", "Reset Password");
            model.addAttribute("message", "Invalid token");
            return "message";
        }

        model.addAttribute("token", token);
        model.addAttribute("pageTitle", "Reset Password");
        
        return "reset-password";
    }
    

    @PostMapping("/reset-password")
    public String processResetPassword(Model model, HttpServletRequest request){

        String token = request.getParameter("token");
        String password = request.getParameter("password");
        //String confirmPassword = request.getParameter("confirmPassword");
        String confirmPassword = request.getParameter("confirmPassword"); 

        UserEntity user = userService.getPassword(token);

        if (!(password.equals(confirmPassword))){
            model.addAttribute("token", token);
            model.addAttribute("pageTitle", "Reset Password");
            model.addAttribute("message", "Passwords do not match!");
            return "reset-password";
            
            
        }else if(user == null){
            model.addAttribute("title", "Reset Password");
            model.addAttribute("message", "Invalid token");
           
        } else {
            userService.updatePassword(user, password);
            model.addAttribute("message", "The password was changed successfullly!");
        }
        return "message";
        
    }

}
