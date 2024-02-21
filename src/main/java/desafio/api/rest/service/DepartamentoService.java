package desafio.api.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import desafio.api.rest.dto.departamento.DepartamentoCountDTO;
import desafio.api.rest.dto.departamento.DepartamentoDTO;
import desafio.api.rest.model.Departamento;
import desafio.api.rest.repository.DepartamentoRepository;

@Service
public class DepartamentoService {
	
	@Autowired
	private DepartamentoRepository departamentoRepository;

	// Cadastra
	public Optional<Departamento> postDepartamento(@RequestBody DepartamentoDTO dto) {
		if(departamentoRepository.findAllByTitulo(dto.getTitulo()).isEmpty()) {
			Departamento departamento = new Departamento(dto.getTitulo());
			return Optional.of(departamentoRepository.save(departamento));	
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados incorretos ou departamento já existente!", null);
		}
	}

	// Edita
	public Optional<Departamento> putDepartamento(@RequestBody DepartamentoDTO dto, long id) {
		if(departamentoRepository.findById(id).isPresent()) {
			Departamento departamento = departamentoRepository.getById(id);
			departamento.setTitulo(dto.getTitulo());
			return Optional.of(departamentoRepository.save(departamento));
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados incorretos!", null);
		}
	}

	// Busca todos os departamentos
	public List<Departamento> findAll() {
		return departamentoRepository.findAll();
	}
	
	// Lista departamento e quantidade de pessoas e tarefas
	public List<DepartamentoCountDTO> listarDepartamentos() {
		List<DepartamentoCountDTO> DepartamentoCountDTO = departamentoRepository.findAll().stream().map(x -> new DepartamentoCountDTO(x))
				.collect(Collectors.toList());
		return DepartamentoCountDTO;
	}

}
