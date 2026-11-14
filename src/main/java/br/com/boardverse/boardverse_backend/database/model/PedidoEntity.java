package br.com.boardverse.boardverse_backend.database.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import br.com.boardverse.boardverse_backend.database.enums.StatusPedidoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pedido")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private BigDecimal subtotal;

    @Column(name = "custo_envio")
    private BigDecimal custoEnvio;

    private BigDecimal total;

    @OneToMany
    private Set<PagamentoEntity> pagamentos;

    @OneToMany (mappedBy = "pedido")
    private Set<ItemPedidoEntity> itens = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private StatusPedidoEnum status;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
    
    // Cupom
    // Endereco
    // Entrega
    // Tipo entrega
}
