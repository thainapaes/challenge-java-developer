package br.com.neurotech.challenge.service;

import br.com.neurotech.challenge.entity.CreditModality;
import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreditService implements ICreditService{
    @Autowired
    IClientRepository clientRepository;

    @Override
    public boolean checkCredit(String clientId, VehicleModel model) {
        boolean apto = false;
        if (!clientId.isEmpty()) {
            Optional<NeurotechClient> clientOptional = clientRepository.findById(clientId);
            if(clientOptional.isPresent()) {
                Double renda = clientOptional.get().getIncome();
                if (renda >= 5000) {
                    if (renda >= 8000 && clientOptional.get().getAge() > 20) {
                        if (model.equals(VehicleModel.SUV)) {
                            apto = true;
                        } else if (renda <= 15000 && model.equals(VehicleModel.HATCH)) {
                            apto = true;
                        }
                    } else if (renda <= 15000 && model.equals(VehicleModel.HATCH)) {
                        apto = true;
                    }
                }
            }
        }
        return apto;
    }

    public List<NeurotechClient> checkAllClientsCredit(){
        List<NeurotechClient> clientList = clientRepository.findAll();
        List<NeurotechClient> listResponse = new ArrayList<>();
        for(NeurotechClient client : clientList) {
            if ((client.getAge() >= 23 && client.getAge() <= 49)
                    && client.getCreditModality().equals(CreditModality.JUROS_FIXOS)) {
                if (client.getIncome() >= 5000 && client.getIncome() <=15000) {
                    listResponse.add(client);
                }
            }
        }
        return listResponse;
    }

}
