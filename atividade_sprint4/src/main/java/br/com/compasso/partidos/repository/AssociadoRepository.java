package br.com.compasso.partidos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.partidos.constant.CargoPolitico;
import br.com.compasso.partidos.entity.Associado;

public interface AssociadoRepository extends JpaRepository<Associado, Long> {

	Page<Associado> findByCargoPolitico(Pageable page, CargoPolitico cargo);

}
