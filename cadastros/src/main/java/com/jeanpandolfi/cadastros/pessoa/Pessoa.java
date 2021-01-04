package com.jeanpandolfi.cadastros.pessoa;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Entity
@Table(name = "TB_PESSOA")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @CPF
    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @NotNull
    @Past
    @Column(nullable = false)
    private LocalDate dataNascimento;

    public Pessoa(@NotBlank String nome, @NotBlank @Email String email, @NotBlank @CPF String cpf, @NotNull @Past LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public NovaPessoaResponse toResponse() {
        return new NovaPessoaResponse(this.id, this.nome, this.email, this.cpf, this.dataNascimento);
    }

    public Pessoa() {
    }

    public Long getId() {
        return id;
    }
}
