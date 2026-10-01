package br.com.boardverse.boardverse_backend.database.model;

import jakarta.persistence.*;
import lombok.*;
import br.com.boardverse.boardverse_backend.database.enums.TipoEnderecoEnum;
import br.com.boardverse.boardverse_backend.database.enums.TipoResidenciaEnum;

@Entity
@Table(name = "endereco")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EnderecoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_residencia", nullable = false)
    private TipoResidenciaEnum tipoResidencia;

    @Column (nullable = false)
    private String logradouro;

    @Column (nullable = false)
    private Integer numero;

    private String complemento;

    @Column (nullable = false)
    private String bairro;

    @Column (nullable = false)
    private String cep;
    
    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private CidadeEntity cidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_endereco", nullable = false)
    private TipoEnderecoEnum tipoEndereco;

    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;
}
