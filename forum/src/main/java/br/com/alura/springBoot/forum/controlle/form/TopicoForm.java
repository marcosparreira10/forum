package br.com.alura.springBoot.forum.controlle.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alura.springBoot.forum.modelo.Curso;
import br.com.alura.springBoot.forum.modelo.Topico;
import br.com.alura.springBoot.forum.repository.CursoRepository;

public class TopicoForm { // essa classe sera preenchida pelo usuario, os outros atributos q faz parte do topico virao automaticamente ou por meio de atributos de login, ou pelo banco ou ate mesmo automaticamente
	//java validation
	@NotNull @NotEmpty @Length(min = 5)
	private String titulo;
	
	@NotNull @NotEmpty @Length(min = 10)
	private String mensagem;
	
	@NotNull @NotEmpty @Length(min= 3)
	private String nomeCurso;
	
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	
	public Topico converter(CursoRepository cursoRepository) {
		
		Curso curso = cursoRepository.findByNome(nomeCurso);
		
		return new Topico(titulo,mensagem,curso);
	}
	
	
}
