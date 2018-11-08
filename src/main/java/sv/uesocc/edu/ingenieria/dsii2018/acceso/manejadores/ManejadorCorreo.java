package sv.uesocc.edu.ingenieria.dsii2018.acceso.manejadores;

import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Directorio;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Solicitud;

/**
 *
 * @author katiro
 */
public class ManejadorCorreo {

    private String mensaje;
    private final String email = "dsiiues@gmail.com";
    private final String pass = "dsii2018";

    public void EnviarCorreo(Solicitud solicitud, Directorio correo) {
        try {
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yy");
            mensaje = "Buen día " + correo.getNombre1() + " "+correo.getNombre2()+" "+ correo.getApellido1() +" "+correo.getApellido2()+ ".\n\n"
                    + "Se le ha asignado un nueva solicitud a resolver, código de solicitud: " + solicitud.getIdSolicitud() + ", "
                    + "con una prioridad " + solicitud.getIdPrioridad().getNombre() + ", " + "creada el " 
                    + formateador.format(solicitud.getAudFechaCreacion())+ ", "
                    + "resuelva a la brevedad posible tomando en consideracion la prioridad de la solicitud, evite la acumulacion de su trabajo." + "\n\n\n"
                    + "\n\nUna descripcion brindada por el solicitante para que avance en su diagnóstico: "+ solicitud.getDescripcion()
                    + "\n\nSaludos cordiales desde el departamento de " + correo.getIdDepartamento().getNombre() + ".";
            Properties propiedades = new Properties();
            propiedades.put("mail.smtp.host", "smtp.gmail.com");
            propiedades.setProperty("mail.smtp.starttls.enable", "true");
            propiedades.setProperty("mail.smtp.port", "587");
            propiedades.setProperty("mail.smtp.user", email);
            propiedades.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(propiedades, null);
            BodyPart text = new MimeBodyPart();

            text.setText(mensaje);
            MimeMultipart m = new MimeMultipart();
            m.addBodyPart(text);
            MimeMessage messege = new MimeMessage(session);
            messege.setFrom(new InternetAddress(email));
            messege.addRecipient(Message.RecipientType.TO, new InternetAddress(correo.getCorreo()));
            messege.setSubject(solicitud.getTitulo());
            messege.setContent(m);

            Transport t = session.getTransport("smtp");
            t.connect(email, pass);
            t.sendMessage(messege, messege.getAllRecipients());
            t.close();

        } catch (MessagingException ex) {
            Logger.getLogger(ManejadorCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
