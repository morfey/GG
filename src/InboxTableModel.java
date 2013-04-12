import java.text.DateFormat;
import java.util.ArrayList;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.table.AbstractTableModel;

public class InboxTableModel extends AbstractTableModel
{
    public InboxTableModel()
    {
        list = new ArrayList();
        num_messages = 0;
    }

    public void addMessage(Message message)
    {
        list.add(message);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void clearAll()
    {
        if(getRowCount() != 0)
        {
            fireTableRowsDeleted(getRowCount() - 1, getRowCount() - 1);
            list.clear();
        }
    }

    public void clearMessage(int i)
    {
        list.remove(i);
        fireTableRowsDeleted(i, i);
    }

    public Message getMessage(int i)
    {
        return (Message)list.get(i);
    }

    public int getColumnCount()
    {
        return columnNames.length;
    }

    public String getColumnName(int i)
    {
        return columnNames[i];
    }

    public Class getColumnClass(int i)
    {
        return columnClasses[i];
    }

    public int getRowCount()
    {
        return list.size();
    }

    public Object getValueAt(int i, int j)
    {
        Message message = (Message)list.get(i);
        try
        {
            return message.getSubject();
        }
        catch(Exception exception) { }
        try {
			return df.format(message.getReceivedDate());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }

    final String columnNames[] = {
        "Received From:", "Message:", "Received On:"
    };
    final Class columnClasses[] = {
    	String.class,String.class,String.class 
    };
    static final DateFormat df = DateFormat.getDateTimeInstance(2, 3);
    ArrayList list;
    private int num_messages;

}
