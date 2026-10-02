package br.com.boardverse.boardverse_backend.database.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item_carrinho")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ItemCarrinhoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne  
    @Column(name = "carrinho_id", nullable = false)
    private CarrinhoEntity carrinho;

    @ManyToOne  
    @Column(name = "produto_id", nullable = false)
    private ProdutoEntity produto;

    @Column(nullable = false)
    private Integer quantidade;
}
