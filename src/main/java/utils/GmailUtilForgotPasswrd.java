package utils;
import java.util.Properties;
import javax.mail.*;

public class GmailUtilForgotPasswrd {
	public static String getResetEmailBody() {
        String host = "imap.gmail.com";
        String username = "larasiva24@gmail.com";
        String password = "luql xhjw epdz btnj";

        String body ="";

        try {
            Properties props = new Properties();
            props.put("mail.store.protocol", "imap");
            props.put("mail.imap.host", host);
            props.put("mail.imap.port", "993");
            props.put("mail.imap.ssl.enable", "true");
            props.put("mail.imap.ssl.trust", "*");

            Session session = Session.getInstance(props);
            Store store = session.getStore("imap");
            store.connect(host, username, password);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages(Math.max(0, inbox.getMessageCount() - 5), inbox.getMessageCount());

            for (int i = messages.length - 1; i >= 0; i--) {
                Message message = messages[i];
                if (message.getSubject().toLowerCase().contains("reset")) {
                    Object content = message.getContent();
                    if (content instanceof String) {
                        body = (String) content;
                    } else if (content instanceof Multipart) {
                        Multipart multipart = (Multipart) content;
                        for (int j = 0; j < multipart.getCount(); j++) {
                            BodyPart part = multipart.getBodyPart(j);
                            if (part.isMimeType("text/plain") || part.isMimeType("text/html")) {
                                body = part.getContent().toString();
                                break;
                            }
                        }
                    }
                    break;
                }
            }



            inbox.close(false);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return body;
    }

}
