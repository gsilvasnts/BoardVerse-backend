package br.com.boardverse.boardverse_backend.database.model;

import jakarta.persistence.*;
import br.com.boardverse.boardverse_backend.database.enums.GeneroEnum;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "cliente")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GeneroEnum genero;

    @Column (nullable = false)
    private String nome;

    @Column (name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @CPF
    @Column (nullable = false, unique = true)
    private String cpf;

    @Column (nullable = false, unique = true)
    private String email;

    @Column (nullable = false)
    private String senha;

    @OneToMany(mappedBy = "cliente")
    private Set<TelefoneEntity> telefones = new HashSet<>();

    @OneToMany(mappedBy = "cliente")
    private Set<EnderecoEntity> enderecos = new HashSet<>();

    // @OneToMany(mappedBy = "cliente")
    // private Set<CartaoEntity> cartoes;

    private Boolean ativo;
}
