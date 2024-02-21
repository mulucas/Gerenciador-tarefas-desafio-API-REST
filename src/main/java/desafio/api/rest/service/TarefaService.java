package desafio.api.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import desafio.api.rest.dto.tarefa.TarefaDTO;
import desafio.api.rest.dto.tarefa.TarefaIdDTO;
import desafio.api.rest.model.Departamento;
import desafio.api.rest.model.Pessoa;
import desafio.api.rest.model.Tarefa;
import desafio.api.rest.repository.DepartamentoRepository;
import desafio.api.rest.repository.PessoaRepository;
import desafio.api.rest.repository.TarefaRepository;

@Service
public class TarefaService {

	@Autowired
	private TarefaRepository tarefaRepository;

	@Autowired
	DepartamentoRepository departamentoRepository;

	@Autowired
	PessoaRepository pessoaRepository;

	// Cadastra
	public Optional<TarefaDTO> postTarefa(@RequestBody TarefaDTO dto) {
		if (tarefaRepository.findAllByTitulo(dto.getTitulo()).isEmpty()
				&& departamentoRepository.findById(dto.getIdDepartamento()).isPresent()) {
			Departamento departamento = new Departamento();
			departamento.setId(dto.getIdDepartamento());
			Tarefa tarefa = new Tarefa(dto.getTitulo(), dto.getDescricao(), dto.getPrazo(), dto.getDuracao(),
					departamento);
			tarefaRepository.save(tarefa);
			return Optional.of(dto);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Possiveis erros: Titulo da Tarefa já existente ou Departamento não localizado!", null);
		}
	}

	// Edita
	public Optional<Tarefa> putTarefa(@RequestBody TarefaDTO dto, long id) {
		if (tarefaRepository.findById(id).isPresent()
				&& departamentoRepository.findById(dto.getIdDepartamento()).isPresent()) {
			Tarefa tarefa = tarefaRepository.getById(id);
			Departamento departamento = new Departamento();
			tarefa.setTitulo(dto.getTitulo());
			tarefa.setDescricao(dto.getDescricao());
			tarefa.setPrazo(dto.getPrazo());
			tarefa.setDuracao(dto.getDuracao());
			departamento.setId(dto.getIdDepartamento());
			tarefa.setIdDepartamento(departamento);
			return Optional.of(tarefaRepository.save(tarefa));
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Possiveis erros: Titulo da Tarefa já existente ou Departamento não localizado!", null);
		}
	}

	// Busca todas as tarefas
	public List<Tarefa> findAll() {
		return tarefaRepository.findAll();
	}

	// Aloca uma pessoa na tarefa que tenha o mesmo departamento
	public Optional<TarefaIdDTO> alocarPessoaTarefa(@RequestBody TarefaIdDTO alocarDTO, long id) {
		Tarefa tarefa = tarefaRepository.getById(alocarDTO.getIdTarefa());
		Pessoa pessoa = pessoaRepository.getById(id);
		if (tarefaRepository.findById(id).isPresent() && pessoaRepository.findById(id).isPresent()
				&& tarefa.getIdDepartamento() == pessoa.getIdDepartamento()) {
			tarefa.setPessoa(pessoa);
			tarefaRepository.save(tarefa);
			return Optional.of(alocarDTO);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Possiveis erros: Departamentos diferentes ou não encontrado pessoa/tarefa!", null);
		}
	}

	// Finaliza
	public Optional<Long> finalizarTarefa(long finalizarTarefa) {
		if (tarefaRepository.findById(finalizarTarefa).isPresent()) {
			Tarefa tarefa = tarefaRepository.getById(finalizarTarefa);
			tarefa.setFinalizado(true);
			tarefaRepository.save(tarefa);
			return Optional.of(finalizarTarefa);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID não localizado!", null);
		}
	}

}