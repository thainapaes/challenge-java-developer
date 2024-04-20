package br.com.neurotech.challenge.controller;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.service.ClientService;
import jakarta.websocket.server.PathParam;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/client", produces = "application/json")
public class ClientController {

    ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/searchClient/{id}")
    public ResponseEntity<Object> searchClient(@PathParam("id") String id) {
        NeurotechClient clientResponse = clientService.get(id);

        if(clientResponse != null) {
            return ResponseEntity.status(HttpStatus.OK).body(clientResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
    }

    @GetMapping("/allClients")
    public ResponseEntity<List<NeurotechClient>> allClient() {
        List<NeurotechClient> listReturn = clientService.getAll();
        return ResponseEntity.ok(listReturn);
    }

    @PostMapping("/saveClient")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> checkCredit(@RequestBody NeurotechClient client) {
        String idResponse = clientService.save(client);

        if(idResponse.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocorreu um erro no momento da criação");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(idResponse);
        }
    }

}
