import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

class SMTPAuthenticator extends Authenticator
{

    public SMTPAuthenticator(String s, char ac[])
    {
        userName = s;
        password = new String(ac);
    }

    public PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication(userName, password);
    }

    private String userName;
    private String password;
}
