package desafio.api.rest.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import desafio.api.rest.model.Pessoa;
import desafio.api.rest.model.Tarefa;

@Repository
public interface TarefaRepository extends CrudRepository<Tarefa, Long>{

	@Query("SELECT t FROM Tarefa t WHERE t.pessoa IS NULL ORDER BY t.prazo ASC")
    List<Tarefa> listaTopTresTarefasAntigasPrazo(Pageable pageable);
	
	@Query("SELECT AVG(t.duracao) FROM Tarefa t WHERE t.pessoa = :pessoa")
    Double calcularMediaDuracaoPorPessoa(@Param("pessoa") Pessoa pessoa);
}
