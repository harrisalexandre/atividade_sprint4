package br.com.compasso.partidos.dto;

import java.util.Date;
import java.util.GregorianCalendar;

import br.com.compasso.partidos.constant.CargoPolitico;
import br.com.compasso.partidos.constant.Sexo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AssociadoFormDTO {

	@NotEmpty(message = "nome is required")
	private String nome;

	@NotNull(message = "cargoPolitico is required")
	private CargoPolitico cargoPolitico;

	@NotNull(message = "data is required")
	private Date dataNascimento;

	@NotNull(message = "sexo is required")
	private Sexo sexo;

	public void setCargoPolitico(String cargoPoliticoS) {
		this.cargoPolitico = CargoPolitico.valueOf(cargoPoliticoS.toUpperCase());
	}

	public void setSexo(String sexoS) {
		this.sexo = Sexo.valueOf(sexoS.toUpperCase());
	}

	public void setDataNascimento(Date dataNascimento) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dataNascimento);
		gc.add(gc.DATE, 1);
		this.dataNascimento = gc.getTime();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public CargoPolitico getCargoPolitico() {
		return cargoPolitico;
	}

	public void setCargoPolitico(CargoPolitico cargoPolitico) {
		this.cargoPolitico = cargoPolitico;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

}
