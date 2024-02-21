package desafio.api.rest.controller;

import java.util.List;

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

import desafio.api.rest.dto.pessoa.PessoaDTO;
import desafio.api.rest.dto.pessoa.PessoaHorasDTO;
import desafio.api.rest.dto.pessoa.PessoaMediaDTO;
import desafio.api.rest.dto.pessoa.PessoaMediaRetornoDTO;
import desafio.api.rest.model.Pessoa;
import desafio.api.rest.repository.PessoaRepository;
import desafio.api.rest.service.PessoaService;

@RestController
@RequestMapping
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private PessoaRepository pessoaRepository;

	// Cadastra	OK
	@PostMapping("/post/pessoas")
	public ResponseEntity<Pessoa> postPessoa(@RequestBody PessoaDTO dto) {
		return pessoaService.postPessoa(dto).map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	// Edita OK
	@PutMapping("/put/pessoas/{id}")
	public ResponseEntity<Pessoa> putPessoa(@RequestBody PessoaDTO dto, @PathVariable("id") long id) {
		return pessoaService.putPessoa(dto, id).map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	// Exclui OK
	@DeleteMapping("/delete/pessoas/{id}")
	public void deletePessoa(@PathVariable("id") long id) {
		pessoaRepository.deleteById(id);
	}

	// Listar pessoas trazendo nome, departamento, total horas gastas nas tarefas. OK
	@GetMapping("/get/pessoas")
	public ResponseEntity<List<PessoaHorasDTO>> findAll() {
		return ResponseEntity.ok(pessoaService.listarPessoas());
	}

	// Buscar pessoas por nome e retorna média de horas gastas por tarefa. OK
	@GetMapping("/get/pessoas/gastos")
	public ResponseEntity<List<PessoaMediaRetornoDTO>> findByNomeMediaHoras(@RequestBody PessoaMediaDTO pessoaEntrada) {
		return ResponseEntity.ok(pessoaService.buscarPessoaMediaHoras(pessoaEntrada));
	}

}
