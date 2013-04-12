import java.awt.Component;
import javax.swing.*;

class MyCellRenderer extends JLabel
    implements ListCellRenderer
{

    MyCellRenderer()
    {
    }

    public Component getListCellRendererComponent(JList jlist, Object obj, int i, boolean flag, boolean flag1)
    {
        String s = obj.toString();
        setText(s);
        setIcon(icon);
        if(flag)
        {
            setBackground(jlist.getSelectionBackground());
            setForeground(jlist.getSelectionForeground());
        } else
        {
            setBackground(jlist.getBackground());
            setForeground(jlist.getForeground());
        }
        setEnabled(jlist.isEnabled());
        setFont(jlist.getFont());
        setOpaque(true);
        return this;
    }

    static final ImageIcon icon = new ImageIcon("images\\attach.gif");

}
