package com.eventoWS.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity(name = "Usuario")
@Table(name = "usuario")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@NotBlank
	@Column(name = "login")
	private String login;
	
	@NotBlank
	@Column(name = "nomeCompleto")
	private String nomeCompleto;
	
	@NotBlank
	@Column(name = "senha")
	private String senha;

	@Column(name="roles")
	@ManyToMany
	@JoinTable(name = "usuarios_roles", 
			   joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "login"), 
			   inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "nomeRole")) 
    private List<Role> roles;
	
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
