package br.com.compasso.partidos.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.compasso.partidos.constant.CargoPolitico;
import br.com.compasso.partidos.constant.Sexo;
import br.com.compasso.partidos.entity.Associado;
import br.com.compasso.partidos.entity.Partido;

public class AssociadoDTO {
	
	private Long id;
	private String nome;
	private CargoPolitico cargoPolitico;
	private Date dataNascimento;
	private Sexo sexo;
	private Partido partido;
	
	public AssociadoDTO(Associado associado) {
		this.id = associado.getId();
		this.nome = associado.getNome();
		this.cargoPolitico = associado.getCargoPolitico();
		this.dataNascimento = associado.getDataNascimento();
		this.sexo = associado.getSexo();
		this.partido = associado.getPartido();
	}
	
	public String getdataNascimento() {
		Locale locale = new java.util.Locale("pt","BR");
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy", locale);
		String dataNascimento = formater.format(this.dataNascimento);
		return dataNascimento;
	}
}
