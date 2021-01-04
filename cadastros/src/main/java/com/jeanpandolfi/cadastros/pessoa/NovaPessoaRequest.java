package com.jeanpandolfi.cadastros.pessoa;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class NovaPessoaRequest {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @CPF
    private String cpf;

    @NotNull
    @Past
    private LocalDate dataNascimento;

    public Pessoa toEntity(){
        return new Pessoa(this.nome, this.email, this.cpf, this.dataNascimento);
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
}
