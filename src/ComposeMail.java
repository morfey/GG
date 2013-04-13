import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class ComposeMail extends JPanel
{

    public ComposeMail(Session session1, URLName urlname, JFrame jframe)
    {
        cur_dir = new File(".\\");
        initComponents();
        registerComponents();
        session = session1;
        urlName = urlname;
        viewer = (GMAILViewer)jframe;
        multipart = new MimeMultipart();
    }

    private void attach() throws MessagingException
    {
        File file;
        JFileChooser jfilechooser = new JFileChooser(cur_dir);
        int i = jfilechooser.showOpenDialog(viewer);
        file = jfilechooser.getSelectedFile();
        setCursor(Cursor.getPredefinedCursor(3));
        MimeBodyPart mimebodypart = new MimeBodyPart();
        FileDataSource filedatasource = new FileDataSource(file);
        mimebodypart.setDataHandler(new DataHandler(filedatasource));
        mimebodypart.setFileName(file.getName());
        multipart.addBodyPart(mimebodypart);
        deflist.addElement(file.getName());
        cur_dir = new File(file.getParent());
        setCursor(Cursor.getDefaultCursor());
    }

    private void resetFields()
    {
        toField.setText("");
        ccField.setText("");
        bccField.setText("");
        msgArea.setText("");
        subjectField.setText("");
        deflist.removeAllElements();
    }

    private void sendMessage() throws AddressException, MessagingException
    {
        setCursor(Cursor.getPredefinedCursor(3));
        if(!toField.getText().equals(""))
        {
        	String host = "smtp.gmail.com";
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "true");
            viewer.setStatus("Sending.....");
            session = Session.getInstance(props, new GMailAuthenticator((urlName.toString().substring(7)), "kotovsk88"));
            message = new MimeMessage(session);
            session.setDebug(true);
            message.setFrom(new InternetAddress(urlName.toString().substring(7)));
            message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(toField.getText()));
            if(!ccField.getText().equals(""))
                message.addRecipient(javax.mail.Message.RecipientType.CC, new InternetAddress(ccField.getText()));
            if(!bccField.getText().equals(""))
                message.addRecipient(javax.mail.Message.RecipientType.BCC, new InternetAddress(bccField.getText()));
            MimeBodyPart mimebodypart = new MimeBodyPart();
            mimebodypart.setText(msgArea.getText());
            multipart.addBodyPart(mimebodypart);
            message.setSubject(subjectField.getText());
            message.setContent(multipart);
            message.saveChanges();
            Transport transport = session.getTransport("smtp");
            transport.connect(host, urlName.toString().substring(7), "kotovsk88");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            viewer.setStatus("Message Sent");
            resetFields();
        }
        setCursor(Cursor.getDefaultCursor());
        viewer.setStatus("Your Message could not be sent.");
    }

    private void registerComponents()
    {
        send.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent actionevent)
            {
                try {
					sendActionPerformed(actionevent);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
            }
        });
        attachFile.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent actionevent)
            {
                try {
					attachFileActionPerformed(actionevent);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
            }
        });
    }
    private void sendActionPerformed(ActionEvent actionevent) throws AddressException, MessagingException
    {
    	sendMessage();
    }
    private void attachFileActionPerformed(ActionEvent actionevent) throws MessagingException
    {
    	attach();
    }
    private void initComponents()
    {
        jPanel1 = new JPanel();
        send = new JButton();
        discard = new JButton();
        save = new JButton();
        jLabel1 = new JLabel();
        toField = new JTextField();
        jLabel2 = new JLabel();
        ccField = new JTextField();
        jLabel3 = new JLabel();
        bccField = new JTextField();
        jLabel4 = new JLabel();
        subjectField = new JTextField();
        attachFile = new JButton();
        jScrollPane1 = new JScrollPane();
        msgArea = new JTextArea();
        send1 = new JButton();
        save1 = new JButton();
        discard1 = new JButton();
        jScrollPane2 = new JScrollPane();
        deflist = new DefaultListModel();
        list = new JList(deflist);
        list.setCellRenderer(new MyCellRenderer());
        jPanel1.setBackground(new Color(155, 183, 247));
        jPanel1.setBorder(new LineBorder(new Color(149, 179, 249), 2, true));
        send.setFont(new Font("Times New Roman", 1, 14));
        send.setText("Send");
        discard.setFont(new Font("Times New Roman", 0, 14));
        discard.setText("Discard");
        save.setFont(new Font("Times New Roman", 0, 14));
        save.setText("Save Now");
        jLabel1.setBackground(new Color(10, 15, 244));
        jLabel1.setFont(new Font("Times New Roman", 1, 14));
        jLabel1.setText("To:");
        toField.setFont(new Font("Times New Roman", 0, 14));
        jLabel2.setBackground(new Color(10, 15, 244));
        jLabel2.setFont(new Font("Times New Roman", 1, 14));
        jLabel2.setText("CC");
        ccField.setFont(new Font("Times New Roman", 0, 14));
        jLabel3.setFont(new Font("Times New Roman", 1, 14));
        jLabel3.setText("BCC");
        bccField.setFont(new Font("Times New Roman", 0, 14));
        jLabel4.setFont(new Font("Times New Roman", 1, 14));
        jLabel4.setText("Subject:");
        subjectField.setFont(new Font("Times New Roman", 0, 14));
        attachFile.setFont(new Font("Times New Roman", 1, 14));
        attachFile.setText("Attach a file");
        msgArea.setColumns(20);
        msgArea.setFont(new Font("Times New Roman", 0, 14));
        msgArea.setRows(5);
        jScrollPane1.setViewportView(msgArea);
        send1.setFont(new Font("Times New Roman", 1, 14));
        send1.setText("Send");
        save1.setFont(new Font("Times New Roman", 0, 14));
        save1.setText("Save Now");
        discard1.setFont(new Font("Times New Roman", 0, 14));
        discard1.setText("Discard");
        list.setBorder(new LineBorder(new Color(149, 179, 249), 2, true));
        list.setFont(new Font("Times New Roman", 0, 12));
        jScrollPane2.setViewportView(list);
        GroupLayout grouplayout = new GroupLayout(jPanel1);
        jPanel1.setLayout(grouplayout);
        grouplayout.setHorizontalGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout.createSequentialGroup().addGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout.createSequentialGroup().addGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout.createSequentialGroup().addContainerGap().addComponent(jLabel4)).addGroup(grouplayout.createSequentialGroup().addGap(30, 30, 30).addGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel2).addComponent(jLabel1).addComponent(jLabel3)))).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout.createSequentialGroup().addComponent(send).addGap(18, 18, 18).addComponent(save).addGap(22, 22, 22).addComponent(discard)).addComponent(ccField, -1, 688, 32767).addComponent(toField, javax.swing.GroupLayout.Alignment.TRAILING, -1, 688, 32767).addComponent(bccField, javax.swing.GroupLayout.Alignment.TRAILING, -1, 688, 32767).addComponent(subjectField, javax.swing.GroupLayout.Alignment.TRAILING, -1, 688, 32767).addGroup(grouplayout.createSequentialGroup().addComponent(attachFile).addGap(30, 30, 30).addComponent(jScrollPane2, -2, 198, -2)))).addGroup(grouplayout.createSequentialGroup().addContainerGap().addComponent(send1).addGap(18, 18, 18).addComponent(save1).addGap(18, 18, 18).addComponent(discard1)).addGroup(grouplayout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1, -1, 742, 32767))).addContainerGap()));
        grouplayout.setVerticalGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout.createSequentialGroup().addContainerGap().addGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(discard).addComponent(send).addComponent(save)).addGap(18, 18, 18).addGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1).addComponent(toField, -2, 38, -2)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel2).addComponent(ccField, -2, 41, -2)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel3).addComponent(bccField, -2, 43, -2)).addGap(21, 21, 21).addGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(subjectField, -2, 28, -2).addComponent(jLabel4, -2, 17, -2)).addGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout.createSequentialGroup().addGap(30, 30, 30).addComponent(attachFile)).addGroup(grouplayout.createSequentialGroup().addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jScrollPane2, -2, 89, -2))).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jScrollPane1, -2, 212, -2).addGap(18, 18, 18).addGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(send1).addComponent(save1).addComponent(discard1)).addContainerGap(-1, 32767)));
        GroupLayout grouplayout1 = new GroupLayout(this);
        setLayout(grouplayout1);
        grouplayout1.setHorizontalGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, -1, -1, 32767));
        grouplayout1.setVerticalGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, -2, -1, -2));
    }

    private Session session;
    private URLName urlName;
    private GMAILViewer viewer;
    MimeMessage message;
    Multipart multipart;
    File cur_dir;
    private JButton attachFile;
    private JTextField bccField;
    private JTextField ccField;
    private JButton discard;
    private JButton discard1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JList list;
    private DefaultListModel deflist;
    private JTextArea msgArea;
    private JButton save;
    private JButton save1;
    private JButton send;
    private JButton send1;
    private JTextField subjectField;
    private JTextField toField;


}
