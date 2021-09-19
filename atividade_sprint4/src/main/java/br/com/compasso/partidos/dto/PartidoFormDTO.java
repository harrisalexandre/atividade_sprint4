package br.com.compasso.partidos.dto;

import java.util.Date;
import java.util.GregorianCalendar;

import br.com.compasso.partidos.constant.Ideologia;
import br.com.compasso.partidos.constant.Sexo;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PartidoFormDTO {

	@NotNull
	@NotEmpty
	private String nome;

	@NotNull
	@NotEmpty
	private String sigla;

	@NotNull
	@NotEmpty
	private Ideologia ideologia;

	@NotNull
	@NotEmpty
	private Date dataFundacao;

	public void setIdeologia(String ideologiaS) {
		this.ideologia = Ideologia.valueOf(ideologiaS.toUpperCase());
	}

	public void setDataFundacao(Date dataFundacao) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dataFundacao);
		gc.add(gc.DATE, 1);
		this.dataFundacao = gc.getTime();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Ideologia getIdeologia() {
		return ideologia;
	}

	public void setIdeologia(Ideologia ideologia) {
		this.ideologia = ideologia;
	}

	public Date getDataFundacao() {
		return dataFundacao;
	}

}
