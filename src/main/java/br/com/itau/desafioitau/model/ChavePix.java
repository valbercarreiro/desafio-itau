package br.com.itau.desafioitau.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import br.com.itau.desafioitau.model.enums.TipoChave;
import br.com.itau.desafioitau.model.enums.TipoConta;
import br.com.itau.desafioitau.model.enums.TipoPessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Valber Carreiro
 *
 */
@Table(name="CHAVES_PIX")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(of = { "id" }, callSuper = false )
@ToString(of = { "id" })
public class ChavePix implements Serializable {
	
	private static final long serialVersionUID = 2608128091355655277L;
	
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Type(type = "pg-uuid")
	@Column(name="ID")
	private UUID id;
	
	@Column(name="TIPO_CHAVE")
	@Enumerated(EnumType.STRING)
	private TipoChave tipoChave;
	
	@Column(name="VALOR_CHAVE")
	private String valorChave;
	
	@Column(name="TIPO_CONTA")
	@Enumerated(EnumType.STRING)
	private TipoConta tipoConta;

	@Column(name="NUM_AGENCIA")
	private Integer numAgencia;

	@Column(name="NUM_CONTA")
	private Long numConta;

	@Column(name="NOME_CORRENTISTA")
	private String nomeCorrentista;

	@Column(name="SOBRENOME_CORRENTISTA")
	private String sobrenomeCorrentista;

	@Column(name="TIPO_PESSOA")
	@Enumerated(EnumType.STRING)
	private TipoPessoa tipoPessoa;

	@Column(name="DATA_CRIACAO")
	private LocalDateTime dataCriacao;
	
	@Column(name="DATA_ATUALIZACAO")
	private LocalDateTime dataAtualizacao;
	
	@Column(name="DATA_INATIVACAO")
	private LocalDateTime dataInativacao;
	
	@PrePersist
	public void prePersist() {
		this.dataCriacao = LocalDateTime.now();
		this.id = UUID.randomUUID();
	}

	@PreUpdate
	public void preUpdate() {
		this.dataAtualizacao = LocalDateTime.now();
	}
}
