package br.com.seunome.portfoliobackend.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    // 1. Injeta o JavaMailSender que configuramos no properties
    @Autowired
    private JavaMailSender mailSender;

    // 2. Defina o e-mail que VAI RECEBER as mensagens
    // (Pode ser o mesmo que você usou no properties)
    private final String toEmail = "seu-email-aqui@gmail.com";

    public void sendEmail(ContactRequest request) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            // De: O e-mail configurado (seu)
            // (Não podemos usar o email do 'request' aqui, o Gmail bloquearia)
            mailMessage.setFrom("seu-email-aqui@gmail.com"); 

            // Para: O seu e-mail de destino (definido acima)
            mailMessage.setTo(toEmail); 

            // O mais importante:
            // Permite que você clique em "Responder" e responda para o email do usuário
            mailMessage.setReplyTo(request.email());

            // Assunto do E-mail
            mailMessage.setSubject("Nova Mensagem do Portfólio de: " + request.name());

            // Corpo do E-mail
            String emailBody = "Você recebeu uma nova mensagem de contato:\n\n" +
                               "Nome: " + request.name() + "\n" +
                               "Email: " + request.email() + "\n\n" +
                               "Mensagem:\n" +
                               request.message();
            
            mailMessage.setText(emailBody);

            // Envia o e-mail
            mailSender.send(mailMessage);

        } catch (Exception e) {
            // (Em um app real, trataríamos esse erro melhor)
            e.printStackTrace();
        }
    }
}