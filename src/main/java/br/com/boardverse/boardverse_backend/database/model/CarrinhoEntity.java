package br.com.boardverse.boardverse_backend.database.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "carrinho")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CarrinhoEntity {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne 
    @JoinColumn (name = "cliente_id", nullable = false, unique = true)
    private ClienteEntity cliente;

    @OneToMany (mappedBy = "carrinho")
    private Set<ItemCarrinhoEntity> itens = new HashSet<>();

    private boolean status;

    private LocalDateTime createdAt;
}
