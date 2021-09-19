package br.com.compasso.partidos.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
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
import br.com.compasso.partidos.dto.PartidoDTO;
import br.com.compasso.partidos.dto.PartidoFormDTO;
import br.com.compasso.partidos.entity.Associado;
import br.com.compasso.partidos.entity.Partido;
import br.com.compasso.partidos.repository.PartidoRepository;
import br.com.compasso.partidos.service.PartidoService;

@RestController
@RequestMapping("partidos")
public class PartidoController {
	
	@Autowired
	private PartidoService service;
	
	@Autowired
	private PartidoRepository partidoRepository;
	
	@PostMapping
	public ResponseEntity<PartidoDTO> save(@RequestBody PartidoFormDTO form) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(service.save(form));
	}
	
	@GetMapping
	public ResponseEntity<Page<PartidoDTO>> findAll(@PageableDefault Pageable page, 
			@RequestParam(required = false) String ideologia) {
        return ResponseEntity.ok(service.find(page, ideologia));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PartidoDTO> findById(@PathVariable Long id){
		Optional<Partido> partido = partidoRepository.findById(id);
		if(partido.isPresent()) {
			return ResponseEntity.ok().body(service.findById(partido));
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}/associados")
	public ResponseEntity<List<Associado>> findAssociados(@PathVariable Long id){
		Optional<Partido> partido = partidoRepository.findById(id);
		if(partido.isPresent()) {
			return ResponseEntity.ok().body(service.findById(partido).retornaAssociados());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PartidoDTO> update(@PathVariable Long id, @RequestBody PartidoFormDTO form) {	
		Optional<Partido> partido = partidoRepository.findById(id);
		if(partido.isPresent()) {
			return ResponseEntity.ok().body(service.update(partido, form));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {	
		Optional<Partido> partido = partidoRepository.findById(id);
		if(partido.isPresent()) {
			service.delete(partido);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
