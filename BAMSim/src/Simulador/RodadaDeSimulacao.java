package Simulador;
import java.util.Date;
import java.util.Random;

import DSTE.EstatisticasDSTE;
import DSTE.Lsp;
import DSTE.ParametrosDSTE;



/* Armazena informacoes de simulacao do modelo atual */

public class RodadaDeSimulacao
{
	String name = "";									/* nome a ser usado para denominar o modelo simulado */
	EvChain evc_begin = null;			/* Apontador para o inicio da cadeia de eventos (tokens) */
	EvChain evc_end = null;				/* Apontador para o final da cadeia de eventos (tokens) */
	double clock=0;
	int event=1;
	public int causep_ev;
	public long causep_tkn;
	long tkn_count=0;
	public final String filename = new Date().getTime()+"";/*Usado com prefixo para arquivos e pastas*/
	public EstatisticasDSTE estatistica = new EstatisticasDSTE(filename);
	public Long LSPcount = (long) 0;
	public double simtime()
	{
		return(this.clock);
	}
	
	/*-------CANCEL TOKEN WITH TOKEN POINTER - FC cancelp_tkn ---------------------*/
	/* Esta rotina cancela uma token da cadeia de eventos retornando um apontador para
	   o elemento da cadeia que foi retirado */

	public EvChain cancelp_tkn(int tkn)
	{

		EvChain evc, evc_tkn_srv;

		if ( tkn == 0 )
		{
			//printf ("\n Erro - fc cancelp_tkn - erro fatal token = 0 ");
			MensagensDoSimulador.setMensagem("\n Erro - fc cancelp_tkn - erro fatal token = 0 ");
			System.exit(1);
		}

		if ( (this.evc_begin == null) && (this.evc_end == null) )	/* Cadeia de eventos vazia */
		{
			//printf ("\n Erro - fc cancelp - cadeia de eventos vazia");
			MensagensDoSimulador.setMensagem("\n Erro - fc cancelp - cadeia de eventos vazia");
			System.exit(1);
		}

		evc = this.evc_begin;
		evc_tkn_srv = null;

		while (evc != null)
		{
			if ( evc.ev_tkn == tkn)
			{
				evc_tkn_srv = evc;
				break;
			}
			evc = evc.ev_next;
		}

		if ( evc_tkn_srv == null )	/* Nao achou tkn em servico na cadeia de eventos */
		{
			//printf ("\n Erro - fc cancelp - tkn em servico nao encontrada na cadeia de eventos");
			MensagensDoSimulador.setMensagem("\n Erro - fc cancelp - tkn em servico nao encontrada na cadeia de eventos");
			System.exit (1);
		}

		/* O elemento eh o primeiro e unico da cadeia de eventos */
		if ((evc_tkn_srv.ev_prior == null) && (evc_tkn_srv.ev_next == null))
		{
			this.evc_begin = null;
			this.evc_end = null;
			return (evc_tkn_srv);
		}

		/* O elemento eh o primeiro e nao eh o unico da cadeia de eventos */
		if ((evc_tkn_srv.ev_prior == null) && (evc_tkn_srv.ev_next != null))
		{
			this.evc_begin = evc_tkn_srv.ev_next;
			evc_tkn_srv.ev_next.ev_prior = null;
			return (evc_tkn_srv);
		}

		/* O elemento eh o ultimo da cadeia de eventos */
		if ((evc_tkn_srv.ev_prior != null) && (evc_tkn_srv.ev_next == null))
		{
			this.evc_end = evc_tkn_srv.ev_prior;
			evc_tkn_srv.ev_prior.ev_next = null;
			return (evc_tkn_srv);
		}

		/* O elemento estah no meio da cadeia de eventos */
		if ((evc_tkn_srv.ev_prior != null) && (evc_tkn_srv.ev_next != null))
		{
			evc_tkn_srv.ev_prior.ev_next = evc_tkn_srv.ev_next;
			evc_tkn_srv.ev_next.ev_prior = evc_tkn_srv.ev_prior;
			return (evc_tkn_srv);
		}

		/* Se chegar neste ponto siginifica que um erro imprevisto ocorreu e tem que ser analisado 
		   em detalhe */
		//printf ("\n Erro - fc cancelp - elemento fora de opcoes de retirada");
		MensagensDoSimulador.setMensagem("\n Erro - fc cancelp - elemento fora de opcoes de retirada");
		System.exit (1);
		return (evc_tkn_srv);

	}
	
	



