package br.com.compasso.partidos.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.compasso.partidos.constant.Ideologia;
import br.com.compasso.partidos.dto.PartidoDTO;
import br.com.compasso.partidos.dto.PartidoFormDTO;
import br.com.compasso.partidos.entity.Partido;
import br.com.compasso.partidos.repository.PartidoRepository;

@Service
public class PartidoServiceImpl implements PartidoService {

	@Autowired
	private PartidoRepository repository;

	@Autowired
	private ModelMapper mapper;

	@Override
	@Transactional
	public PartidoDTO save(PartidoFormDTO form) {
		Partido entity = this.repository.save(mapper.map(form, Partido.class));
		return mapper.map(entity, PartidoDTO.class);
	}

	@Override
	public Page<PartidoDTO> find(Pageable page, String ideologiaS) {
		if (ideologiaS != null) {
			ideologiaS = ideologiaS.toUpperCase();
			Ideologia ideologia = Ideologia.valueOf(ideologiaS);
			Page<Partido> partidos = this.repository.findByIdeologia(page, ideologia);
			Page<PartidoDTO> partidoDTOS = partidos.map(PartidoDTO::new);
			return partidoDTOS;
		}
		Page<Partido> partidos = this.repository.findAll(page);
		return partidos.map(PartidoDTO::new);
	}

	@Override
	public PartidoDTO findById(Optional<Partido> partido) {
		return mapper.map(partido.get(), PartidoDTO.class);
	}

	@Override
	@Transactional
	public PartidoDTO update(Optional<Partido> partidoOptional, PartidoFormDTO form) {
		Partido partido = partidoOptional.get();
		if (form.getNome() != null)
			partido.setNome(form.getNome());
		if (form.getSigla() != null)
			partido.setSigla(form.getSigla());
		if (form.getIdeologia() != null)
			partido.setIdeologia(form.getIdeologia());
		if (form.getDataFundacao() != null)
			partido.setDataFundacao(form.getDataFundacao());

		this.repository.save(partido);

		return mapper.map(partido, PartidoDTO.class);
	}

	@Override
	@Transactional
	public void delete(Optional<Partido> partido) {
		repository.deleteById(partido.get().getId());
		return;
	}

}
