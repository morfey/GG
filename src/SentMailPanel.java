import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import javax.mail.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class SentMailPanel extends JPanel
{

    public SentMailPanel(Session session1, Store store1, GMAILViewer gmailviewer)
        throws Exception
    {
        initComponents();
        registerComponents();
        session = session1;
        store = store1;
        viewer = gmailviewer;
        loadMessages(-1, -1);
    }

    public void loadMessages(int i, int j)
        throws Exception
    {
        Folder folder = store.getFolder("INBOX");
        folder.open(2);
        if(i < 0 || j < 0)
        {
            upperLimit = folder.getMessageCount();
            lowerLimit = upperLimit - 20;
        }
        if(upperLimit >= folder.getMessageCount())
        {
            upperLimit = folder.getMessageCount();
            newer.setEnabled(false);
        } else
        {
            newer.setEnabled(true);
        }
        if(lowerLimit <= 0)
        {
            lowerLimit = 1;
            older.setEnabled(false);
        } else
        {
            older.setEnabled(true);
        }
        javax.mail.Message amessage[] = folder.getMessages(lowerLimit, upperLimit);
        for(int k = amessage.length - 1; k >= 0; k--)
            outbox_tab.addMessage(amessage[k]);

        jScrollPane1.setViewportView(jTable1);
    }

    public void backToOutbox()
    {
        jTable1.setVisible(true);
        jScrollPane1.setViewportView(jTable1);
        jScrollPane1.revalidate();
        jScrollPane1.repaint();
    }

    private void showMessage(int i) throws Exception
    {
        setCursor(Cursor.getPredefinedCursor(3));
        javax.mail.Message message = outbox_tab.getMessage(i);
        MessageViewer messageviewer = new MessageViewer(this);
        messageviewer.registerComponentsOutbox();
        messageviewer.setMessage(message);
        jScrollPane1.add(messageviewer);
        jScrollPane1.setViewportView(messageviewer);
        jScrollPane1.revalidate();
        jScrollPane1.repaint();
        setCursor(Cursor.getDefaultCursor());
        setCursor(Cursor.getDefaultCursor());
        setCursor(Cursor.getDefaultCursor());
    }

    private void tableSelectionChanged(MouseEvent mouseevent) throws Exception
    {
        int i = jTable1.getSelectedRow();
        System.out.println(i);
        if(i != -1 && mouseevent.getClickCount() == 2)
            showMessage(i);
    }

    private void registerComponents()
    {
        jTable1.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent mouseevent)
            {
                try {
					tableSelectionChanged(mouseevent);
				} catch (Exception e) {
					e.printStackTrace();
				}
            }

            final SentMailPanel this$0;

            
            {
                this$0 = SentMailPanel.this;
            }
        }
);
        older.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                setCursor(Cursor.getPredefinedCursor(3));
                outbox_tab.clearAll();
                upperLimit = lowerLimit;
                lowerLimit -= 20;
                try {
					loadMessages(lowerLimit, upperLimit);
				} catch (Exception e) {
					e.printStackTrace();
				}
                setCursor(Cursor.getDefaultCursor());
                setCursor(Cursor.getDefaultCursor());
                setCursor(Cursor.getDefaultCursor());
            }

            final SentMailPanel this$0;

            
            {
                this$0 = SentMailPanel.this;
            }
        }
);
        newer.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                setCursor(Cursor.getPredefinedCursor(3));
                outbox_tab.clearAll();
                lowerLimit = upperLimit;
                upperLimit += 20;
                try {
					loadMessages(lowerLimit, upperLimit);
				} catch (Exception e) {
					e.printStackTrace();
				}
                setCursor(Cursor.getDefaultCursor());
                setCursor(Cursor.getDefaultCursor());
                setCursor(Cursor.getDefaultCursor());
            }

            final SentMailPanel this$0;

            
            {
                this$0 = SentMailPanel.this;
            }
        }
);
    }

    private void initComponents()
    {
        jPanel1 = new JPanel();
        arhieve = new JButton();
        spam = new JButton();
        delete = new JButton();
        outbox_tab = new OutboxTableModel();
        jTable1 = new JTable(outbox_tab);
        jScrollPane1 = new JScrollPane();
        arhieve1 = new JButton();
        spam1 = new JButton();
        delete1 = new JButton();
        jLabel1 = new JLabel();
        refresh = new JButton();
        older = new JButton();
        newer = new JButton();
        newest = new JButton();
        jPanel1.setBackground(new Color(155, 183, 247));
        arhieve.setFont(new Font("Times New Roman", 1, 14));
        arhieve.setText("Arhieve");
        spam.setFont(new Font("Times New Roman", 0, 14));
        spam.setText("Report Spam");
        delete.setFont(new Font("Times New Roman", 0, 14));
        delete.setText("Delete");
        jTable1.setFont(new Font("Times New Roman", 0, 14));
        jTable1.setSelectionBackground(new Color(149, 179, 249));
        jTable1.setSelectionForeground(Color.white);
        jTable1.setRowHeight(24);
        jTable1.setRowMargin(5);
        jTable1.setGridColor(new Color(149, 179, 249));
        TableColumn tablecolumn = jTable1.getColumnModel().getColumn(0);
        tablecolumn.setPreferredWidth(180);
        tablecolumn.setMaxWidth(200);
        TableColumn tablecolumn1 = jTable1.getColumnModel().getColumn(1);
        tablecolumn1.setPreferredWidth(430);
        tablecolumn1.setMaxWidth(450);
        TableColumn tablecolumn2 = jTable1.getColumnModel().getColumn(2);
        tablecolumn2.setPreferredWidth(130);
        tablecolumn2.setMaxWidth(150);
        jScrollPane1.setViewportView(jTable1);
        jScrollPane1.setBackground(new Color(255, 255, 255));
        jScrollPane1.setBorder(new LineBorder(new Color(149, 179, 249), 2, true));
        arhieve1.setFont(new Font("Times New Roman", 1, 14));
        arhieve1.setText("Arhieve");
        spam1.setFont(new Font("Times New Roman", 0, 14));
        spam1.setText("Report Spam");
        delete1.setFont(new Font("Times New Roman", 0, 14));
        delete1.setText("Delete");
        jLabel1.setFont(new Font("Times New Roman", 1, 16));
        jLabel1.setForeground(new Color(255, 255, 255));
        jLabel1.setText("SENT MAILS");
        refresh.setText("Refresh");
        older.setText("Older>>");
        newer.setText("<<Newer");
        newest.setText("<<Newest");
        GroupLayout grouplayout = new GroupLayout(jPanel1);
        jPanel1.setLayout(grouplayout);
        grouplayout.setHorizontalGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout.createSequentialGroup().addContainerGap().addGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, -1, 780, 32767).addGroup(grouplayout.createSequentialGroup().addComponent(arhieve).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(spam).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(delete).addGap(108, 108, 108).addComponent(jLabel1, -2, 108, -2).addGap(194, 194, 194).addComponent(refresh).addContainerGap()).addGroup(grouplayout.createSequentialGroup().addComponent(arhieve1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(spam1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(delete1).addGap(191, 191, 191).addComponent(newest).addGap(18, 18, 18).addComponent(older).addGap(18, 18, 18).addComponent(newer).addGap(47, 47, 47)))));
        grouplayout.setVerticalGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout.createSequentialGroup().addContainerGap().addGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(arhieve).addComponent(spam).addComponent(delete).addComponent(jLabel1).addComponent(refresh)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jScrollPane1, -2, 457, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(arhieve1).addComponent(spam1).addComponent(delete1).addComponent(older).addComponent(newer).addComponent(newest)).addContainerGap(-1, 32767)));
        GroupLayout grouplayout1 = new GroupLayout(this);
        setLayout(grouplayout1);
        grouplayout1.setHorizontalGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, -1, -1, 32767));
        grouplayout1.setVerticalGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, -1, -1, 32767));
    }

    Session session;
    Store store;
    GMAILViewer viewer;
    OutboxTableModel outbox_tab;
    int lowerLimit;
    int upperLimit;
    private JButton arhieve;
    private JButton arhieve1;
    private JButton delete;
    private JButton delete1;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JButton newer;
    private JButton newest;
    private JButton older;
    private JButton refresh;
    private JButton spam;
    private JButton spam1;
    private JTable jTable1;

}
