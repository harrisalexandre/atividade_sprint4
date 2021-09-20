package br.com.compasso.partidos.dto;

import javax.validation.constraints.NotNull;

public class BindFormDTO {
	
	@NotNull(message = "Id do associado é necessário")
	private Long idAssociado;
	
	@NotNull(message = "Id do partido é necessário")
	private Long idPartido;

	public Long getIdAssociado() {
		return idAssociado;
	}

	public void setIdAssociado(Long idAssociado) {
		this.idAssociado = idAssociado;
	}

	public Long getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(Long idPartido) {
		this.idPartido = idPartido;
	}


	
}
