package br.com.compasso.partidos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.partidos.constant.Ideologia;
import br.com.compasso.partidos.entity.Partido;

public interface PartidoRepository extends JpaRepository<Partido, Long> {

	Page<Partido> findByIdeologia(Pageable page, Ideologia ideologia);

}
