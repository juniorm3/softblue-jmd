package br.com.softblue.bluefood.domain.usuario;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.softblue.bluefood.util.StringUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass
public class Usuario implements Serializable {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "O nome não pode ser vazio")
	@Size(max = 80, message = "O nome é muito gande")
	private String nome;

	@NotBlank(message = "O e-mail não pode ser vazio")
	@Size(max = 60, message = "O e-mail é muito gande")
	@Email(message = "O e-mail é inválido")
	private String email;

	@NotBlank(message = "A senha não pode ser vazia")
	@Size(max = 80, message = "A senha é muito gande")
	private String senha;

	@NotBlank(message = "O telefone não pode ser vazio")
	@Pattern(regexp = "[0-9]{10,11}", message = "O telefone possui formato inválido")
	@Column(length = 11, nullable = false)
	private String telefone;
	
	public void encryptPassword() {
		this.senha = StringUtils.encrypt(this.senha);
	}

}
