package ar.com.leogaray.hiring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Table(name = "vacantes")
public class VacanteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(name = "nombre")
    private String nombre;
    @NotNull
    @Column(name = "descripcion")
    private String descripcion;
    @NotNull
    @Column(name = "fecha")
    private LocalDate fecha;

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

    @Column(name = "detalle")
    private String detalle;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "categoria_id", nullable = false, updatable = false)
    private CategoriaEntity categoriaEntity;
}
