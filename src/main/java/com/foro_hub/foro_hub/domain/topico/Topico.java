package com.foro_hub.foro_hub.domain.topico;

import com.foro_hub.foro_hub.domain.curso.Curso;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "topicos")
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String mensaje;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoTopico estado;

    @Column(nullable = false)
    private String autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    public Topico(DatosRegistroTopicoDTO datos, Curso curso) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.autor = datos.autor();
        this.curso = curso;
        this.fechaCreacion = LocalDateTime.now();
        this.estado = EstadoTopico.ABIERTO;
    }
    
    public void actualizarDatos(@Valid ActualizarTopicoDTO datos, Curso curso) {
        if(datos.titulo() != null) {
            this.titulo = datos.titulo();
        }
        if(datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
        if(datos.autor() != null) {
            this.autor = datos.autor();
        }
        if (datos.curso() != null) {
            this.curso = curso;
        }
    }
}
