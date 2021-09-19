package br.com.compasso.partidos.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.compasso.partidos.constant.CargoPolitico;
import br.com.compasso.partidos.constant.Sexo;

@Entity
@Table(name = "associados")
public class Associado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@Enumerated(EnumType.STRING)
	private CargoPolitico cargoPolitico;

	private Date dataNascimento;

	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	@OneToOne
	private Partido partido;

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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
