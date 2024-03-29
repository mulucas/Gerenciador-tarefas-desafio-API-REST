package desafio.api.rest.dto.pessoa;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import desafio.api.rest.model.Pessoa;

public class PessoaDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@NotNull
	private String nome;
	
	@NotBlank
	private long idDepartamento;

	public PessoaDTO(Pessoa pessoa) {
		super();
		this.nome = pessoa.getNome();
		this.idDepartamento = pessoa.getIdDepartamento().getId();
	}

	public PessoaDTO() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

}