	/*-------SCHEDULE EVENT WITH TOKEN POINTER - FC schedulep ------------------*/
	/* A funcao schedule sera modificada com o acrescimo de um apontador associado
	   a tkn para que na area apontada tenha mais informacoes sobre a tkn. Desta
		 maneira pode-se acrescentar caracteristicas da token */
	/* Atencao - Verificar mais tarde como generalizar este procedimento para 
	   que nao seja necessario toda vez que for compilar o programa  definir 
		 o tipo da variavel que a funcao recebera criar um typedef ou coisa do tipo */
	/* Neste caso criou-se uma variavel tkp - token pointer com o tipo da estrutura
	   do pacote. O ideal e isto ser generalizado para se evitar ter que se colocar
		 esta informacao. Ou ainda deixar a rotina schedule normal e uma schedule com
		 apontador. Neste segundo caso a vantagem e rodar scripts smpl antigos sem
		 problemas */
	/* Basicamente precisam de apenas duas mudancas */


	public void schedulep(int ev, double te, No tkp)
	/* void schedule(int ev, double te, int tkn)           MUDANCA 01/02 */
	{
		EvChain evc, evc_aux;
		//ID
		long tkn = ++tkn_count;
		//Tempo para execução do novo evento
		double st;														/* simulation time tempo de ococrrencia do evento */ 
		st = this.clock + te;

		if ( tkn == 0 )
		{
			MensagensDoSimulador.setMensagem("\n Erro - fc schedulep - erro fatal token = 0 ");
			System.exit (1);
		}

		if (te < 0.0)
		{
			MensagensDoSimulador.setMensagem ("\n Erro - fc schedule - tempo simulado menor que zero");
			System.exit (1);
		}

		evc = new EvChain();
		if (evc == null)
		{
			MensagensDoSimulador.setMensagem ("\n Erro - fc schedulep - nao tem mais memoria para alocar para evento");
			System.exit (1);
		}

		evc.ev_time = st;
		evc.ev_tkn = tkn;
		evc.ev_tkn_p = tkp;							/* Insercao desta linha MUDANCA 02/02 */
		evc.ev_type = ev;

		if ( (this.evc_begin == null) && (this.evc_end == null) )	/* Cadeia de eventos vazia */
		{
			this.evc_begin = evc;
			this.evc_end = evc;
			evc.ev_next = null;
			evc.ev_prior = null;
			return;
		}

		/************************************************/
		/* A sequencia de testes a seguir eh mandatoria */
		/************************************************/


		evc_aux = this.evc_end;

		if (( st > evc_aux.ev_time ) || ( st == evc_aux.ev_time ))	/* Tempo atual maior ou igual */ 
		{																															/* do que o ultimo tempo da 	*/
			evc_aux.ev_next = evc;																			/* cadeia acrescenta no final */
			evc.ev_prior = evc_aux;																		/* da cadeia automaticamente  */
			evc.ev_next = null;
			this.evc_end = evc;
			return;
		}


		evc_aux = this.evc_begin;

		if ( st < evc_aux.ev_time )					/* Tempo atual menor que o primeiro tempo da cadeia
																						 acrescenta no inicio da cadeia automaticamente */
		{
			evc_aux.ev_prior = evc;
			evc.ev_prior = null;
			evc.ev_next = evc_aux;
			this.evc_begin = evc;
			return;
		}
/*
		if ( st == evc_aux.ev_time )					/* Tempo atual igual ao primeiro tempo da cadeia
																						 como ele foi gerado depois ele ficara logo apos o primeiro */
/*		{
			evc.ev_next = evc_aux.ev_next;
			evc.ev_prior = evc_aux;
			evc_aux.ev_next.ev_prior = evc;
			evc_aux.ev_next = evc;
			return;
		}
		
		
		
*/
		
		if ( st == evc_aux.ev_time )					/* Tempo atual igual ao primeiro tempo da cadeia
			 como ele foi gerado depois ele ficara logo apos o ultimo com mesmo tempo */
		{
			while (evc_aux.ev_next!=null && ( st == evc_aux.ev_next.ev_time ))		/* Insere na lista mas nao e o primeiro */
			{
				evc_aux = evc_aux.ev_next;
			}
			evc.ev_next = evc_aux.ev_next;
			evc.ev_prior = evc_aux;
			evc_aux.ev_next.ev_prior = evc;
			evc_aux.ev_next = evc;
			return;
		}
		/* Tempo atual menor ou igual do que o ultimo  tempo da cadeia procura dentro da lista 
			 ponto de insercao. A busca para insercao comeca do final para o inicio pois a 
		   perspectiva e que o novo sn esteja mais proximo do final do que do inicio e isto
		   agiliza a insercao. Pensar em colocar um contador para verificar esta velocidade */


		evc_aux = this.evc_end;

		while (true)		/* Insere na lista mas nao e o primeiro */
		{
			if (( st > evc_aux.ev_time ) || ( st == evc_aux.ev_time ))
			{
				evc.ev_next = evc_aux.ev_next;
				evc.ev_prior = evc_aux;
				evc_aux.ev_next.ev_prior = evc;
				evc_aux.ev_next = evc;
				return;
			}
			evc_aux = evc_aux.ev_prior;
		}

		/* Se chegar a este ponto significa que ocorreu algum erro na insercao do tempo */

		//Mensagem.setMensagem("\n Erro - fc schedulep - erro no tempo nao inserido na lista de eventos");
		//System.exit (1);

	}


