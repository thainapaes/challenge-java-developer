package br.com.neurotech.challenge.service;

import br.com.neurotech.challenge.entity.CreditModality;
import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IClientService{

    @Autowired
    private IClientRepository clientRepository;

    @Override
    public String save(NeurotechClient client) {

        if(client.getAge() >= 18) {
            if ((client.getAge() >= 21 && client.getAge() <= 65)
                    && (client.getIncome() >= 5000 && client.getIncome() <=15000)) {
                client.setCreditModality(CreditModality.JUROS_VARIAVEIS);
            } else if (client.getAge() <= 25) {
                client.setCreditModality(CreditModality.JUROS_FIXOS);
            } else if (client.getAge() > 65) {
                client.setCreditModality(CreditModality.CONSIGNADO);
            }
        }

        NeurotechClient clientSave = clientRepository.save(client);
        return clientSave.getId().toString();
    }

    @Override
    public NeurotechClient get(String id) {
        NeurotechClient neurotechClient = null;
        if(!id.isBlank()) {
            Optional<NeurotechClient> clientOptional = clientRepository.findById(id);
            if (clientOptional.isPresent()) {
                neurotechClient = new NeurotechClient();
                neurotechClient.setName(clientOptional.get().getName());
                neurotechClient.setAge(clientOptional.get().getAge());
                neurotechClient.setIncome(clientOptional.get().getIncome());
            }
        }
        return neurotechClient;
    }

    public List<NeurotechClient> getAll() {
        return clientRepository.findAll();
    }
}
