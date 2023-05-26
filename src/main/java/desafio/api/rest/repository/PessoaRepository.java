package desafio.api.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import desafio.api.rest.model.Pessoa;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long>{

	@Query("SELECT p.departamento, COUNT(DISTINCT p.id), COUNT(t.id) FROM Pessoa p LEFT JOIN p.tarefas t GROUP BY p.departamento")
    List<Object[]> contarPessoasTarefasPorDepartamento();
    
    List<Pessoa> findByNome(String nome);
}
