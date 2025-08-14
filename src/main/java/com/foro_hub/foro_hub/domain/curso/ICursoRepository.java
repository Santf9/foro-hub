package com.foro_hub.foro_hub.domain.curso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ICursoRepository extends JpaRepository<Curso, Long> {
    
    Optional<Curso> findByNombre(String nombre);
}
