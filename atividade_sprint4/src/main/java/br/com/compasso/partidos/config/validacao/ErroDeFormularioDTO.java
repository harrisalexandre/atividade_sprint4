package br.com.compasso.partidos.config.validacao;

public class ErroDeFormularioDTO {
	
	private String campo;
	private String mensagem;
	
	public ErroDeFormularioDTO(String field, String mensagem2) {
	}
	
	public String getCampo() {
		return campo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
}
