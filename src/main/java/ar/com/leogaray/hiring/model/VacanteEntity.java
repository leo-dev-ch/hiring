package ar.com.leogaray.hiring.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
@Table(name = "vacantes")
public class VacanteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    @NotNull
    @Column(name = "descripcion")
    private String descripcion;
    @NotNull
    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "salario")
    private long salario;

    @Column(name = "destacado")
    private Boolean destacado;
    @Column(name = "imagen")
    private String imagen;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoVacante estado;

    @NotNull
    @Column(name = "detalle")
    private String detalle;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "categoria_id", nullable = false, updatable = false)
    private CategoriaEntity categoriaEntity;
}
