package br.com.boardverse.boardverse_backend.database.model;

import br.com.boardverse.boardverse_backend.database.enums.EstadoEnum;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cidade")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CidadeEntity {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoEnum estado;
}
