// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 08.04.2013 19:17:52
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MessageViewer.java

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class MessageViewer extends JPanel
{

    public MessageViewer(JPanel jpanel)
    {
        initComponents();
        panel = jpanel;
    }

    private void saveFile(String s, InputStream inputstream)
        throws Exception
    {
        int i = JOptionPane.showConfirmDialog(this, (new StringBuilder()).append("Download Attachment \"").append(s).append("\"").toString(), "Attachments", 0, 2);
        if(i == 0)
        {
            File file = new File(".\\Attachments", s);
            byte abyte0[] = new byte[4096];
            for(int j = 1; file.exists(); j++)
                file = new File(".\\Attachments", (new StringBuilder()).append(s.substring(0, s.indexOf('.'))).append(j).append(s.substring(s.indexOf('.'))).toString());

            FileOutputStream fileoutputstream = new FileOutputStream(file);
            boolean flag = false;
            do
            {
                int k = inputstream.read(abyte0);
                if(k == -1)
                    break;
                fileoutputstream.write(abyte0, 0, k);
            } while(true);
            inputstream.close();
            fileoutputstream.flush();
            fileoutputstream.close();
            JOptionPane.showMessageDialog(this, (new StringBuilder()).append("Saved in ").append((new File(file.getParent())).getCanonicalPath()).toString(), "Attachments", 1);
        }
    }

    private void processMultipart(Multipart multipart, PrintWriter printwriter)
        throws Exception
    {
        boolean flag = true;
        String s = "";
        int i = 0;
        for(int j = multipart.getCount(); i < j; i++)
        {
            BodyPart bodypart = multipart.getBodyPart(i);
            String s1 = bodypart.getDisposition();
            if(s1 != null && (s1.equalsIgnoreCase("attachment") || s1.equalsIgnoreCase("inline")))
                saveFile(bodypart.getFileName(), bodypart.getInputStream());
            if(s1 != null)
                continue;
            if(bodypart.isMimeType("text/html"))
            {
                flag = false;
                InputStreamReader inputstreamreader = new InputStreamReader(bodypart.getInputStream());
                BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
                for(String s2 = bufferedreader.readLine(); s2 != null; s2 = bufferedreader.readLine())
                    printwriter.print(s2);

                bufferedreader.close();
                continue;
            }
            if(bodypart.isMimeType("text/plain"))
            {
                s = (new StringBuilder()).append(s).append((String)bodypart.getContent()).toString();
            } else
            {
                Multipart multipart1 = (Multipart)bodypart.getContent();
                processMultipart(multipart1, printwriter);
            }
        }

        if(flag)
            printwriter.print(s);
    }

    private void processHTML(PrintWriter printwriter, Message message)
        throws Exception
    {
        String s = "text/html";
        InputStream inputstream = ((MimeMessage)message).getInputStream();
        InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
        BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
        printwriter.println((new StringBuilder()).append("<h1>").append(((MimeMessage)message).getSubject()).append("</h1>").append("<hr>").append("<br>").toString());
        for(String s1 = bufferedreader.readLine(); s1 != null; s1 = bufferedreader.readLine())
            printwriter.print(s1);

        printwriter.flush();
        bufferedreader.close();
        URL url = getClass().getResource("/temp/temp.html");
        editorPane.setContentType(s);
        editorPane.setPage(url);
    }

    public void setMessage(Message message)
        throws Exception
    {
        msg = message;
        File file = new File (".\\temp\\temp.html");
        FileOutputStream fileoutputstream = new FileOutputStream(".\\temp\\temp.html");
        OutputStreamWriter outputstreamwriter = new OutputStreamWriter(fileoutputstream,"KOI8-U");
        BufferedWriter bufferedwriter = new BufferedWriter(outputstreamwriter);
        PrintWriter printwriter = new PrintWriter(bufferedwriter);
        if(message.getContentType().toLowerCase().contains("text/plain"))
        {
            Object obj = ((MimeMessage)message).getContent();
            String s = "text/plain";
            editorPane.setContentType(s);
            editorPane.setText((new StringBuilder()).append("Subject:").append(((MimeMessage)message).getSubject()).append("\n\n").append((String)obj).toString());
        } else
        if(message.getContentType().toLowerCase().contains("text/html"))
        {
            processHTML(printwriter, message);
        } else
        {
            Object obj1 = message.getContent();
            if(obj1 instanceof Multipart)
            {
                Multipart multipart = (Multipart)obj1;
                printwriter.println((new StringBuilder()).append("<h1>").append(((MimeMessage)message).getSubject()).append("</h1>").append("<hr>").append("<br>").toString());
                processMultipart(multipart, printwriter);
                printwriter.flush();
                URL url = url=file.toURL();
                editorPane.setContentType("text/html");
                editorPane.setPage(url);
            } else
            {
                processHTML(printwriter, message);
            }
        }
        printwriter.close();
    }

    public void registerComponentsInbox()
    {
        back.setText("Back to inbox");
        back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                ((InboxPanel)panel).backToInbox();
            }

            final MessageViewer this$0;
 
            {
                this$0 = MessageViewer.this;
            }
        }
);
        reply.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                ((InboxPanel)panel).reply(msg);
            }

            final MessageViewer this$0;

            
            {
                this$0 = MessageViewer.this;
            }
        }
);
        editorPane.addHyperlinkListener(new HyperlinkListener() {

            public void hyperlinkUpdate(HyperlinkEvent hyperlinkevent)
            {
                hyperlinkActivated(hyperlinkevent);
            }

            final MessageViewer this$0;

            
            {
                this$0 = MessageViewer.this;
            }
        }
);
    }

    public void registerComponentsOutbox()
    {
        back.setText("Back to Sent mail");
        reply.setVisible(false);
        back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                ((SentMailPanel)panel).backToOutbox();
            }
            final MessageViewer this$0;            
            {
                this$0 = MessageViewer.this;
            }
        }
);
    }

    private void hyperlinkActivated(HyperlinkEvent hyperlinkevent)
    {
        if(hyperlinkevent.getEventType() == javax.swing.event.HyperlinkEvent.EventType.ACTIVATED)
            showInBrowser(hyperlinkevent.getURL());
    }

    private void showInBrowser(URL url)
    {
        String s = System.getProperty("os.name").toLowerCase();
        Runtime runtime = Runtime.getRuntime();
        try
        {
            if(s.indexOf("win") >= 0)
                runtime.exec((new StringBuilder()).append("rundll32 url.dll,FileProtocolHandler ").append(url).toString());
            else
            if(s.indexOf("mac") >= 0)
                runtime.exec((new StringBuilder()).append("open ").append(url).toString());
            else
            if(s.indexOf("nix") >= 0 || s.indexOf("nux") >= 0)
            {
                String as[] = {
                    "epiphany", "firefox", "mozilla", "konqueror", "netscape", "opera", "links", "lynx"
                };
                StringBuffer stringbuffer = new StringBuffer();
                for(int i = 0; i < as.length; i++)
                    stringbuffer.append((new StringBuilder()).append(i != 0 ? " || " : "").append(as[i]).append(" \"").append(url).append("\" ").toString());

                runtime.exec(new String[] {
                    "sh", "-c", stringbuffer.toString()
                });
            } else
            {
                return;
            }
        }
        catch(Exception exception)
        {
            return;
        }
    }

    private void initComponents()
    {
        jScrollPane1 = new JScrollPane();
        editorPane = new JEditorPane();
        jPanel1 = new JPanel();
        back = new JButton();
        reply = new JButton();
        forward = new JButton();
        editorPane.setBorder(new LineBorder(new Color(149, 179, 249), 2, true));
        editorPane.setEditable(false);
        editorPane.setFont(new Font("Times New Roman", 0, 14));
        jScrollPane1.setViewportView(editorPane);
        jPanel1.setBackground(new Color(255, 255, 255));
        jPanel1.setBorder(new LineBorder(new Color(149, 179, 249), 2, true));
        back.setText("Back to inbox");
        reply.setText("Reply");
        forward.setText("Forward");
        GroupLayout grouplayout = new GroupLayout(jPanel1);
        jPanel1.setLayout(grouplayout);
        grouplayout.setHorizontalGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout.createSequentialGroup().addGap(22, 22, 22).addComponent(back).addGap(18, 18, 18).addComponent(reply).addGap(18, 18, 18).addComponent(forward).addContainerGap(160, 32767)));
        grouplayout.setVerticalGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(grouplayout.createSequentialGroup().addContainerGap().addGroup(grouplayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(back, -1, 23, 32767).addComponent(reply).addComponent(forward)).addContainerGap()));
        GroupLayout grouplayout1 = new GroupLayout(this);
        setLayout(grouplayout1);
        grouplayout1.setHorizontalGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, -1, 451, 32767).addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, -1, -1, 32767));
        grouplayout1.setVerticalGroup(grouplayout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, grouplayout1.createSequentialGroup().addComponent(jScrollPane1, -1, 323, 32767).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel1, -2, -1, -2)));
    }

    JPanel panel;
    Message msg;
    private JEditorPane editorPane;
    private JButton forward;
    private JButton back;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JButton reply;

}
