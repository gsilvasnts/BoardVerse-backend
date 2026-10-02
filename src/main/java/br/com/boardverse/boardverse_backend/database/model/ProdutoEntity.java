package br.com.boardverse.boardverse_backend.database.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "produto")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(name = "min_jogadores", nullable = false)
    private Integer minJogadores;

    @Column(name = "max_jogadores", nullable = false)
    private Integer maxJogadores;

    @Column(name = "min_tempo", nullable = false)
    private Integer minTempo;

    @Column(name = "max_tempo", nullable = false)
    private Integer maxTempo;

    @OneToMany (mappedBy = "produto")
    private Set<ItemPedidoEntity> itens = new HashSet<>();

    private Boolean ativo;

}
