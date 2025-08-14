package com.foro_hub.foro_hub.domain.curso;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cursos")
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;

    public Curso(String nombre, Categoria categoria) {
        this.nombre = nombre;
        this.categoria = categoria;
    }
}
