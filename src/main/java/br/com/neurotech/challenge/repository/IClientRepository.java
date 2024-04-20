package br.com.neurotech.challenge.repository;

import br.com.neurotech.challenge.entity.NeurotechClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository extends JpaRepository<NeurotechClient, String> {
}