	/*----------  CAUSE EVENT  WITH TOKEN POINTER ---------------------------*/
	/* Foram feitas duas mudancas em relacao ao processamento original       */

	//Tem que verificar isso. Passagem por referencia de ev e tkn
	public  No causep()
	{
		EvChain evc;
		No tkp;											/* Insercao desta linha MUDANCA 01/03 */

		evc = this.evc_begin;

		causep_tkn = evc.ev_tkn;
		causep_ev = evc.ev_type;			/* este event servira para o enqueue token colocar o
																	   evento que devera ser processado associado a token
																		 retirada da fila da facilty */
		this.event=causep_ev;
//		Estatisticas.LSP_Number2=tkn;
		this.clock = evc.ev_time;
		tkp = evc.ev_tkn_p;						/* Insercao desta linha ao inves de return puro
	                                     MUDANCA 02/03  */

		/* Apos a retirada do evento libera-se a area deste evento */

		this.evc_begin = evc.ev_next;

		if (evc.ev_next == null)
		{
			this.evc_begin = null;
			this.evc_end = null;
		}
		else
		{
			evc.ev_next.ev_prior = null;
		}


		//free(evc);

		/* retorna o apontador da token */

		return (tkp);											/* Insercao desta linha ao inves de return puro
	                                     MUDANCA 03/03  */

	}


