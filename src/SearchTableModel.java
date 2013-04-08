// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 08.04.2013 19:28:41
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   SearchTableModel.java

import java.text.DateFormat;
import java.util.ArrayList;
import javax.mail.Message;
import javax.swing.table.AbstractTableModel;

public class SearchTableModel extends AbstractTableModel
{

    public SearchTableModel()
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
        //j;
        //JVM INSTR tableswitch 0 2: default 63;
    //                   0 40
    //                   1 47
    //                   2 52;
          // goto _L1 _L2 _L3 _L4
/*_L1:
        break; /* Loop/switch isn't completed */
/*_L2:
        return message.getFrom()[0];
_L3:
        try
        {
            return message.getSubject();
        }
        catch(Exception exception) { }
        break; /* Loop/switch isn't completed */
/*_L4:
        return df.format(message.getReceivedDate());*/
        return null;
    }

    final String columnNames[] = {
        "Received From:", "Message:", "Received On:"
    };
    final Class columnClasses[] = {
        //java/lang/String, java/lang/String, java/lang/String
    };
    static final DateFormat df = DateFormat.getDateTimeInstance(2, 3);
    ArrayList list;
    private int num_messages;

}
