package br.com.alura.forum.conf.Validacao;

//classe representa erro json de retorno do erro do formutario codigo 400
public class ErroDeFormularioDto {
	
	private String campo; //qual campo que deu erro
	private String erro; //qual o erro
	
	public ErroDeFormularioDto(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}
	
	
}
