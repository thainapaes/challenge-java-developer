package br.com.neurotech.challenge.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class NeurotechClient {
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Configuração para auto incrementar o ID
	private Integer id;
	@JsonProperty("name")
	@NotNull
	private String name;
	@JsonProperty("age")
	@NotNull
	private Integer age;
	@JsonProperty("income")
	@NotNull
	private Double income;
	@JsonIgnore
	private CreditModality creditModality;

	public NeurotechClient(String name, Integer age, Double income) {
		this.name = name;
		this.age = age;
		this.income = income;
	}

	public NeurotechClient() {}

}