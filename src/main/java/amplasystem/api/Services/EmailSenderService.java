package amplasystem.api.Services;

import java.io.UnsupportedEncodingException;
import java.util.NoSuchElementException;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import amplasystem.api.exceptions.ChangePasswordException;
import amplasystem.api.exceptions.InvalidInformationException;
import amplasystem.api.utils.Regex;

import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    private static final String TEMPLATE_NAME = "RecoveryPassword";
    private static final String LOGO_IMAGE = "templates/img/logo.png";
    private static final String LOCKED_IMAGE = "templates/img/locked.png";
    private static final String LINKEDIN_IMAGE = "templates/img/linkedin.png";
    private static final String PNG_MIME = "image/png";
    public static final String MAIL_SUBJECT = "Recuperação de Senha";

    @Autowired
    private Environment environment;

    @Autowired
    private TemplateEngine htmlTemplateEngine;

    @Autowired
    private VendedorService vendedorService;

    public void sendRecoveryPasswordMail(String mail, String token)
            throws MessagingException, InvalidInformationException, UnsupportedEncodingException, NoSuchElementException{

        if (!Regex.isValidEmail(mail)) {
            throw new InvalidInformationException("Por favor, confira as informações enviadas");
        }
        vendedorService.getVendedoresByEmail(mail);

        String mailFrom = environment.getProperty("spring.mail.properties.mail.smtp.from");
        String mailFromName = environment.getProperty("mail.from.name", "Identity");

        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();

        final MimeMessageHelper email;

        email = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        email.setTo(mail);
        email.setSubject(MAIL_SUBJECT);
        email.setFrom(new InternetAddress(mailFrom, mailFromName));

        final Context ctx = new Context(LocaleContextHolder.getLocale());
        ctx.setVariable("logo", LOGO_IMAGE);
        ctx.setVariable("unlocked", LOCKED_IMAGE);
        ctx.setVariable("linkedIn", LINKEDIN_IMAGE);
        ctx.setVariable("token", token);

        final String htmlContent = this.htmlTemplateEngine.process(TEMPLATE_NAME, ctx);

        email.setText(htmlContent, true);

        ClassPathResource clr = new ClassPathResource(LOGO_IMAGE);
        ClassPathResource clr2 = new ClassPathResource(LOCKED_IMAGE);
        ClassPathResource clr3 = new ClassPathResource(LINKEDIN_IMAGE);
        email.addInline("logo", clr, PNG_MIME);
        email.addInline("unlocked", clr2, PNG_MIME);
        email.addInline("linkedIn", clr3, PNG_MIME);
        mailSender.send(mimeMessage);

    }
}
