package br.unisal.api.impl;

import br.unisal.api.interfaces.RegraDeCalculo;
import br.unisal.api.model.Frete;

public class ValorFreteAte100Km implements RegraDeCalculo{

	@Override
	public double calcula(Frete frete) {
		double retorno = 0;
		if(frete.getDistancia() <= 100)
			retorno = frete.getDistancia() * 0.15;
		return retorno;
	}

}
