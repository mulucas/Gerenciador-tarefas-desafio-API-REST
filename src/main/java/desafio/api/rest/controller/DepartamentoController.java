package desafio.api.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import desafio.api.rest.repository.PessoaRepository;

@RestController
@RequestMapping(value = "/departamentos")
public class DepartamentoController {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	/*Listar departamento e quantidade de pessoas
	 *  e tarefas (get/departamentos)*/
	
	@GetMapping(value = "/", produces = "application/json")
	public List<Object[]> listarDepartamentosQuantidadePessoas() {
        return pessoaRepository.contarPessoasTarefasPorDepartamento();
	}
}
