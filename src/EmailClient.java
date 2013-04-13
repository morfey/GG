import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.*;
import javax.swing.*;

public class EmailClient extends JFrame
{
	private File fuser = new File(".\\properties\\users");
	private Store store = null;
	private int port = 995;
	public boolean flag=false;
	public boolean flag1=false;
	private Session session = null;
	//private URLName urlname = null;
    public EmailClient()
    {
    	
        initComponents();
        registerComponents();
    }
    int getport(){
    	return port;
    }
    public Store getStore() {
		return store;
	}
    private void registerComponents()
    {
        login.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                try {
					authenticate(username.getText(),new String(password.getPassword()));
				} catch (Throwable e) {
					e.printStackTrace();
				}
            }
            final EmailClient this$0;
            {
                this$0 = EmailClient.this;   
            }
        }
);
    }
    public void authenticate(String username, String password) throws Throwable
    {
    	setCursor(Cursor.getPredefinedCursor(3));
        File file = new File(".\\properties\\parameters.properties"); 
        FileWriter wrt = new FileWriter(fuser,true);
        FileInputStream fileinputstream = new FileInputStream(file);
        Properties properties = System.getProperties();
        properties.load(fileinputstream);
        properties.setProperty("mail.pop3.port", String.valueOf(port));
        properties.setProperty("mail.pop3.socketFactory.port",
        		String.valueOf(port));
        properties.setProperty("mail.pop3.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.pop3.socketFactory.fallback", "false");
        properties.put("mail.pop3.host", "pop.gmail.com");
        properties.put("mail.store.protocol", "pop3");
		Session session = null;
		session = Session.getInstance(properties);
		session.setDebug(true);
		if (flag1){
	        wrt.append("\r\n");
	        wrt.flush();}
		if (!flag){
			wrt.append(username+" "+password);
			wrt.flush();}
        Scanner in = new Scanner(fuser);
		try {
			// Connect to host
			store = session.getStore("pop3");
			store.connect("pop.gmail.com", -1, username, password);
		} catch (Exception ex) {
		
			System.exit(-1);
		}

		/*Properties props = System.getProperties();
	    props.setProperty("mail.store.protocol", "imaps");
	    Session session1 = Session.getDefaultInstance(props, null);
	    session1.setDebug(true);
	    Store store1 = session1.getStore("imap");
	    store1.connect("imap.gmail.com", 143, "morfey.ua", "kotovsk88");
	    System.out.println(store);

	    Folder[] f = store1.getDefaultFolder().list();
	    for(Folder fd:f)
	        System.out.println(">> "+fd.getName());*/
        URLName urlname = new URLName((new StringBuilder()).append("pop3").append("://").append(username).append(":").append(password).append("pop.gmail.com").toString());
        System.out.println(urlname);
        status.setText("Loading Inbox.......");
        (new GMAILViewer(session, store, urlname)).setVisible(true);
        status.setText("Loading Inbox.......");
		setCursor(Cursor.getDefaultCursor());
		dispose();
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
        jPanel1 = new JPanel();
        jLabel2 = new JLabel();
        jPanel2 = new JPanel();
        jLabel11 = new JLabel();
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
        setTitle("GG");
        setBackground(new Color(255, 255, 255));
        setResizable(false);
        jPanel1.setBackground(new Color(150, 179, 249));
        jLabel2.setBackground(new Color(255, 255, 255));
        jLabel2.setFont(new Font("Times New Roman", 1, 16));
        jLabel2.setForeground(new Color(240, 246, 247));
        jLabel2.setText("Welcome to Gmail");
        GroupLayout grouplayout = new GroupLayout(jPanel1);
        jPanel1.setLayout(grouplayout);
        grouplayout.setHorizontalGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        		.addGroup(grouplayout.createSequentialGroup()
        				.addContainerGap().addComponent(jLabel2, -2, 132, -2).addContainerGap(150, 250)));
        grouplayout.setVerticalGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        		.addGroup(grouplayout.createSequentialGroup()
        				.addContainerGap().addComponent(jLabel2, -1, -1, 250).addContainerGap()));
        jPanel2.setBackground(new Color(150, 179, 249));
        jLabel11.setBackground(new Color(255, 255, 255));
        jLabel11.setFont(new Font("Times New Roman", 0, 14));
        jLabel11.setForeground(new Color(255, 255, 255));
        jLabel11.setText("Sign in with your");
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
        grouplayout1.setHorizontalGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout1.createSequentialGroup().addGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout1.createSequentialGroup().addComponent(jLabel11)).addGroup(grouplayout1.createSequentialGroup().addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jLabel13)).addGroup(grouplayout1.createSequentialGroup().addContainerGap().addGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(password).addComponent(username)))).addContainerGap()).addGroup(grouplayout1.createSequentialGroup().addGap(84, 84, 84).addGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(signedIn).addComponent(login))));
        grouplayout1.setVerticalGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout1.createSequentialGroup().addContainerGap().addComponent(jLabel11).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jLabel13, -1, 25, 150)).addGap(19, 19, 19).addGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel14).addComponent(username, -2, -1, -2)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel15).addComponent(password, -2, -1, -2)).addGap(18, 18, 18).addComponent(signedIn).addGap(24, 24, 24).addComponent(login).addContainerGap()));
        jPanel3.setBackground(new Color(150, 179, 249));
        status.setFont(new Font("Times New Roman", 1, 14));
        status.setForeground(new Color(255, 255, 255));
        status.setText("Gmail: Email from google");
        GroupLayout grouplayout2 = new GroupLayout(jPanel3);
        jPanel3.setLayout(grouplayout2);
        grouplayout2.setHorizontalGroup(grouplayout2.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(status, -1, 200, 250));
        grouplayout2.setVerticalGroup(grouplayout2.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(status, -1, 36, 250));
        GroupLayout grouplayout3 = new GroupLayout(getContentPane());
        getContentPane().setLayout(grouplayout3);
        grouplayout3.setHorizontalGroup(grouplayout3.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, grouplayout3.createSequentialGroup().addGroup(grouplayout3.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, -1, -1, 250).addGroup(grouplayout3.createSequentialGroup().addContainerGap().addGroup(grouplayout3.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout3.createSequentialGroup().addGroup(grouplayout3.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel1, -2, -1, -2)).addGroup(grouplayout3.createSequentialGroup().addGroup(grouplayout3.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout3.createSequentialGroup().addGroup(grouplayout3.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(grouplayout3.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)))).addGap(18, 18, 18).addComponent(jPanel2, -1, -1, 250))))).addGap(36, 36, 36)));
        grouplayout3.setVerticalGroup(grouplayout3.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout3.createSequentialGroup().addGroup(grouplayout3.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout3.createSequentialGroup().addContainerGap()).addGroup(grouplayout3.createSequentialGroup().addGap(22, 22, 22).addComponent(jPanel1, -2, -1, -2))).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(grouplayout3.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout3.createSequentialGroup().addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(grouplayout3.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, grouplayout3.createSequentialGroup().addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGap(1, 1, 1))).addGroup(grouplayout3.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addGroup(grouplayout3.createSequentialGroup()).addGroup(grouplayout3.createSequentialGroup().addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, -1, 250)))).addComponent(jPanel2, -2, -1, -2)).addGap(18, 18, 18).addComponent(jPanel3, -1, -1, 250)));
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

    private JLabel jLabel11;
    private JLabel jLabel13;
    private JLabel jLabel14;
    private JLabel jLabel15;
    private JLabel jLabel2;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JButton login;
    private JPasswordField password;
    private JCheckBox signedIn;
    private JLabel status;
    private JTextField username;

}
