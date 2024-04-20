package br.com.neurotech.challenge.controller;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.service.ClientService;
import br.com.neurotech.challenge.service.CreditService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/credit", produces = "application/json")
public class CreditController {
    CreditService creditService;

    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping("/checkCredit/{id}/{model}")
    public ResponseEntity<String> checkCreditToVehicle(@PathParam("id") String id, @PathParam("model") VehicleModel model) {
        boolean aptoResponse = creditService.checkCredit(id, model);

        if(aptoResponse) {
            return ResponseEntity.status(HttpStatus.OK).body("O cliente está apto para ter esse tipo de modelo");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("O cliente está não está apto");
        }
    }

    @GetMapping("/checkAllClientsCredit")
    public ResponseEntity<List<NeurotechClient>> checkCreditToVehicle() {
        List<NeurotechClient> clientListResponse = creditService.checkAllClientsCredit();

        if(!clientListResponse.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(clientListResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clientListResponse);
        }
    }
}
