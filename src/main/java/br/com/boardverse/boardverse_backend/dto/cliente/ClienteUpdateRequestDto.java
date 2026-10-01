package br.com.boardverse.boardverse_backend.dto.cliente;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import br.com.boardverse.boardverse_backend.database.enums.GeneroEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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
public class ClienteUpdateRequestDto {
    
    private GeneroEnum genero;

    private String nome;

    @Past(message = "A data de nascimento deve ser no passado")
    private LocalDate dataNascimento;

    @CPF
    private String cpf;

    @Email(message = "O e-mail digitado é inválido")
    private String email;

    private Boolean ativo;

}
