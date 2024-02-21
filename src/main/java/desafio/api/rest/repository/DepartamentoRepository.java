package desafio.api.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import desafio.api.rest.model.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long>{
	
	public List<Departamento> findAllByTitulo(String titulo);

	public Departamento getById(long id);
	
}
