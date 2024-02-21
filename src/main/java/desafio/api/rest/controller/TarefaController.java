package desafio.api.rest.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import desafio.api.rest.dto.tarefa.TarefaDTO;
import desafio.api.rest.dto.tarefa.TarefaIdDTO;
import desafio.api.rest.model.Tarefa;
import desafio.api.rest.repository.TarefaRepository;
import desafio.api.rest.service.TarefaService;

@RestController
@RequestMapping
public class TarefaController {

	@Autowired
	private TarefaService tarefaService;

	@Autowired
	private TarefaRepository tarefaRepository;

	// Cadastra	OK
	@PostMapping("/post/tarefas")
	public ResponseEntity<TarefaDTO> postTarefa(@RequestBody TarefaDTO dto) {
		return tarefaService.postTarefa(dto).map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	// Edita
	@PutMapping("/put/tarefas/{id}")
	public ResponseEntity<Tarefa> putTarefa(@RequestBody TarefaDTO dto, @PathVariable("id") long id) {
		return tarefaService.putTarefa(dto, id).map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	// Exclui	OK
	@DeleteMapping("/delete/tarefa/{id}")
	public void deleteTarefa(@PathVariable("id") long id) {
		tarefaRepository.deleteById(id);
	}

	// Busca tarefas
	@GetMapping("/get/tarefas/all")
	public ResponseEntity<List<Tarefa>> getAll() {
		return ResponseEntity.ok(tarefaService.findAll());
	}

	// Aloca uma pessoa na tarefa que tenha o mesmo departamento OK
	@PutMapping("/put/tarefas/alocar/{id}")
	public ResponseEntity<TarefaIdDTO> alocarPessoaTarefa(@RequestBody TarefaIdDTO alocarDTO,
			@PathVariable("id") long id) {
		return tarefaService.alocarPessoaTarefa(alocarDTO, id)
				.map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	// Finaliza	OK
	@PutMapping("/put/tarefas/finalizar/{id}")
	public ResponseEntity<Long> finalizarTarefa(@PathVariable("id") long finalizarTarefa) {
		return tarefaService.finalizarTarefa(finalizarTarefa)
				.map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	//Lista 3 tarefas que estejam sem pessoa alocada com os prazos mais antigos. OK
	@GetMapping("get/tarefas/pendentes")
	public List<Tarefa> listarTarefasPendentesMaisAntigas() {
        List<Tarefa> tarefasPendentes = tarefaRepository.findByPessoaIsNull();
        tarefasPendentes.sort(Comparator.comparing(Tarefa::getPrazo));
        return tarefasPendentes.stream().limit(3).collect(Collectors.toList());
    }

}