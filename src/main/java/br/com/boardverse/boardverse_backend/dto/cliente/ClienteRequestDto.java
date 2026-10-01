package br.com.boardverse.boardverse_backend.dto.cliente;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import br.com.boardverse.boardverse_backend.database.enums.GeneroEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class ClienteRequestDto {

    private Integer id;

    @NotNull(message = "O gênero é obrigatório")
    private GeneroEnum genero;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotNull(message = "A data de nascimento é obrigatória")
    private LocalDate dataNascimento;

    @NotBlank(message = "O CPF é obrigatório")
    @CPF(message = "O CPF digitado é inválido")
    private String cpf;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "O e-mail digitado é inválido")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{8,}$", message = "A senha deve ter pelo menos 8 caracteres, uma letra maiúscula, uma letra minúscula e um caractere especial")
    private String senha;

    @NotNull(message = "O status é obrigatório")
    private Boolean ativo;

    // @NotEmpty
    // @Valid
    // private Set<TelefoneRequest> telefones;

    // @NotEmpty
    // @Valid
    // private Set<EnderecoRequest> enderecos;

}
