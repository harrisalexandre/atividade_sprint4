package br.com.compasso.partidos.controller;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.partidos.dto.AssociadoDTO;
import br.com.compasso.partidos.dto.AssociadoFormDTO;
import br.com.compasso.partidos.dto.BindFormDTO;
import br.com.compasso.partidos.entity.Associado;
import br.com.compasso.partidos.entity.Partido;
import br.com.compasso.partidos.repository.AssociadoRepository;
import br.com.compasso.partidos.repository.PartidoRepository;
import br.com.compasso.partidos.service.AssociadoService;

@RestController
@RequestMapping("associados")
public class AssociadoController {
	
	@Autowired
	private AssociadoService service;
	
	@Autowired
	private AssociadoRepository associadoRepository;
	
	@Autowired
	private PartidoRepository partidoRepository;
	
	@PostMapping
	public ResponseEntity<AssociadoDTO> save(@RequestBody AssociadoFormDTO form) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(service.save(form));
	}
	
	@GetMapping
	public ResponseEntity<Page<AssociadoDTO>> findAll(@PageableDefault Pageable page,
			@RequestParam(required = false) String cargo) {
		return ResponseEntity.ok(service.find(page, cargo));
	}
	
	@PostMapping("/partidos")
	public ResponseEntity<AssociadoDTO> bind(@RequestBody BindFormDTO form) {
		Optional<Associado> associado = associadoRepository.findById(form.getIdAssociado());
		Optional<Partido> partido = partidoRepository.findById(form.getIdPartido());
		if(associado.isPresent() && partido.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(service.bind(associado, partido));
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AssociadoDTO> findById(@PathVariable Long id){
		Optional<Associado> associado = associadoRepository.findById(id);
		if(associado.isPresent()) {
			return ResponseEntity.ok().body(service.findById(associado));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<AssociadoDTO> update(@PathVariable Long id, @RequestBody AssociadoFormDTO form) {	
		Optional<Associado> associado = associadoRepository.findById(id);
		if(associado.isPresent()) {
			return ResponseEntity.ok().body(service.update(associado, form));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {	
		Optional<Associado> associado = associadoRepository.findById(id);
		if(associado.isPresent()) {
			service.delete(associado);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{idA}/partidos/{idP}")
	@Transactional
	public ResponseEntity<?> deleteBind(@PathVariable Long idA, 
			@PathVariable Long idP) {	
		Optional<Associado> associado = associadoRepository.findById(idA);
		Optional<Partido> partido = partidoRepository.findById(idP);
		if(associado.isPresent() && partido.isPresent()) {
			service.deleteBind(associado, partido);
			return ResponseEntity.ok().body(associado.get().getNome() 
					+ " removido do partido "
					+ partido.get().getNome());
		}
		return ResponseEntity.notFound().build();
	}
}