	/*--------------------------  RETURN TIME  ---------------------------*/
	public String imprime_evchain() // Imprime elemento
	{	
		EvChain aux;
		String texto="";

		if(this.evc_begin==null)
			aux = null;
		else
			//aux = sim[Parametros.sn].evc_begin.ev_next;
			aux = this.evc_begin;
		if (aux == null)
		{
			texto+="Lista Vazia";
		}
		else
		{
			while(aux!=null)
			{
				texto+="(Ev="+aux.ev_type+"|Time="+String.format("%.4f",aux.ev_time)+"|Tkn="+aux.ev_tkn+") -> ";
				aux = aux.ev_next;
			}
		}
		return texto;
	}
	public EvChain cancelp_tkn(Object obj)
	{

		EvChain evc, evc_tkn_srv;

		if ( obj == null )
		{
			//printf ("\n Erro - fc cancelp_tkn - erro fatal token = 0 ");
			MensagensDoSimulador.setMensagem("\n Erro - fc cancelp_tkn - erro fatal objeto = null ");
			System.exit(1);
		}

		if ( (this.evc_begin == null) && (this.evc_end == null) )	/* Cadeia de eventos vazia */
		{
			//printf ("\n Erro - fc cancelp - cadeia de eventos vazia");
			MensagensDoSimulador.setMensagem("\n Erro - fc cancelp - cadeia de eventos vazia");
			return null;
		}

		evc = this.evc_begin;
		evc_tkn_srv = null;

		while (evc != null)
		{
			if ( evc.ev_tkn_p!=null && evc.ev_tkn_p.item == obj)
			{
				evc_tkn_srv = evc;
				break;
			}
			evc = evc.ev_next;
		}

		if ( evc_tkn_srv == null )	/* Nao achou tkn em servico na cadeia de eventos */
		{
			//printf ("\n Erro - fc cancelp - tkn em servico nao encontrada na cadeia de eventos");
			MensagensDoSimulador.setMensagem("\n Erro - fc cancelp - tkn em servico nao encontrada na cadeia de eventos");
			System.exit (1);
		}

		/* O elemento eh o primeiro e unico da cadeia de eventos */
		if ((evc_tkn_srv.ev_prior == null) && (evc_tkn_srv.ev_next == null))
		{
			this.evc_begin = null;
			this.evc_end = null;
			return (evc_tkn_srv);
		}

		/* O elemento eh o primeiro e nao eh o unico da cadeia de eventos */
		if ((evc_tkn_srv.ev_prior == null) && (evc_tkn_srv.ev_next != null))
		{
			this.evc_begin = evc_tkn_srv.ev_next;
			evc_tkn_srv.ev_next.ev_prior = null;
			return (evc_tkn_srv);
		}

		/* O elemento eh o ultimo da cadeia de eventos */
		if ((evc_tkn_srv.ev_prior != null) && (evc_tkn_srv.ev_next == null))
		{
			this.evc_end = evc_tkn_srv.ev_prior;
			evc_tkn_srv.ev_prior.ev_next = null;
			return (evc_tkn_srv);
		}

		/* O elemento estah no meio da cadeia de eventos */
		if ((evc_tkn_srv.ev_prior != null) && (evc_tkn_srv.ev_next != null))
		{
			evc_tkn_srv.ev_prior.ev_next = evc_tkn_srv.ev_next;
			evc_tkn_srv.ev_next.ev_prior = evc_tkn_srv.ev_prior;
			return (evc_tkn_srv);
		}

		/* Se chegar neste ponto siginifica que um erro imprevisto ocorreu e tem que ser analisado 
		   em detalhe */
		//printf ("\n Erro - fc cancelp - elemento fora de opcoes de retirada");
		MensagensDoSimulador.setMensagem("\n Erro - fc cancelp - elemento fora de opcoes de retirada");
		System.exit (1);
		return (evc_tkn_srv);

	}
	public void schedulep(int src, int dest, int CT, double carga, int ev, double te, double tempoDeVida)
	{
		No dados = new No();
		Lsp lsp = new Lsp(this);
		lsp.CargaReduzida = 0;
		lsp.src = src;
		lsp.dest = dest;
		lsp.CT = CT;
		lsp.Carga = carga;
		lsp.tempoDeVida=tempoDeVida;
		dados.item = lsp;
		this.schedulep (ev, te, dados);	

	}
}