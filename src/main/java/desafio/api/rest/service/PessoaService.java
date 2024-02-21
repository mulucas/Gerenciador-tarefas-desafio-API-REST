package desafio.api.rest.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import desafio.api.rest.dto.pessoa.PessoaDTO;
import desafio.api.rest.dto.pessoa.PessoaHorasDTO;
import desafio.api.rest.dto.pessoa.PessoaMediaDTO;
import desafio.api.rest.dto.pessoa.PessoaMediaRetornoDTO;
import desafio.api.rest.model.Departamento;
import desafio.api.rest.model.Pessoa;
import desafio.api.rest.repository.DepartamentoRepository;
import desafio.api.rest.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private DepartamentoRepository departamentoRepository;

	// Cadastra
	public Optional<Pessoa> postPessoa(@RequestBody PessoaDTO dto) {
		if (departamentoRepository.findById(dto.getIdDepartamento()).isPresent()) {
			Departamento departamento = new Departamento();
			departamento.setId(dto.getIdDepartamento());
			Pessoa pessoa = new Pessoa(dto.getNome(), departamento);
			return Optional.of(pessoaRepository.save(pessoa));
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Possiveis erros: Nome de Usuário já existente ou Departamento não localizado!", null);
		}
	}

	// Edita
	public Optional<Pessoa> putPessoa(@RequestBody PessoaDTO dto, long id) {

		if (pessoaRepository.findById(id).isPresent()
				&& departamentoRepository.findById(dto.getIdDepartamento()).isPresent()) {
			Pessoa pessoa = pessoaRepository.getById(id);
			Departamento departamento = new Departamento();
			departamento.setId(dto.getIdDepartamento());
			pessoa.setNome(dto.getNome());
			pessoa.setIdDepartamento(departamento);

			return Optional.of(pessoaRepository.save(pessoa));

		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados incorretos!", null);
		}
	}

	// Retorna a buscar de pessoas por nome e a média de horas gastas por tarefa
	public List<PessoaMediaRetornoDTO> buscarPessoaMediaHoras(PessoaMediaDTO pessoaEntrada) {
		List<PessoaMediaRetornoDTO> PessoaMediaRetornoDTO = pessoaRepository
				.findAllByNomeContainingIgnoreCase(pessoaEntrada.getNome()).stream()
				.map(x -> new PessoaMediaRetornoDTO(x)).collect(Collectors.toList());
		return PessoaMediaRetornoDTO;
	}

	// Retorna a listar de pessoas (nome, departamento, e total horas gastas nas tarefas)
	public List<PessoaHorasDTO> listarPessoas() {
		List<PessoaHorasDTO> PessoaHorasDTO = ((Collection<Pessoa>) pessoaRepository.findAll()).stream().map(x -> new PessoaHorasDTO(x))
				.collect(Collectors.toList());
		return PessoaHorasDTO;
	}
}
