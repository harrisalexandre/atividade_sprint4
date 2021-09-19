package br.com.compasso.partidos.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.compasso.partidos.constant.Ideologia;
import br.com.compasso.partidos.entity.Associado;
import br.com.compasso.partidos.entity.Partido;
public class PartidoDTO {
	
	private Long id;
	
	private String nome;
	
	private String sigla;
	
	private Ideologia ideologia;
	
	private Date dataFundacao;
	
	@JsonIgnore
	 private List<Associado> associados;

	public PartidoDTO(Partido partido) {
		this.id = partido.getId();
		this.nome = partido.getNome();
		this.sigla = partido.getSigla();
		this.ideologia = partido.getIdeologia();
		this.dataFundacao = partido.getDataFundacao();
	}
	
	public String getDataFundacao() {
		Locale locale = new java.util.Locale("pt","BR");
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy", locale);
		String dataFundacao = formater.format(this.dataFundacao);
		return dataFundacao;
	}
	
	public List<Associado> retornaAssociados() {
		return this.associados;
	}
}
