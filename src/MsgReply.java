import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.swing.*;

public class MsgReply extends JDialog
{

    public MsgReply(Frame frame, boolean flag)
    {
        super(frame, flag);
        initComponents();
        registerComponents();
        viewer = (GMAILViewer)frame;
    }

    public void reply(Message message)
        throws MessagingException
    {
        msg = message;
        replyTo.setText((new StringBuilder()).append(message.getFrom()[0]).append("").toString());
        subject.setText(message.getSubject());
        subject.setCaretPosition(0);
        msgArea.setFocusable(true);
        msgArea.requestFocus();
    }

    private void registerComponents()
    {
        reply.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                try
                {
                    MimeMessage mimemessage = (MimeMessage)msg.reply(false);
                    mimemessage.setFrom(msg.getFrom()[0]);
                    mimemessage.setSubject(subject.getText());
                    mimemessage.setText(msgArea.getText());
                    Transport.send(mimemessage);
                    viewer.setStatus("Your Message has been sent");
                    dispose();
                }
                catch(Exception exception)
                {
                    JOptionPane.showMessageDialog(MsgReply.this, exception.getMessage(), "Error Sending Mail", 0);
                }
            }

            final MsgReply this$0;

            
            {
                this$0 = MsgReply.this;
              
            }
        }
);
        cancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                dispose();
            }

            final MsgReply this$0;

            
            {
                this$0 = MsgReply.this;
                
            }
        }
);
    }

    private void initComponents()
    {
        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        replyTo = new JTextField();
        jLabel2 = new JLabel();
        subject = new JTextField();
        attch = new JButton();
        jScrollPane1 = new JScrollPane();
        msgArea = new JTextArea();
        reply = new JButton();
        cancel = new JButton();
        setDefaultCloseOperation(2);
        jPanel1.setBackground(new Color(149, 179, 249));
        jLabel1.setFont(new Font("Times New Roman", 1, 14));
        jLabel1.setForeground(new Color(255, 255, 255));
        jLabel1.setText("Reply To:");
        replyTo.setEditable(false);
        jLabel2.setFont(new Font("Times New Roman", 1, 14));
        jLabel2.setForeground(new Color(255, 255, 255));
        jLabel2.setText("Subject:");
        subject.setFont(new Font("Times New Roman", 0, 14));
        attch.setText("Attach file");
        attch.setVisible(false);
        msgArea.setColumns(20);
        msgArea.setRows(5);
        jScrollPane1.setViewportView(msgArea);
        reply.setText("Reply");
        cancel.setText("Cancel");
        GroupLayout grouplayout = new GroupLayout(jPanel1);
        jPanel1.setLayout(grouplayout);
        grouplayout.setHorizontalGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout.createSequentialGroup().addGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addGroup(javax.swing.GroupLayout.Alignment.LEADING, grouplayout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1, -1, 460, 32767)).addGroup(grouplayout.createSequentialGroup().addGap(40, 40, 40).addGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel1).addComponent(jLabel2)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(attch).addComponent(subject, -1, 367, 32767).addComponent(replyTo, -1, 367, 32767)))).addGroup(grouplayout.createSequentialGroup().addContainerGap().addComponent(reply).addGap(31, 31, 31).addComponent(cancel))).addContainerGap()));
        grouplayout.setVerticalGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout.createSequentialGroup().addContainerGap().addGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1).addComponent(replyTo, -2, 32, -2)).addGap(18, 18, 18).addGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel2).addComponent(subject, -2, 35, -2)).addGap(18, 18, 18).addComponent(attch).addGap(31, 31, 31).addComponent(jScrollPane1, -2, 221, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(reply).addComponent(cancel)).addContainerGap(-1, 32767)));
        GroupLayout grouplayout1 = new GroupLayout(getContentPane());
        getContentPane().setLayout(grouplayout1);
        grouplayout1.setHorizontalGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, -1, -1, 32767));
        grouplayout1.setVerticalGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, -1, -1, 32767));
        pack();
    }

    Message msg;
    GMAILViewer viewer;
    private JButton attch;
    private JButton cancel;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JTextArea msgArea;
    private JButton reply;
    private JTextField replyTo;
    private JTextField subject;


}
