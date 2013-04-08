import java.awt.Color;
import java.awt.Font;
import javax.mail.Message;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class SearchPanel extends JPanel
{

    public SearchPanel(Message amessage[])
    {
        initComponents();
        msgs = amessage;
        showAll();
    }

    private void showAll()
    {
        for(int i = msgs.length - 1; i >= 0; i--)
            search_tab.addMessage(msgs[i]);

    }

    private void initComponents()
    {
        jScrollPane1 = new JScrollPane();
        search_tab = new SearchTableModel();
        jTable1 = new JTable(search_tab);
        jButton1 = new JButton();
        jButton2 = new JButton();
        jButton3 = new JButton();
        jButton4 = new JButton();
        jButton5 = new JButton();
        jButton6 = new JButton();
        jButton7 = new JButton();
        setBackground(new Color(149, 179, 249));
        setForeground(new Color(255, 255, 255));
        jTable1.setBorder(new LineBorder(new Color(149, 179, 249), 2, true));
        jTable1.setFont(new Font("Times New Roman", 0, 14));
        jTable1.setSelectionBackground(new Color(149, 179, 249));
        jTable1.setSelectionForeground(Color.white);
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
        jButton1.setFont(new Font("Times New Roman", 1, 14));
        jButton1.setText("Arhieve");
        jButton2.setFont(new Font("Times New Roman", 0, 14));
        jButton2.setText("Report Spam");
        jButton3.setFont(new Font("Times New Roman", 0, 14));
        jButton3.setText("Delete");
        jButton4.setFont(new Font("Times New Roman", 1, 14));
        jButton4.setText("Arhieve");
        jButton5.setFont(new Font("Times New Roman", 0, 14));
        jButton5.setText("Report Spam");
        jButton6.setFont(new Font("Times New Roman", 0, 14));
        jButton6.setText("Delete");
        jButton7.setFont(new Font("Times New Roman", 0, 14));
        jButton7.setText("<<Back");
        GroupLayout grouplayout = new GroupLayout(this);
        setLayout(grouplayout);
        grouplayout.setHorizontalGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout.createSequentialGroup().addContainerGap().addGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, -1, 720, 32767).addGroup(grouplayout.createSequentialGroup().addComponent(jButton1).addGap(18, 18, 18).addComponent(jButton2).addGap(18, 18, 18).addComponent(jButton3)).addGroup(grouplayout.createSequentialGroup().addComponent(jButton4).addGap(18, 18, 18).addComponent(jButton5).addGap(18, 18, 18).addComponent(jButton6).addGap(18, 18, 18).addComponent(jButton7))).addContainerGap()));
        grouplayout.setVerticalGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout.createSequentialGroup().addGap(12, 12, 12).addGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jButton1).addComponent(jButton2).addComponent(jButton3)).addGap(18, 18, 18).addComponent(jScrollPane1, -2, 362, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jButton4).addComponent(jButton5).addComponent(jButton6).addComponent(jButton7)).addContainerGap(22, 32767)));
    }

    Message msgs[];
    SearchTableModel search_tab;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JButton jButton5;
    private JButton jButton6;
    private JButton jButton7;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
}
