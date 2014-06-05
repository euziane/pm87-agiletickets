package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco =null;
		
		switch (sessao.getEspetaculo().getTipo()) {
		case CINEMA:preco = calculaPrecoIngresso(sessao, 0.10,0.50, TipoDeEspetaculo.CINEMA);
			break;
		case SHOW:preco = calculaPrecoIngresso(sessao, 0.10,0.50, TipoDeEspetaculo.SHOW);
			break;
		case BALLET:preco = calculaPrecoIngresso(sessao, 0.20,0.50,TipoDeEspetaculo.BALLET);
			break;
		case ORQUESTRA:preco = calculaPrecoIngresso(sessao, 0.20,0.50,TipoDeEspetaculo.ORQUESTRA);
			break;
		default: preco = sessao.getPreco();
			break;
		} 
		
		return preco.multiply(BigDecimal.valueOf(quantidade));
	}
	
	public static BigDecimal calculaPrecoIngresso(Sessao sessao, double valorDesconto , 
			double percentualDescontoIngresso, TipoDeEspetaculo tipo){
		BigDecimal preco;
		
		if((sessao.getTotalIngressos() - sessao.getIngressosReservados()) / 
				sessao.getTotalIngressos().doubleValue() <= percentualDescontoIngresso) { 
			preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(valorDesconto)));
		} else {
			preco = sessao.getPreco();
		}
		
		if ((tipo.equals(tipo.BALLET) || (tipo.equals(tipo.ORQUESTRA)))){
			if(sessao.getDuracaoEmMinutos() > 60){
				calculaPrecoIngresso(sessao,0.10);
			}
		}
		return preco;
	}
	
	public static BigDecimal calculaPrecoIngresso(Sessao sessao, double valorDesconto ){
		BigDecimal preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(valorDesconto)));
		return preco;
	}

}