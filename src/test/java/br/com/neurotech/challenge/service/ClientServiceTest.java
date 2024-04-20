package br.com.neurotech.challenge.service;

import br.com.neurotech.challenge.data.DatabaseInitializer;
import br.com.neurotech.challenge.entity.NeurotechClient;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static br.com.neurotech.challenge.entity.CreditModality.JUROS_VARIAVEIS;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class ClientServiceTest {
    @Autowired
    private ClientService clientService;
    String ID = "4";
    String ID_INVALID = "20";

    @Autowired
    private DatabaseInitializer databaseInitializer;

    @BeforeEach
    public void setUp() {
        databaseInitializer.initializeDatabase();
    }

    @Test
    public void testSaveClient() {
        String name = "Paulo";
        Integer age = 20;
        Double income = 5500D;

        NeurotechClient nc = new NeurotechClient();
        nc.setName(name);
        nc.setAge(age);
        nc.setIncome(income);

        String clientN = clientService.save(nc);
        assertEquals("1", clientN);
    }


    public void testSearchClient() {
        NeurotechClient ncClient = clientService.get("2");

        assertNotNull(ncClient);
        assertEquals("Renata", ncClient.getName());
        assertEquals(35, ncClient.getAge());
        assertEquals(12000, ncClient.getIncome());
        assertEquals(JUROS_VARIAVEIS, ncClient.getCreditModality());
    }

    @Test
    public void testSearchClientInvalid() {
        NeurotechClient ncClient = clientService.get(ID_INVALID);

        assertNull(ncClient);
    }

}
