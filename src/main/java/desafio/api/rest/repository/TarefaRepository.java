package desafio.api.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import desafio.api.rest.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

	public List<Tarefa> findAllByTitulo(String titulo);

	List<Tarefa> findByPessoaIsNull();

	public Tarefa getById(long id);
}
