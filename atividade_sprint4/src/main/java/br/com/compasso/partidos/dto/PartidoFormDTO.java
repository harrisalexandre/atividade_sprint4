package br.com.compasso.partidos.dto;

import java.util.Date;
import java.util.GregorianCalendar;

import br.com.compasso.partidos.constant.Ideologia;
import br.com.compasso.partidos.constant.Sexo;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PartidoFormDTO {

	@NotEmpty(message = "nome is required")
	private String nome;

	@NotEmpty(message = "sigla is required")
	private String sigla;

	@NotNull(message = "ideologia is required")
	private Ideologia ideologia;

	@NotNull(message = "dataFundacao is required")
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
