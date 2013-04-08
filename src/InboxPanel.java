import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import javax.mail.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class InboxPanel extends JPanel
{

    public InboxPanel(Session session1, Store store1, GMAILViewer gmailviewer)
        throws Exception
    {
        initComponents();
        registerComponents();
        session = session1;
        store = store1;
        viewer = gmailviewer;
        folder = store1.getFolder("INBOX");
        folder.open(2);
        loadMessages(-1, -1);
    }

    public void loadMessages(int i, int j)
        throws Exception
    {
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
        Message amessage[] = folder.getMessages(lowerLimit, upperLimit);
        for(int k = amessage.length - 1; k >= 0; k--)
            inbox_tab.addMessage(amessage[k]);

        jScrollPane1.setViewportView(table);
    }

    private void showMessage(int i)
    {
        setCursor(Cursor.getPredefinedCursor(3));
        Message message = inbox_tab.getMessage(i);
        MessageViewer messageviewer = new MessageViewer(this);
        messageviewer.registerComponentsInbox();
        messageviewer.setMessage(message);
        jScrollPane1.add(messageviewer);
        jScrollPane1.setViewportView(messageviewer);
        jScrollPane1.revalidate();
        jScrollPane1.repaint();
        setCursor(Cursor.getDefaultCursor());
        break MISSING_BLOCK_LABEL_114;
        Exception exception;
        exception;
        JOptionPane.showMessageDialog(this, exception.getMessage(), "Error Loading Message", 0);
        exception.printStackTrace();
        setCursor(Cursor.getDefaultCursor());
        break MISSING_BLOCK_LABEL_114;
        Exception exception1;
        exception1;
        setCursor(Cursor.getDefaultCursor());
        throw exception1;
    }

    public void backToInbox()
    {
        table.setVisible(true);
        jScrollPane1.setViewportView(table);
        jScrollPane1.revalidate();
        jScrollPane1.repaint();
    }

    public void reply(Message message)
    {
        try
        {
            MsgReply msgreply = new MsgReply(viewer, true);
            msgreply.reply(message);
            msgreply.setLocation(280, 200);
            msgreply.setVisible(true);
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
    }

    private void tableSelectionChanged(MouseEvent mouseevent)
    {
        int i = table.getSelectedRow();
        if(i != -1 && mouseevent.getClickCount() == 2)
            showMessage(i);
    }

    private void registerComponents()
    {
        table.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent mouseevent)
            {
                tableSelectionChanged(mouseevent);
            }

            final InboxPanel this$0;

            
            {
                this$0 = InboxPanel.this;
                super();
            }
        }
);
        older.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                setCursor(Cursor.getPredefinedCursor(3));
                inbox_tab.clearAll();
                upperLimit = lowerLimit;
                lowerLimit -= 20;
                loadMessages(lowerLimit, upperLimit);
                setCursor(Cursor.getDefaultCursor());
                break MISSING_BLOCK_LABEL_127;
                Exception exception;
                exception;
                JOptionPane.showMessageDialog(viewer, exception.getMessage(), "Error Loading Inbox", 0);
                setCursor(Cursor.getDefaultCursor());
                break MISSING_BLOCK_LABEL_127;
                Exception exception1;
                exception1;
                setCursor(Cursor.getDefaultCursor());
                throw exception1;
            }

            final InboxPanel this$0;

            
            {
                this$0 = InboxPanel.this;
                super();
            }
        }
);
        newer.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                setCursor(Cursor.getPredefinedCursor(3));
                inbox_tab.clearAll();
                lowerLimit = upperLimit;
                upperLimit += 20;
                loadMessages(lowerLimit, upperLimit);
                setCursor(Cursor.getDefaultCursor());
                break MISSING_BLOCK_LABEL_127;
                Exception exception;
                exception;
                JOptionPane.showMessageDialog(viewer, exception.getMessage(), "Error Loading Inbox", 0);
                setCursor(Cursor.getDefaultCursor());
                break MISSING_BLOCK_LABEL_127;
                Exception exception1;
                exception1;
                setCursor(Cursor.getDefaultCursor());
                throw exception1;
            }

            final InboxPanel this$0;

            
            {
                this$0 = InboxPanel.this;
                super();
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
        jScrollPane1 = new JScrollPane();
        inbox_tab = new InboxTableModel();
        table = new JTable(inbox_tab);
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
        jScrollPane1.setBackground(new Color(255, 255, 255));
        jScrollPane1.setBorder(new LineBorder(new Color(149, 179, 249), 2, true));
        table.setFont(new Font("Times New Roman", 0, 14));
        table.setSelectionBackground(new Color(149, 179, 249));
        table.setSelectionForeground(Color.white);
        table.setRowHeight(24);
        table.setRowMargin(5);
        table.setGridColor(new Color(149, 179, 249));
        TableColumn tablecolumn = table.getColumnModel().getColumn(0);
        tablecolumn.setPreferredWidth(180);
        tablecolumn.setMaxWidth(200);
        TableColumn tablecolumn1 = table.getColumnModel().getColumn(1);
        tablecolumn1.setPreferredWidth(430);
        tablecolumn1.setMaxWidth(450);
        TableColumn tablecolumn2 = table.getColumnModel().getColumn(2);
        tablecolumn2.setPreferredWidth(130);
        tablecolumn2.setMaxWidth(150);
        jScrollPane1.setViewportView(table);
        arhieve1.setFont(new Font("Times New Roman", 1, 14));
        arhieve1.setText("Arhieve");
        spam1.setFont(new Font("Times New Roman", 0, 14));
        spam1.setText("Report Spam");
        delete1.setFont(new Font("Times New Roman", 0, 14));
        delete1.setText("Delete");
        jLabel1.setFont(new Font("Times New Roman", 1, 16));
        jLabel1.setForeground(new Color(255, 255, 255));
        jLabel1.setText("INBOX");
        refresh.setText("Refresh");
        older.setText("Older>>");
        newer.setText("<<Newer");
        newest.setText("<<Newest");
        GroupLayout grouplayout = new GroupLayout(jPanel1);
        jPanel1.setLayout(grouplayout);
        grouplayout.setHorizontalGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout.createSequentialGroup().addContainerGap().addGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, -1, 780, 32767).addGroup(grouplayout.createSequentialGroup().addComponent(arhieve).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(spam).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(delete).addGap(108, 108, 108).addComponent(jLabel1, -2, 55, -2).addGap(247, 247, 247).addComponent(refresh).addContainerGap()).addGroup(grouplayout.createSequentialGroup().addComponent(arhieve1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(spam1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(delete1).addGap(191, 191, 191).addComponent(newest).addGap(18, 18, 18).addComponent(older).addGap(18, 18, 18).addComponent(newer).addGap(47, 47, 47)))));
        grouplayout.setVerticalGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout.createSequentialGroup().addContainerGap().addGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(arhieve).addComponent(spam).addComponent(delete).addComponent(jLabel1).addComponent(refresh)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jScrollPane1, -2, 457, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(arhieve1).addComponent(spam1).addComponent(delete1).addComponent(older).addComponent(newer).addComponent(newest)).addContainerGap(-1, 32767)));
        GroupLayout grouplayout1 = new GroupLayout(this);
        setLayout(grouplayout1);
        grouplayout1.setHorizontalGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, -1, -1, 32767));
        grouplayout1.setVerticalGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, -1, -1, 32767));
    }

    Session session;
    Store store;
    Folder folder;
    GMAILViewer viewer;
    InboxTableModel inbox_tab;
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
    private JTable table;

}
