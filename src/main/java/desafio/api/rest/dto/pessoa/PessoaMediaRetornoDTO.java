package desafio.api.rest.dto.pessoa;

import java.io.Serializable;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import desafio.api.rest.model.Pessoa;
import desafio.api.rest.model.Tarefa;

public class PessoaMediaRetornoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull
	private String nome;

	private Double mediaHorasTarefas;

	public PessoaMediaRetornoDTO(Pessoa pessoa) {
		super();
		this.nome = pessoa.getNome();
		this.mediaHorasTarefas = pessoa.getTarefa().stream().collect(Collectors.averagingInt(Tarefa::getDuracao));
	}

	public PessoaMediaRetornoDTO() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getMediaHorasTarefas() {
		return mediaHorasTarefas;
	}

	public void setMediaHorasTarefas(Double mediaHorasTarefas) {
		this.mediaHorasTarefas = mediaHorasTarefas;
	}

}
