package desafio.api.rest.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import desafio.api.rest.model.Pessoa;
import desafio.api.rest.repository.PessoaRepository;
import desafio.api.rest.repository.TarefaRepository;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private TarefaRepository tarefaRepository;

	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Pessoa> adicionarPessoa(@RequestBody Pessoa pessoa) {

		Pessoa pessoaSalva = pessoaRepository.save(pessoa);

		return new ResponseEntity<Pessoa>(pessoaSalva, HttpStatus.OK);

	}

	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<Pessoa> atualizarPessoa(@RequestBody Pessoa pessoa) {

		Pessoa pessoaAtualizada = pessoaRepository.save(pessoa);

		return new ResponseEntity<Pessoa>(pessoaAtualizada, HttpStatus.OK);

	}

	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String deletarPessoa(@PathVariable("id") Long id) {
		pessoaRepository.deleteById(id);

		return "pessoa deletada";
	}

	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Pessoa>> listarPessoa() {

		List<Pessoa> pessoas = (List<Pessoa>) pessoaRepository.findAll();

		return new ResponseEntity<List<Pessoa>>(pessoas, HttpStatus.OK);
	}

	@GetMapping(value = "/gastos", produces = "application/json")
	public List<Object[]> buscarPessoasPorNome(@RequestParam("nome") String nome) {
		List<Pessoa> pessoas = pessoaRepository.findByNome(nome);
		List<Object[]> pessoasMediaDuracao = new ArrayList<>();

		for(Pessoa pessoa : pessoas) {
			Double mediaDuracao = tarefaRepository.calcularMediaDuracaoPorPessoa(pessoa);
			Object[] pessoaMediaDuracao = new Object[] { pessoa, mediaDuracao };
			pessoasMediaDuracao.add(pessoaMediaDuracao);
		}
		return pessoasMediaDuracao;
	}

}
