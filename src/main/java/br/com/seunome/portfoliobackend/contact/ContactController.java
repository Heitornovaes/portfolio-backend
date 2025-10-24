package br.com.seunome.portfoliobackend.contact;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// @CrossOrigin permite que seu front-end (em outra porta) chame esta API
@CrossOrigin(origins = "*") 
@RestController
@RequestMapping("/api/contact")
public class ContactController {

    // 1. Injeta o Service (injeção via construtor, é a melhor prática)
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    // 2. Define o método POST
    // Quando o front-end enviar um POST para /api/contact, este método será chamado
    @PostMapping
    public ResponseEntity<Void> sendEmail(@RequestBody ContactRequest request) {
        
        // 3. Chama o service para fazer o trabalho sujo
        contactService.sendEmail(request);
        
        // 4. Retorna uma resposta "200 OK"
        return ResponseEntity.ok().build();
    }
}