package br.com.compasso.partidos.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.compasso.partidos.dto.PartidoDTO;
import br.com.compasso.partidos.dto.PartidoFormDTO;
import br.com.compasso.partidos.entity.Partido;

public interface PartidoService {

	PartidoDTO save(PartidoFormDTO form);

	Page<PartidoDTO> find(Pageable page, String ideologia);

	PartidoDTO findById(Optional<Partido> partido);

	PartidoDTO update(Optional<Partido> partido, PartidoFormDTO form);

	void delete(Optional<Partido> partido);
	
}
