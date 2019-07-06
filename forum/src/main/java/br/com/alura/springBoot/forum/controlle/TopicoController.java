package br.com.alura.springBoot.forum.controlle;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.springBoot.forum.controlle.dto.DetalhesDoTopicoDTO;
import br.com.alura.springBoot.forum.controlle.dto.TopicoDTO;
import br.com.alura.springBoot.forum.controlle.form.TopicoForm;
import br.com.alura.springBoot.forum.modelo.Topico;
import br.com.alura.springBoot.forum.repository.CursoRepository;
import br.com.alura.springBoot.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
	
	@Autowired //injetando dependencia do TopicoRepository
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping
	public List<TopicoDTO> lista(String nomeCurso){ //cliente  mandar parametro na url ex: /topicos?nomeCurso=Spring ... chega spring no nomeCurso , nesse cliente passa parametro nome do curso te devolve a lista do curso
		if(nomeCurso==null) {
			List<Topico> topicos = topicoRepository.findAll();
			return TopicoDTO.converte(topicos);
		} else {
			List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso); //Spring, se usar nomeclatura na criacao do metodo o Spring monta a query, no caso usa findBy mais a numeclarura do artributo para filtrar 
			return TopicoDTO.converte(topicos);

		}
	}
	
	// nesse metodo e enviado pelo cliente o topicoForm(q e mesma coisa q um dto) so q cliente te envia os valores, a anotacao posMapping do spring e anotacao q avisa spring q iremos manipular uma requisicao pos
	// a anotacao RequestBody avisa para spring q objeto passado ira vim pelo corpo da requisicao
	@PostMapping
	public ResponseEntity<TopicoDTO> cadastrar( @Valid @RequestBody TopicoForm form, UriComponentsBuilder uriBuilder) { //retornar codigo de rest 201 temos q usar metodo created de retorno informando a aplicacao que teve modificacao ao servidor, usar UriComponetsBuilder, nessa implementacao SpringBoot traz nossa url da aplicacao completa, retorno ResponseEntity faz parte do retorno com 201 do rest
														  // avisa spring rode as validacoes anotadas na classe
		Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);
		
		 URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();//metodo buidAndExpand injeta id passado onde esta a chaves to uri converte tudo em uri e completa uri
		 return ResponseEntity.created(uri).body(new TopicoDTO(topico)); //devole esse metodo para q seja devoldido o codigo 201 ma implatação rest.
		 		
	}
	 @GetMapping("/{id}")//esse metodo e chamado ira trazer do servidor o topicoDTO, so que ele vira dinamico com seu id temos q expecificar na requisicao, porque nesse caso quero trazer apenas um topicoDTO e para isso precisamos especificar  id na requisicao
	 public DetalhesDoTopicoDTO detalhar(@PathVariable Long id) { //usaremos a anotacao @pathVariable pra avisar Spring que nosso id vai ser passado  no corpo da requisiçao sem o interrogaçao. COm isso Spring ira saber que tera q pegar da url {id} e ira botar valor na @PathVariable id long
		 Topico topico = topicoRepository.getOne(id);
		 return new DetalhesDoTopicoDTO(topico);
	 } 
	 

}
