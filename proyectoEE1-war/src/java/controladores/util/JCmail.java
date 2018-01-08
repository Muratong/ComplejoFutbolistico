package controladores.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class JCmail {

    private String from;
    private String clave;
    private String to;
    private String asunto;
    private String contenido;

    public JCmail(String from, String clave, String to, String asunto,
            String contenido) {
        this.from = from;
        this.clave = clave;
        this.to = to;
        this.asunto = asunto;
        this.contenido = contenido;

    }

    public Boolean  send() throws MessagingException {
        Boolean envioOk = false;
        try {
            // Propiedades de la conexión
            Properties props = new Properties();

            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", from);
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            // Se compone la parte del texto
            BodyPart texto = new MimeBodyPart();
            texto.setText(contenido);

            // Una MultiParte para agrupar texto e imagen.
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);

            // Se compone el correo, dando to, from, subject y el
            // contenido.
            MimeMessage message = new MimeMessage(session);
            // message.setFrom(new InternetAddress("yo@yo.com"));
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(to));
            //ESTA LINEA VA CUANDO SE QUIERE PONER UN ACUSE DE RECIDO DE LOS MAIL Q SE ENVIAN DE MI CORREO DESDE LA APP..
           // message.addHeader("Disposition-Notification-To", "gonzalo_delcorro@hotmail.com");
            message.setSubject(asunto);
            message.setContent(multiParte);
            
            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect(from, clave);
            t.sendMessage(message, message.getAllRecipients());
            // Cierre.
            t.close();
            System.out.println("El mail se ha enviado correctamente");
            envioOk = true;
        } catch (Exception e) {
            System.out.println("El mail no se pudo enviar: "+e);
        }
        return envioOk;
    }
}
