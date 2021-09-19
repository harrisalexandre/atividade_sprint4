package br.com.compasso.partidos.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.compasso.partidos.dto.AssociadoDTO;
import br.com.compasso.partidos.dto.AssociadoFormDTO;
import br.com.compasso.partidos.dto.BindFormDTO;
import br.com.compasso.partidos.entity.Associado;
import br.com.compasso.partidos.entity.Partido;

public interface AssociadoService {

	AssociadoDTO save(AssociadoFormDTO form);

	Page<AssociadoDTO> find(Pageable page, String cargo);

	AssociadoDTO bind(Optional<Associado> associado, Optional<Partido> partido);

	AssociadoDTO findById(Optional<Associado> associado);

	AssociadoDTO update(Optional<Associado> associado, AssociadoFormDTO form);

	void delete(Optional<Associado> associado);

	void deleteBind(Optional<Associado> associado, Optional<Partido> partido);

}
