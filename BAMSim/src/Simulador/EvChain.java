package Simulador;
public class EvChain 									/* declaracao da estrutura dos elementos da cadeia de eventos */
{
	double ev_time;									/* tempo do proximo processamento desta token */
	long ev_tkn;										/* numero da token */
	int ev_type;									/* tipo do evento associado a esta token para ser processada */
	EvChain ev_prior;			/* apontador para a token anterior */
	EvChain ev_next;			/* apontador para a proxima token */

	/* Inicio da declaracao da estrutura da token */ 
	No ev_tkn_p= new No();							/* Apontador do tipo da struct associada a token - packet */
	/* Final da declaracao da estrutura da token */
};