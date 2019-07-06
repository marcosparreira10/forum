package br.com.alura.springBoot.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.springBoot.forum.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nomeCurso);

}
