package br.com.boardverse.boardverse_backend.database.model;

import jakarta.persistence.*;
import lombok.*;

import br.com.boardverse.boardverse_backend.database.enums.TipoTelefoneEnum;

@Entity
@Table(name = "telefone")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TelefoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoTelefoneEnum tipo;

    @Column(nullable = false)
    private String ddd;

    @Column(nullable = false)
    private String numero;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;
}
