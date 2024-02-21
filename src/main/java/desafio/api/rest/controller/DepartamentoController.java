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

import desafio.api.rest.dto.departamento.DepartamentoCountDTO;
import desafio.api.rest.dto.departamento.DepartamentoDTO;
import desafio.api.rest.model.Departamento;
import desafio.api.rest.repository.DepartamentoRepository;
import desafio.api.rest.service.DepartamentoService;

@RestController
@RequestMapping
public class DepartamentoController {

	@Autowired
	private DepartamentoService departamentoService;

	@Autowired
	private DepartamentoRepository departamentoRepository;

	// Cadastra
	@PostMapping("/post/departamento")
	public ResponseEntity<Departamento> postDepartamento(@RequestBody DepartamentoDTO dto) {
		return departamentoService.postDepartamento(dto)
				.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	// Edita
	@PutMapping("/put/departamento/{id}")
	public ResponseEntity<Departamento> putDepartamento(@RequestBody DepartamentoDTO dto, @PathVariable("id") long id) {
		return departamentoService.putDepartamento(dto, id).map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	// Exclui
	@DeleteMapping("/delete/departamento/{id}")
	public void deleteDepartamento(@PathVariable("id") long id) {
		departamentoRepository.deleteById(id);
	}

	// Lista e retorna a quantidade de pessoas e tarefas
	@GetMapping("/get/departamentos")
	public ResponseEntity<List<DepartamentoCountDTO>> getAll() {
		return ResponseEntity.ok(departamentoService.listarDepartamentos());
	}

}