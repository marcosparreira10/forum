package br.com.alura.springBoot.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alura.springBoot.forum.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long>{ // sao passado 2 parametros classe que ira usar metodo, e tipo de aid

	List<Topico> findByCursoNome(String nomeCurso); // pra filtrar por outra entidade e so botar nome da entidade e atributo que esta querendo filtrar usando padrao de nomeclatura do spring findByCursonome se seguir padrao da query spring gera query pra vc, e so criar assinatura do metodo usando padrao de nomeclaura

	/*
	//criando na mão JPQL não quero usar conversao do springdata, fica assim nome do metodo seguindo da anotacao e query
	@Query("SELECT t FROM topico t WHERE t.curso.nome = :nomeCurso")
	List<Topico> carregarPorNomeDoCurso(@Param("nomeCurso")String nomeCurso);
	*/
}
