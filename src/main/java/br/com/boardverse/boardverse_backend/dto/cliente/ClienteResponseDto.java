package br.com.boardverse.boardverse_backend.dto.cliente;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import br.com.boardverse.boardverse_backend.database.enums.GeneroEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ClienteResponseDto {

    private Integer id;

    @NotNull
    private GeneroEnum genero;

    @NotBlank
    private String nome;

    @NotNull
    private LocalDate dataNascimento;

    @NotBlank
    @CPF
    private String cpf;

    @NotBlank
    @Email
    private String email;

    private Boolean ativo;

    // @NotEmpty
    // @Valid
    // private Set<TelefoneRequest> telefones;

    // @NotEmpty
    // @Valid
    // private Set<EnderecoRequest> enderecos;

}
