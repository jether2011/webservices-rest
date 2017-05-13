package br.unisal.api.impl;

import br.unisal.api.interfaces.RegraDeCalculo;
import br.unisal.api.model.Frete;

public class ValorFreteDe101Ate300Km implements RegraDeCalculo{

	@Override
	public double calcula(Frete frete) {
		double retorno = 0;
		if((frete.getDistancia() > 100) && (frete.getDistancia() <= 300))
			retorno = frete.getDistancia() * 0.75;
		return retorno;
	}

}
