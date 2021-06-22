package DSTE;
import Simulador.*;
/*********** ESTRUTURAS BÁSICAS PARA ESTRUTURAÇÃO DAS INFORMAÇÕES EM LISTAS ENCADEADAS **********/

public class Lista {
	No primeiro;
	No ultimo;
	Lista()
	{
		this.inicia_lista(this);
	}
	/***************************************************************/
	/* FUNÇÕES AUXILIARES DOS ALGORITMOS DE ROTEAMENTO E PREEMPÇÃO */
	/***************************************************************/

	// Rotina que Inicia uma Estrutura de dados Tipo Lista
	public static void inicia_lista(Lista L) 
	{
		L.primeiro = new No();
		L.ultimo = L.primeiro;
		L.primeiro.prox = null;
	}
	// Rotina que Insere o elemento no Final da Lista
	public static void insere_lista(Lsp x, Lista L) 
	{

		L.ultimo.prox = new No();
		L.ultimo=L.ultimo.prox;
		L.ultimo.item = x;
		L.ultimo.prox = null;
	}
	
	public static No remove_lista(Lsp LSP, Lista ListaLSPs)
	{
		No aux, retorno;
		 
		aux = ListaLSPs.primeiro;
		
		
		while(aux.prox != null)
		{	
			if(((Lsp)aux.prox.item).ID == LSP.ID)
			{	

				break;

			}
			else
			{	aux = aux.prox;}
		}	

		retorno=aux.prox;
		if(retorno!=null && retorno.prox != null) // NESSE CASO O ELEMENTO A SER RETIRADO ESTA NO MEIO DA LISTA
		{
			aux.prox = retorno.prox;
			//free(retorno);
		}
		else // NESSE CASO O ELEMENTO A SER RETIRADO É O ULTIMO DA LISTA
		{
			ListaLSPs.ultimo = aux;
			ListaLSPs.ultimo.prox = null;
			//aux->prox = NULL;	
		}
		return(retorno);	
	}
	// Rotina que Retira Elemento do Final de um Lista
	public static No Retira_Lista_Final(Lista L) //Retira o Elemento (Nó) do Final da Lista
	{
		No aux, retorno;

		aux = L.primeiro;

		if (aux.prox == null)
		{
			//printf("Lista Vazia");
			return(null);
		}

		else
		{		
			while(aux.prox.prox != L.ultimo.prox ) // OBS: RESOLVI O PROBLEMA A PRINCÌPIO MAS NAO ENTENDI O PQ AINDA!!???!?!?	
				aux = aux.prox;
			retorno=L.ultimo;
			L.ultimo = aux;
			retorno=L.ultimo.prox;
			L.ultimo.prox = null;

			return(retorno);	
		}
	}
	// Insere em Lista em Ordem Decrescente de Banda
	public static void insereListaOrdemDecrescenteBanda(Lsp x,Lista L ) 
	{
		No aux, aux_busca;

		aux  = new No();
		aux.item = x;

		if(((Lsp)aux.item).Carga <= ((Lsp)L.ultimo.item).Carga || L.primeiro.prox == null) // ver depois se isso funciona mesmo!
		{
			L.ultimo.prox = aux;
			L.ultimo=L.ultimo.prox;
			L.ultimo.prox = null;
		}
		else
		{
			aux_busca = L.primeiro;
			while (((Lsp)aux_busca.prox.item).Carga > ((Lsp)aux.item).Carga)
				aux_busca = aux_busca.prox;
			aux.prox = aux_busca.prox;
			aux_busca.prox = aux;
		}

	}
	
	public static void insereListaOrdemDecrescentePrioridade(Lsp x,Lista L ) 
	{
		No aux, aux_busca;

		aux  = new No();
		aux.item = x;

		if(((Lsp)aux.item).holdPriority <= ((Lsp)L.ultimo.item).holdPriority || L.primeiro.prox == null) // ver depois se isso funciona mesmo!
		{
			L.ultimo.prox = aux;
			L.ultimo=L.ultimo.prox;
			L.ultimo.prox = null;
		}
		else
		{
			aux_busca = L.primeiro;
			while (((Lsp)aux_busca.prox.item).holdPriority > ((Lsp)aux.item).holdPriority)
				aux_busca = aux_busca.prox;
			aux.prox = aux_busca.prox;
			aux_busca.prox = aux;
		}

	}
	
	
	
	
}


