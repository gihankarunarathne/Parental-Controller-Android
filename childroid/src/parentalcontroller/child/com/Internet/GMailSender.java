package parentalcontroller.child.com.Internet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Security;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class GMailSender extends javax.mail.Authenticator {   
    private String mailhost = "smtp.gmail.com";   
    private String user;   
    private String password;   
    private Session session;  
    private Multipart multipart; 
    
    /*
     * set JSSEP
     */
    static {   
        Security.addProvider(new JSSEProvider());   
    }  

    /*
     * Send the mail to Gmail
     */
    public GMailSender(String user, String password) {   
        this.user = user;   
        this.password = password;   

        Properties props = new Properties();   
        props.setProperty("mail.transport.protocol", "smtp");   
        props.setProperty("mail.host", mailhost);   
        props.put("mail.smtp.auth", "true");   
        props.put("mail.smtp.port", "465");   
        props.put("mail.smtp.socketFactory.port", "465");   
        props.put("mail.smtp.socketFactory.class",   
                "javax.net.ssl.SSLSocketFactory");   
        props.put("mail.smtp.socketFactory.fallback", "false");   
        props.setProperty("mail.smtp.quitwait", "false");   

        session = Session.getDefaultInstance(props, this);   
        //add attachments
        multipart = new MimeMultipart();
    }   

    /*get the password authontication form gmail
     */
    protected PasswordAuthentication getPasswordAuthentication() {   
        return new PasswordAuthentication(user, password);   
    }   

    /*
     * send email with text body
     */
    public synchronized void sendMail(String subject, String body, String sender, String recipients) throws Exception {   
        try{
        MimeMessage message = new MimeMessage(session);   
        DataHandler handler = new DataHandler(new ByteArrayDataSource(body.getBytes(), "text/plain"));   
        message.setSender(new InternetAddress(sender));   
        message.setSubject(subject);
        //add attachment to the mail
        if(true){
        	message.setContent(multipart);
        }
        message.setDataHandler(handler);   
        if (recipients.indexOf(',') > 0)   
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));   
        else  
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));   
        Transport.send(message);   
        }catch(Exception e){

        }
    } 
   
    /*
     * add a attachment to the mail
     */
    public void addAttachment(String filename,String subject) throws Exception { 
        BodyPart messageBodyPart = new MimeBodyPart(); 
        DataSource source = new FileDataSource(filename); 
        messageBodyPart.setDataHandler(new DataHandler(source)); 
        messageBodyPart.setFileName(filename); 
        multipart.addBodyPart(messageBodyPart);

        BodyPart messageBodyPart2 = new MimeBodyPart(); 
        messageBodyPart2.setText(subject); 

        multipart.addBodyPart(messageBodyPart2); 
    } 


    /*
     * get the source in bytes
     */
    public class ByteArrayDataSource implements DataSource {   
        private byte[] data;   
        private String type;   

        /*
         * 
         */
        public ByteArrayDataSource(byte[] data, String type) {   
            super();   
            this.data = data;   
            this.type = type;   
        }   

        public ByteArrayDataSource(byte[] data) {   
            super();   
            this.data = data;   
        }   

        public void setType(String type) {   
            this.type = type;   
        }   
        /*get content type
         */
        public String getContentType() {   
            if (type == null)   
                return "application/octet-stream";   
            else  
                return type;   
        }   

        /*get a input instrame to the file
         */
        public InputStream getInputStream() throws IOException {   
            return new ByteArrayInputStream(data);   
        }   

        /*get name
         */
        public String getName() {   
            return "ByteArrayDataSource";   
        }   

        public OutputStream getOutputStream() throws IOException {   
            throw new IOException("Not Supported");   
        }   
    }   
} 