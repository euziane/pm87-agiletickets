package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;



public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vaga(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	@Test
	public void deveCriarUmSessaoParaDatasDeInicioEFimIguaisEPericiodadeDiaria(){
		LocalDate inicio = new LocalDate();
		LocalDate fim= new LocalDate(); 
		LocalTime horario = new LocalTime();
		Periodicidade periodicidade = Periodicidade.DIARIA;
		
		Espetaculo show = new Espetaculo();
		
		List<Sessao> sessoes = show.criaSessoes(inicio, fim, horario, periodicidade);
		
		Assert.assertEquals(1, sessoes.size());
		Assert.assertEquals(show, sessoes.get(0).getEspetaculo());
		Assert.assertEquals(inicio.toDateTime(horario),sessoes.get(0).getInicio());
		
	}
	
	@Test
	public void deveCriarUmSessaoParaDatasDeInicioEFimIguaisEPericiodadeSemanal(){
		LocalDate inicio = new LocalDate();
		LocalDate fim= new LocalDate(); 
		LocalTime horario = new LocalTime();
		Periodicidade periodicidade = Periodicidade.SEMANAL;
		
		Espetaculo show = new Espetaculo();
		
		List<Sessao> sessoes = show.criaSessoes(inicio, fim, horario, periodicidade);
		
		Assert.assertEquals(1, sessoes.size());
		Assert.assertEquals(show, sessoes.get(0).getEspetaculo());
		Assert.assertEquals(inicio.toDateTime(horario),sessoes.get(0).getInicio());
		
	}
	
/*	@Test
	public void deveCriarUmSessaoParaDatasDeInicioEFimDiferentesEPericiodadeDiaria(){
		LocalDate inicio = new LocalDate();
		LocalDate fim= new LocalDate(); 
		LocalTime horario = new LocalTime();
		Periodicidade periodicidade = Periodicidade.DIARIA;
		
		Espetaculo show = new Espetaculo();
		
		List<Sessao> sessoes = show.criaSessoes(inicio, fim, horario, periodicidade);
		
		Assert.assertEquals(1, sessoes.size());
		Assert.assertEquals(show, sessoes.get(0).getEspetaculo());
		Assert.assertEquals(inicio.toDateTime(horario),sessoes.get(0).getInicio());
		
	}*/
	
	@Test
	public void deveCriarDuasSessoesParaDatasDeInicioEFimDiferentesEPericiodadeSemanal(){
		LocalDate inicio = new LocalDate();
		LocalDate fim= new LocalDate().plusDays(2); 
		
		LocalTime horario = new LocalTime();
		Periodicidade periodicidade = Periodicidade.SEMANAL;
		
		Espetaculo show = new Espetaculo();
		
		List<Sessao> sessoes = show.criaSessoes(inicio, fim, horario, periodicidade);
		
		Assert.assertEquals(1, sessoes.size());
		Assert.assertEquals(show, sessoes.get(0).getEspetaculo());
		Assert.assertEquals(inicio.toDateTime(horario),sessoes.get(0).getInicio());
		Assert.assertEquals(show, sessoes.get(0).getEspetaculo());
	//	Assert.assertEquals(inicio.toDateTime(horario),sessoes.get(1).getInicio());
		
	}
	
	
	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
}
