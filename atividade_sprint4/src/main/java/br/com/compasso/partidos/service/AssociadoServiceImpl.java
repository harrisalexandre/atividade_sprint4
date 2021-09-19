package br.com.compasso.partidos.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.compasso.partidos.constant.CargoPolitico;
import br.com.compasso.partidos.dto.AssociadoDTO;
import br.com.compasso.partidos.dto.AssociadoFormDTO;
import br.com.compasso.partidos.entity.Associado;
import br.com.compasso.partidos.entity.Partido;
import br.com.compasso.partidos.repository.AssociadoRepository;
import br.com.compasso.partidos.repository.PartidoRepository;

@Service
public class AssociadoServiceImpl implements AssociadoService {

	@Autowired
	private AssociadoRepository associadoRepository;

	@Autowired
	private PartidoRepository partidoRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public AssociadoDTO save(AssociadoFormDTO form) {
		Associado entity = this.associadoRepository.save(mapper.map(form, Associado.class));
		return mapper.map(entity, AssociadoDTO.class);
	}

	@Override
	public Page<AssociadoDTO> find(Pageable page, String cargoS) {
		if (cargoS != null) {
			cargoS = cargoS.toUpperCase();
			CargoPolitico cargo = CargoPolitico.valueOf(cargoS);
			Page<Associado> associados = this.associadoRepository.findByCargoPolitico(page, cargo);
			Page<AssociadoDTO> associadoDTOS = associados.map(AssociadoDTO::new);
			return associadoDTOS;
		}
		Page<Associado> associados = this.associadoRepository.findAll(page);
		return associados.map(AssociadoDTO::new);
	}

	@Override
	@Transactional
	public AssociadoDTO bind(Optional<Associado> associadoOptional, Optional<Partido> partidoOptional) {
		Associado associado = associadoOptional.get();
		Partido partido = partidoOptional.get();

		associado.setPartido(partido);

		List<Associado> associados = partido.getAssociados();
		associados.add(associado);
		partido.setAssociados(associados);

		this.associadoRepository.save(associado);
		this.partidoRepository.save(partido);

		return mapper.map(associado, AssociadoDTO.class);
	}

	@Override
	public AssociadoDTO findById(Optional<Associado> associado) {
		return mapper.map(associado.get(), AssociadoDTO.class);
	}

	@Override
	@Transactional
	public AssociadoDTO update(Optional<Associado> associadoOptional, AssociadoFormDTO form) {
		Associado associado = associadoOptional.get();
		if (form.getNome() != null)
			associado.setNome(form.getNome());
		if (form.getCargoPolitico() != null)
			associado.setCargoPolitico(form.getCargoPolitico());
		if (form.getDataNascimento() != null)
			associado.setDataNascimento(form.getDataNascimento());
		if (form.getSexo() != null)
			associado.setSexo(form.getSexo());

		associadoRepository.save(associado);

		return mapper.map(associado, AssociadoDTO.class);
	}

	@Override
	@Transactional
	public void delete(Optional<Associado> associado) {
		associadoRepository.deleteById(associado.get().getId());
		return;
	}

	@Override
	@Transactional
	public void deleteBind(Optional<Associado> associadoOptional, Optional<Partido> partidoOptional) {
		Associado associado = associadoOptional.get();
		Partido partido = partidoOptional.get();

		associado.setPartido(null);
		Long idAssociado = associado.getId();

		List<Associado> associados = partido.getAssociados();
		for (int i = 0; i < associados.size(); i++) {
			Associado a = associados.get(i);
			if (a.getId().equals(idAssociado)) {
				associados.remove(a);
				break;
			}
		}
		partido.setAssociados(associados);

		this.associadoRepository.save(associado);
		this.partidoRepository.save(partido);

		return;
	}
}
