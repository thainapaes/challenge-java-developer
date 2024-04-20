package br.com.neurotech.challenge.data;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class DatabaseInitializer {

    private final JdbcTemplate jdbcTemplate;
    private final ResourceLoader resourceLoader;

    @Autowired
    public DatabaseInitializer(JdbcTemplate jdbcTemplate, ResourceLoader resourceLoader) {
        this.jdbcTemplate = jdbcTemplate;
        this.resourceLoader = resourceLoader;
    }

    public void initializeDatabase() {
        try {
            // Carrega o arquivo SQL como um recurso do classpath
            Resource resource = resourceLoader.getResource("classpath:data.sql");

            // Executa o script SQL usando o JdbcTemplate
            jdbcTemplate.execute(IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            // Tratamento das exceções
        }
    }
}
