package sv.uesocc.edu.ingenieria.dsii2018.acceso.manejadores;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Solicitud;

/**
 *
 * @author katiro
 */
public class ManejadorCorreo {

    private String mensaje;

    public void EnviarCorreo(Solicitud solicitud, String correo) {
        try {
            mensaje = solicitud.getIdDirectorio().getUsuario() + " se la come";
            Properties propiedades = new Properties();
            propiedades.put("mail.smtp.host", "smtp.gmail.com");
            propiedades.setProperty("mail.smtp.starttls.enable", "true");
            propiedades.setProperty("mail.smtp.port", "587");
            propiedades.setProperty("mail.smtp.user", "katirox90@hotmail.com");
            propiedades.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(propiedades, null);
            BodyPart text = new MimeBodyPart();

            text.setText(mensaje);
            MimeMultipart m = new MimeMultipart();
            m.addBodyPart(text);
            MimeMessage messege = new MimeMessage(session);
            messege.setFrom(new InternetAddress("katirox90@hotmail.com"));
            messege.addRecipient(Message.RecipientType.TO, new InternetAddress("jhvv22@gmail.com"));
            messege.setSubject(solicitud.getTitulo());
            messege.setContent(m);

            Transport t = session.getTransport("smtp");
            t.connect("katirox90@hotmail.com", "dawn5000");
            t.sendMessage(messege, messege.getAllRecipients());
            t.close();

        } catch (MessagingException ex) {
            Logger.getLogger(ManejadorCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
