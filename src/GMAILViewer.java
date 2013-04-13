import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.search.FromStringTerm;
import javax.mail.search.OrTerm;
import javax.mail.search.SubjectTerm;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GMAILViewer extends JFrame
{
	private EmailClient e = new EmailClient();
	private ClientHandler users = new ClientHandler();
	private InputStream fuser = new FileInputStream(".\\properties\\users");
	private String data[] = new String[5];
	private String pas[] = new String [5];
	private boolean flag=false;
    public GMAILViewer(Session session1, Store store1, URLName urlname) throws Throwable
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
        	setCursor(Cursor.getDefaultCursor());
            
            InboxPanel inboxpanel = new InboxPanel(session, store, this);
            scrollPane.add(inboxpanel);
            inboxpanel.setVisible(true);
            scrollPane.setViewportView(inboxpanel);
            scrollPane.revalidate();
            scrollPane.repaint();
        }
        catch(Exception exception)
        {
        	setCursor(Cursor.getPredefinedCursor(3));
        }
    }

    private void loadSentMailbox() throws Exception
    {
        setCursor(Cursor.getPredefinedCursor(3));
        SentMailPanel sentmailpanel = new SentMailPanel(session, store, this);
        scrollPane.add(sentmailpanel);
        sentmailpanel.setVisible(true);
        scrollPane.setViewportView(sentmailpanel);
        scrollPane.revalidate();
        scrollPane.repaint();
        setCursor(Cursor.getDefaultCursor());
    }

    public void setStatus(String s)
    {
        jLabel2.setText(s);
    }

    private void searchMails() throws MessagingException
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
    }

    @SuppressWarnings("null")
	private void initComponents() throws Throwable
    {
        setTitle("GG");
        BufferedReader buf=new BufferedReader(new InputStreamReader(fuser));
        jPanel1 = new JPanel();
        jLabel1 = new JButton();
        searchField = new JTextField();
        searchMail = new JButton();
        searchMail.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                try {
					searchMails();
				} catch (MessagingException e) {
					e.printStackTrace();
				}
            }

            final GMAILViewer this$0;

            
            {
                this$0 = GMAILViewer.this;
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
        jScrollPane1 = new JScrollPane();
        setDefaultCloseOperation(3);
        jPanel1.setBackground(new Color(255, 255, 255));
        jPanel1.setBorder(new LineBorder(new Color(149, 179, 249), 2, true));
        jLabel1.setText("Add Account");
        jLabel1.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent actionevent)
            {
                jLabel1ActionPerformed(actionevent);
            }

            final GMAILViewer this$0;

            
            {
                this$0 = GMAILViewer.this;
            }
        });
        this.addWindowListener(new WindowAdapter(){
        	public void windowClosing(WindowEvent e) {
            	FileWriter fstream1;
				try {
					fstream1 = new FileWriter(".\\properties\\users");
					BufferedWriter out1 = new BufferedWriter(fstream1);
	                out1.write("");
	                out1.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                
        	}
        });
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
            }
        }
);
        sentMail.setFont(new Font("Times New Roman", 1, 14));
        sentMail.setForeground(new Color(149, 179, 249));
        sentMail.setText("Sent Mail");
        sentMail.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                try {
					sentMailActionPerformed(actionevent);
				} catch (Exception e) {
					e.printStackTrace();
				}
            }

            final GMAILViewer this$0;

            
            {
                this$0 = GMAILViewer.this;
            }
        }
);
        contacts.setFont(new Font("Times New Roman", 1, 14));
        contacts.setForeground(new Color(149, 179, 249));
        contacts.setText("Contacts");
		int i=0;
		Scanner in = new Scanner(fuser);
		while (in.hasNextLine()) {
        	data[i]=in.next();
        	pas[i]=in.next();
        	i++;
        }
        jList1 = new JList(data);
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jList1);
        jList1.setFont(new Font("Times New Roman", 1, 14));
        jList1.setForeground(new Color(149, 179, 249));
        jList1.addListSelectionListener(
                new ListSelectionListener() {
                     public void valueChanged(ListSelectionEvent q) {
                    	 if (flag){
                          Object element = jList1.getSelectedValue();
                          e.flag=true;
                          int p;
                          int i=0;
                          for (i=0; i<data.length;i++ ){
                        	  if(element.toString()==data[i]){ p=i;
                        	  break;
                        	  }
                          }
                          try {
                        	  dispose();
							e.authenticate(element.toString(),pas[i]);
						} catch (Throwable e) {
							e.printStackTrace();
						}}
                    	 flag=true;
                     }
                });
        GroupLayout grouplayout1 = new GroupLayout(jPanel2);
        jPanel2.setLayout(grouplayout1);
        grouplayout1.setHorizontalGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout1.createSequentialGroup().addContainerGap().addGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, -1, 154, 32767).addComponent(contacts, javax.swing.GroupLayout.Alignment.TRAILING, -1, 154, 32767).addComponent(sentMail, javax.swing.GroupLayout.Alignment.TRAILING, -1, 154, 32767).addComponent(inbox, javax.swing.GroupLayout.Alignment.TRAILING, -1, 154, 32767).addComponent(compose, javax.swing.GroupLayout.Alignment.TRAILING, -1, 154, 32767)).addContainerGap()));
        grouplayout1.setVerticalGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout1.createSequentialGroup().addContainerGap().addComponent(compose).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(inbox).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(sentMail).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(contacts).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jScrollPane1).addContainerGap(-1, 32767)));
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
    private void jLabel1ActionPerformed(ActionEvent actionevent)
    {
    	dispose();
    	e.flag1=true;
    	e.setVisible(true);
    }
    private void inboxActionPerformed(ActionEvent actionevent)
    {
        loadInbox();
    }

    private void sentMailActionPerformed(ActionEvent actionevent) throws Exception
    {
        loadSentMailbox();
    }
    private void formWindowClosing(WindowEvent evt) throws IOException {
    	FileWriter fstream1 = new FileWriter(".\\properties\\users");
        BufferedWriter out1 = new BufferedWriter(fstream1);
        out1.write("");
        out1.close();
    }
    Session session;
    Store store;
    URLName urlName;
    private JButton compose;
    private JButton contacts;
    private JButton inbox;
    private JList jList1;
    private JScrollPane jScrollPane1;
    private JButton jLabel1;
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
