import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.mail.*;
import javax.mail.search.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class GMAILViewer extends JFrame
{

    public GMAILViewer(Session session1, Store store1, URLName urlname)
    {
        initComponents();
        session = session1;
        store = store1;
        urlName = urlname;
        loadInbox();
    }

    private void loadInbox()
    {
        try
        {
            setCursor(Cursor.getPredefinedCursor(3));
            InboxPanel inboxpanel = new InboxPanel(session, store, this);
            scrollPane.add(inboxpanel);
            inboxpanel.setVisible(true);
            scrollPane.setViewportView(inboxpanel);
            scrollPane.revalidate();
            scrollPane.repaint();
        }
        catch(Exception exception)
        {
            setCursor(Cursor.getDefaultCursor());
            break MISSING_BLOCK_LABEL_92;
        }
        setCursor(Cursor.getDefaultCursor());
        break MISSING_BLOCK_LABEL_92;
        Exception exception1;
        exception1;
        setCursor(Cursor.getDefaultCursor());
        throw exception1;
    }

    private void loadSentMailbox()
    {
        setCursor(Cursor.getPredefinedCursor(3));
        SentMailPanel sentmailpanel = new SentMailPanel(session, store, this);
        scrollPane.add(sentmailpanel);
        sentmailpanel.setVisible(true);
        scrollPane.setViewportView(sentmailpanel);
        scrollPane.revalidate();
        scrollPane.repaint();
        setCursor(Cursor.getDefaultCursor());
        break MISSING_BLOCK_LABEL_96;
        Exception exception;
        exception;
        exception.printStackTrace();
        setCursor(Cursor.getDefaultCursor());
        break MISSING_BLOCK_LABEL_96;
        Exception exception1;
        exception1;
        setCursor(Cursor.getDefaultCursor());
        throw exception1;
    }

    public void setStatus(String s)
    {
        jLabel2.setText(s);
    }

    private void searchMails()
    {
        setCursor(Cursor.getPredefinedCursor(3));
        if(!searchField.getText().equals(""))
        {
            OrTerm orterm = new OrTerm(new SubjectTerm(searchField.getText()), new FromStringTerm(searchField.getText()));
            Folder folder = store.getFolder("INBOX");
            folder.open(1);
            javax.mail.Message amessage[] = folder.search(orterm);
            SearchPanel searchpanel = new SearchPanel(amessage);
            scrollPane.add(searchpanel);
            searchpanel.setVisible(true);
            scrollPane.setViewportView(searchpanel);
            scrollPane.revalidate();
            scrollPane.repaint();
        }
        setCursor(Cursor.getDefaultCursor());
        break MISSING_BLOCK_LABEL_166;
        Exception exception;
        exception;
        exception.printStackTrace();
        setCursor(Cursor.getDefaultCursor());
        break MISSING_BLOCK_LABEL_166;
        Exception exception1;
        exception1;
        setCursor(Cursor.getDefaultCursor());
        throw exception1;
    }

    private void initComponents()
    {
        setTitle("GMAIL: Email from google");
        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        searchField = new JTextField();
        searchMail = new JButton();
        searchMail.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                searchMails();
            }

            final GMAILViewer this$0;

            
            {
                this$0 = GMAILViewer.this;
                super();
            }
        }
);
        signOut = new JButton();
        jPanel2 = new JPanel();
        compose = new JButton();
        inbox = new JButton();
        sentMail = new JButton();
        contacts = new JButton();
        scrollPane = new JScrollPane();
        jPanel3 = new JPanel();
        jLabel2 = new JLabel();
        setDefaultCloseOperation(3);
        jPanel1.setBackground(new Color(255, 255, 255));
        jPanel1.setBorder(new LineBorder(new Color(149, 179, 249), 2, true));
        jLabel1.setIcon(new ImageIcon("images\\logo2.gif"));
        jLabel1.setText("jLabel1");
        searchField.setFont(new Font("Times New Roman", 0, 14));
        searchField.setBorder(new LineBorder(new Color(149, 179, 249), 1, true));
        searchMail.setText("Search Mail");
        signOut.setText("Sign out");
        GroupLayout grouplayout = new GroupLayout(jPanel1);
        jPanel1.setLayout(grouplayout);
        grouplayout.setHorizontalGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout.createSequentialGroup().addGap(4, 4, 4).addComponent(jLabel1, -2, 152, -2).addGap(10, 10, 10).addComponent(searchField, -2, 323, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(searchMail, -2, 130, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 284, 32767).addComponent(signOut).addGap(18, 18, 18)));
        grouplayout.setVerticalGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1).addComponent(searchField, -2, 38, -2).addComponent(searchMail, -2, 38, -2).addComponent(signOut)));
        jPanel2.setBackground(new Color(255, 255, 255));
        jPanel2.setBorder(new LineBorder(new Color(149, 179, 249), 2, true));
        compose.setFont(new Font("Times New Roman", 1, 14));
        compose.setForeground(new Color(149, 179, 249));
        compose.setText("Compose Mail");
        compose.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                composeActionPerformed(actionevent);
            }

            final GMAILViewer this$0;

            
            {
                this$0 = GMAILViewer.this;
                super();
            }
        }
);
        inbox.setFont(new Font("Times New Roman", 1, 14));
        inbox.setForeground(new Color(149, 179, 249));
        inbox.setText("Inbox");
        inbox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                inboxActionPerformed(actionevent);
            }

            final GMAILViewer this$0;

            
            {
                this$0 = GMAILViewer.this;
                super();
            }
        }
);
        sentMail.setFont(new Font("Times New Roman", 1, 14));
        sentMail.setForeground(new Color(149, 179, 249));
        sentMail.setText("Sent Mail");
        sentMail.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                sentMailActionPerformed(actionevent);
            }

            final GMAILViewer this$0;

            
            {
                this$0 = GMAILViewer.this;
                super();
            }
        }
);
        contacts.setFont(new Font("Times New Roman", 1, 14));
        contacts.setForeground(new Color(149, 179, 249));
        contacts.setText("Contacts");
        GroupLayout grouplayout1 = new GroupLayout(jPanel2);
        jPanel2.setLayout(grouplayout1);
        grouplayout1.setHorizontalGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout1.createSequentialGroup().addContainerGap().addGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(contacts, javax.swing.GroupLayout.Alignment.TRAILING, -1, 154, 32767).addComponent(sentMail, javax.swing.GroupLayout.Alignment.TRAILING, -1, 154, 32767).addComponent(inbox, javax.swing.GroupLayout.Alignment.TRAILING, -1, 154, 32767).addComponent(compose, javax.swing.GroupLayout.Alignment.TRAILING, -1, 154, 32767)).addContainerGap()));
        grouplayout1.setVerticalGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout1.createSequentialGroup().addContainerGap().addComponent(compose).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(inbox).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(sentMail).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(contacts).addContainerGap(-1, 32767)));
        scrollPane.setBackground(new Color(255, 255, 255));
        scrollPane.setBorder(new LineBorder(new Color(149, 179, 249), 2, true));
        jPanel3.setBackground(new Color(149, 179, 249));
        jPanel3.setBorder(new LineBorder(new Color(149, 179, 249), 2, true));
        jLabel2.setBackground(new Color(153, 153, 153));
        jLabel2.setFont(new Font("Times New Roman", 1, 14));
        jLabel2.setForeground(new Color(255, 255, 255));
        jLabel2.setText("Gmail: Email from google");
        GroupLayout grouplayout2 = new GroupLayout(jPanel3);
        jPanel3.setLayout(grouplayout2);
        grouplayout2.setHorizontalGroup(grouplayout2.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout2.createSequentialGroup().addComponent(jLabel2).addContainerGap(840, 32767)));
        grouplayout2.setVerticalGroup(grouplayout2.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, -1, 31, 32767));
        GroupLayout grouplayout3 = new GroupLayout(getContentPane());
        getContentPane().setLayout(grouplayout3);
        grouplayout3.setHorizontalGroup(grouplayout3.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, -1, -1, 32767).addGroup(grouplayout3.createSequentialGroup().addComponent(jPanel2, -1, -1, 32767).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(scrollPane, -1, 818, 32767)).addComponent(jPanel3, -1, -1, 32767));
        grouplayout3.setVerticalGroup(grouplayout3.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout3.createSequentialGroup().addComponent(jPanel1, -2, -1, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(grouplayout3.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel2, -2, -1, -2).addComponent(scrollPane, -1, 576, 32767)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel3, -2, -1, -2)));
        pack();
    }

    private void composeActionPerformed(ActionEvent actionevent)
    {
        ComposeMail composemail = new ComposeMail(session, urlName, this);
        scrollPane.add(composemail);
        composemail.setVisible(true);
        scrollPane.setViewportView(composemail);
        scrollPane.revalidate();
        scrollPane.repaint();
    }

    private void inboxActionPerformed(ActionEvent actionevent)
    {
        loadInbox();
    }

    private void sentMailActionPerformed(ActionEvent actionevent)
    {
        loadSentMailbox();
    }

    Session session;
    Store store;
    URLName urlName;
    private JButton compose;
    private JButton contacts;
    private JButton inbox;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JScrollPane scrollPane;
    private JTextField searchField;
    private JButton searchMail;
    private JButton sentMail;
    private JButton signOut;




}
