package br.unisal.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import br.unisal.api.impl.ValorFreteAte100Km;
import br.unisal.api.impl.ValorFreteDe101Ate300Km;
import br.unisal.api.impl.ValorFreteMaior300Km;

import br.unisal.api.util.GsonSingleton;

/**
 * The persistent class for the auth_user database table.
 * 
 */
@Entity
@Table(name="frete")
@XmlRootElement
public class Frete implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4124364649619748033L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique = true, nullable = false)
	private Long id;

	@Column(name="cidade_a")
	private City cidadeA;
	
	@Column(name="cidade_b")
	private City cidadeB;
	
	@Column(name="distancia")
	private Double distancia;
	
	@Column(name="valor_frete")
	private Double valorFrete;
	
	public Frete() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public City getCidadeA() {
		return cidadeA;
	}

	public void setCidadeA(City cidadeA) {
		this.cidadeA = cidadeA;
	}
	
	public City getCidadeB() {
		return cidadeB;
	}

	public void setCidadeB(City cidadeB) {
		this.cidadeB = cidadeB;
	}


	public Double getDistancia() {
		return distancia;
	}

	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}

	public Double getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(Double valorFrete) {
		this.valorFrete = valorFrete;
	}
	
	public void calculaValorFrete(){
		if(this.getDistancia() <= 100){
			this.valorFrete = new ValorFreteAte100Km().calcula(this);
		} else if((this.getDistancia() > 100) && (this.getDistancia() <= 300)){
			this.valorFrete = new ValorFreteDe101Ate300Km().calcula(this);
		} else {
			this.valorFrete = new ValorFreteMaior300Km().calcula(this);
		}
	}

	@Override
	public String toString() {
		return GsonSingleton.getInstance().toJson(this);
	}
}
