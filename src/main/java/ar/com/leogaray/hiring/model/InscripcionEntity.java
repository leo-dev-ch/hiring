package ar.com.leogaray.hiring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Table(name = "inscripciones")
public class InscripcionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "vacanteId", nullable = false, updatable = false)
    private VacanteEntity vacante;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "usuarioId", nullable = false, updatable = false)
    private UsuarioEntity usuario;

    @NotNull
    @Column(name="fecha_inscripcion")
    private LocalTime fechaInscripcion;
}
