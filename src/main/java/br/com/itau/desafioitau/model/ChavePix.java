/**
 * 
 */
package br.com.itau.desafioitau.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author valber Carreiro
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private UUID id;
	
	@Column(name="TIPO_CHAVE")
	private String tipoChave;
	
	@Column(name="VALOR_CHAVE")
	private String valorChave;
	
	@Column(name="TIPO_CONTA")
	private String tipoConta;

	@Column(name="NUM_AGENCIA")
	private Integer numAgencia;

	@Column(name="NUM_CONTA")
	private Long numConta;

	@Column(name="NOME_CORRENTISTA")
	private String nomeCorrentista;

	@Column(name="SOBRENOME_CORRENTISTA")
	private String sobrenomeCorrentista;

	@Column(name="TIPO_PESSOA")
	private String tipoPessoa;

	@Column(name="DATA_CRIACAO")
	private LocalDateTime dataCriacao;
	
	@Column(name="DATA_ATUALIZACAO")
	private LocalDateTime dataAtualizacao;
	
	@PrePersist
	public void prePersist() {
		this.dataCriacao = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		this.dataAtualizacao = LocalDateTime.now();
	}
}
