package desafio.api.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import desafio.api.rest.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	public List<Pessoa> findAllByNome(String nome);

	public List<Pessoa> findAllByNomeContainingIgnoreCase(String nome);

	public Pessoa getById(long id);
}
