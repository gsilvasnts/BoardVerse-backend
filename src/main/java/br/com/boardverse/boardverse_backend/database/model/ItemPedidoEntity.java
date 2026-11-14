package br.com.boardverse.boardverse_backend.database.model;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item_pedido")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ItemPedidoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne 
    @JoinColumn (name = "pedido_id", nullable = false)
    private PedidoEntity pedido;

    @ManyToOne 
    @JoinColumn (name = "produto_id", nullable = false)
    private ProdutoEntity produto;

    @Column (nullable = false)
    private Integer quantidade;

    @Column (name = "preco_unitario", nullable = false)
    private BigDecimal precoUnitario;
}
