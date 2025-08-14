package com.foro_hub.foro_hub.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITopicoRepository extends JpaRepository<Topico, Long> {
    
    @Query("SELECT COUNT(t) > 0 FROM Topico t WHERE t.titulo = :titulo AND t.mensaje = :mensaje")
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
}
