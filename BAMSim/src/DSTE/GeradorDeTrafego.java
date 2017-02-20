package DSTE;

import Simulador.*;

public class GeradorDeTrafego {

	private static int numeroDeLSPsPorMinuto[][];

	public static void trafegoDeterministico(RodadaDeSimulacao rodada, Topologia to, No dados) {

		// Inicializar Tráfego
		if (dados == null) {
			for (int i = 0; i < ParametrosDSTE.MaxClassType; i++) {
				int auxCT = i;
				dados = new No();
				Lsp lsp = new Lsp(rodada);
				lsp.CargaReduzida = 0;
				lsp.src = 0; // id do router fonte
				lsp.dest = 1; // id do router destino
				lsp.CT = auxCT;
				lsp.Carga = (int) GeradorDeNumerosAleatorios.uniform(5, 15);
				dados.item = lsp;
				Debug.setMensagem("Agenda estabelecimento da LSP " + ((Lsp) dados.item).ID + " - "
						+ to.getRoteador(((Lsp) dados.item).src).getDescricao() + " -->"
						+ to.getRoteador(((Lsp) dados.item).dest).getDescricao());
				if (((Lsp) dados.item).CT == 2) {

					rodada.schedulep(3, 3600 * 0, dados); //// CT2
					rodada.schedulep(3, 3600 * 15, dados);

				} else if (((Lsp) dados.item).CT == 1) {

					rodada.schedulep(3, 3600 * 4, dados); // CT1
					rodada.schedulep(3, 3600 * 10, dados);

				} else {
					rodada.schedulep(3, 3600 * 2, dados); //// CT0
					rodada.schedulep(3, 3600 * 9, dados);
					rodada.schedulep(3, 3600 * 16, dados);
				}
			}
		} else {
			Debug.setMensagem(
					"Cria LSP " + ((Lsp) dados.item).ID + " - " + to.getRoteador(((Lsp) dados.item).src).getDescricao()
							+ " -->" + to.getRoteador(((Lsp) dados.item).dest).getDescricao());
			rodada.schedulep(1, 0, dados);

			// Repetição do tráfego
			Debug.setMensagem("Agenda estabelecimento da LSP " + ((Lsp) dados.item).ID + " - "
					+ to.getRoteador(((Lsp) dados.item).src).getDescricao() + " -->"
					+ to.getRoteador(((Lsp) dados.item).dest).getDescricao());
			int auxCT = ((Lsp) dados.item).CT;
			dados = new No();
			Lsp lsp = new Lsp(rodada);
			lsp.CargaReduzida = 0;
			lsp.src = 0; // id do router fonte
			lsp.dest = 1; // id do router destino
			lsp.CT = auxCT;
			lsp.Carga = (int) GeradorDeNumerosAleatorios.uniform(5, 15);
			dados.item = lsp;

			double tempoDeVida = 250;

			if (rodada.simtime() < 3600 * 1) // Uma hora de 0 a 1 hora
			{
				((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
				rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(10), dados);

			} else if (rodada.simtime() < 3600 * 2) // 7200 Duas horas de 1 a 2
													// horas
			{

				((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
				rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(2), dados);

			} else if (rodada.simtime() < 3600 * 3) // 10800 Três horas de 2 a 3
													// horas
			{

				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(2), dados);
				}

			} else if (rodada.simtime() < 3600 * 4)// 14.400 de 3 a 4 horas
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
				}
			} else if (rodada.simtime() < 3600 * 5) // 18000 de 4 a 5 horas
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(15), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
				}
			} else if (rodada.simtime() < 3600 * 6) // de 5 a 6 horas
			{

				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
				}
			} else if (rodada.simtime() < 3600 * 7) /// de 6 a 7
													/// horas//////////////////////////////////////////////////////////////////////////////////////////////////////
			{

				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
				}

			} else if (rodada.simtime() < 3600 * 8) /// de 7 as 8
													/// horas//////////////////////////////////////////////////////////////////////////////////////////////////////
			{

				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(15), dados);
				}

			} else if (rodada.simtime() < 3600 * 9) /////// de 8 as 9
													/////// horas//////////////////////////////////////////////////////////////////////////////////////////////////
			{

			} else if (rodada.simtime() < 3600 * 10) /////// de 9 as
														/////// 10horas//////////////////////////////////////////////////////////////////////////////////////////////////
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
				}

			} else if (rodada.simtime() < 3600 * 11) /////// de 10 as 11
														/////// horas///////////////////////////////////////////////////////////////////////////////////////////////////
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(15), dados);
				}

			} else if (rodada.simtime() < 3600 * 12) /////// de 11 as 12
														/////// horas//////////////////////////////////////////////////////////////////////////////////////////////////
			{

				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
				}

			} else if (rodada.simtime() < 3600 * 13) //// de 12 as 13
														//// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(15), dados);
				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
				}

			} else if (rodada.simtime() < 3600 * 14) //// de 13 as 14
														//// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
			{
				if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
				}

			} else if (rodada.simtime() < 3600 * 15) //// de 14 as 15
														//// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
			{
				if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
				}

			} else if (rodada.simtime() < 3600 * 16) /// de 15 as 16
														/// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
			{
				if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);
				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);
				}

			} else if (rodada.simtime() < 3600 * 17) /// de 16 as 17
														/// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
			{

				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);
				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
				}

			}

		}

	}

	// Aleatório1
	public static void trafegoAleatorio(RodadaDeSimulacao rodada, Topologia to, No dados) {

		if (dados != null) {
			Debug.setMensagem("Agenda estabelecimento da LSP " + ((Lsp) dados.item).ID + " - "
					+ to.getRoteador(((Lsp) dados.item).src).getDescricao() + " -->"
					+ to.getRoteador(((Lsp) dados.item).dest).getDescricao());
			rodada.schedulep(1, 0.0, dados);
		}

		dados = new No();
		Lsp lsp = new Lsp(rodada);
		lsp.CargaReduzida = 0;
		lsp.src = GeradorDeNumerosAleatorios.uniform(0, ParametrosDSTE.ROTEADORES - 1); // id
																						// do
																						// router
																						// fonte
		do {
			lsp.dest = GeradorDeNumerosAleatorios.uniform(0, ParametrosDSTE.ROTEADORES - 1); // id
																								// do
																								// router
																								// destino
		} while (lsp.src == lsp.dest);
		// lsp.src = 0;
		// lsp.dest = 1;
		lsp.CT = GeradorDeNumerosAleatorios.uniform(0, ParametrosDSTE.MaxClassType - 1);
		lsp.Carga = GeradorDeNumerosAleatorios.uniform(5, 30);
		dados.item = lsp;
		Debug.setMensagem(
				"Cria LSP " + ((Lsp) dados.item).ID + " - " + to.getRoteador(((Lsp) dados.item).src).getDescricao()
						+ " -->" + to.getRoteador(((Lsp) dados.item).dest).getDescricao());
		((Lsp) dados.item).tempoDeVida = GeradorDeNumerosAleatorios.expntl(300);
		if (rodada.simtime() < 2000)
			rodada.schedulep(3, GeradorDeNumerosAleatorios.expntl(2), dados);
		else
			rodada.schedulep(3, GeradorDeNumerosAleatorios.expntl(20), dados);

	}

	// Aleatório2
	public static void trafegoManualB(RodadaDeSimulacao rodada, Topologia to, No dados) {

		if (dados != null) {
			Debug.setMensagem("Agenda estabelecimento da LSP " + ((Lsp) dados.item).ID + " - "
					+ to.getRoteador(((Lsp) dados.item).src).getDescricao() + " -->"
					+ to.getRoteador(((Lsp) dados.item).dest).getDescricao());
			rodada.schedulep(1, 0.0, dados);
		}

		dados = new No();
		Lsp lsp = new Lsp(rodada);
		lsp.CargaReduzida = 0;
		lsp.src = GeradorDeNumerosAleatorios.uniform(0, ParametrosDSTE.ROTEADORES - 1); // id
																						// do
																						// router
																						// fonte
		do {
			lsp.dest = GeradorDeNumerosAleatorios.uniform(0, ParametrosDSTE.ROTEADORES - 1); // id
																								// do
																								// router
																								// destino
		} while (lsp.src == lsp.dest);
		// lsp.src = 0;
		// lsp.dest = 1;
		lsp.CT = GeradorDeNumerosAleatorios.uniform(0, ParametrosDSTE.MaxClassType - 1);
		lsp.Carga = GeradorDeNumerosAleatorios.uniform(50, 150);
		dados.item = lsp;
		Debug.setMensagem(
				"Cria LSP " + ((Lsp) dados.item).ID + " - " + to.getRoteador(((Lsp) dados.item).src).getDescricao()
						+ " -->" + to.getRoteador(((Lsp) dados.item).dest).getDescricao());

		if (((Lsp) dados.item).CT == 0) {
			((Lsp) dados.item).tempoDeVida = 850;
			rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.poisson(2), dados);

		} else if (((Lsp) dados.item).CT == 1) {
			((Lsp) dados.item).tempoDeVida = 850;
			rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.poisson(15), dados);

		} else if (((Lsp) dados.item).CT == 2) {
			((Lsp) dados.item).tempoDeVida = 850;
			rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.poisson(1), dados);

		}

	}

	// Aleatório3
	public static void trafegoPoisson(RodadaDeSimulacao rodada, Topologia to, No dados) {

		if (dados == null) {
			numeroDeLSPsPorMinuto = gerarCurvasPoisson( (int) ParametrosDSTE.TempoSimulacao + 60);
			dados = new No();
			dados.item = 0;
			Debug.setMensagem("Agenda estabelecimento de LSP do slot:" + 0 );
			rodada.schedulep(3, 0, dados);

		} else {
			
			int slot = (int) dados.item;
			for (int ct = 0; ct < ParametrosDSTE.MaxClassType; ct++){
				
				for (int i = 0; i < numeroDeLSPsPorMinuto[ct][slot]; i++) {
					No dadosLSP = new No();

					Lsp lsp = new Lsp(rodada);
					lsp.CargaReduzida = 0;
					lsp.src = 0; // id do router fonte
					lsp.dest = 1; // id do router destino
					lsp.CT = ct;
					lsp.Carga = (int) GeradorDeNumerosAleatorios.uniform(5, 15);
					lsp.tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(250);
					dadosLSP.item = lsp;

					Debug.setMensagem("Cria LSP " + ((Lsp) dadosLSP.item).ID + " - "
							+ to.getRoteador(((Lsp) dadosLSP.item).src).getDescricao() + " -->"
							+ to.getRoteador(((Lsp) dadosLSP.item).dest).getDescricao());
					rodada.schedulep(1, GeradorDeNumerosAleatorios.uniform(0, 59), dadosLSP);

				}
			}
			Debug.setMensagem("Agenda estabelecimento de LSP do slot:" + slot );
			dados.item = slot+1;
			rodada.schedulep(3, 60, dados);

		}

	}

	public static int[][] gerarCurvasPoisson(int tempoEmSegundos) {

		int slotsDeTempo = tempoEmSegundos / 60;
		int numeroDeLSPsPorMinuto[][] = new int[ParametrosDSTE.MaxClassType][slotsDeTempo];
		int pesoLSPHora = 10;		
		int numeroDeLSPPorHoraCT[] = new int[] {20*pesoLSPHora,30*pesoLSPHora,50*pesoLSPHora};
		for (int ct = 0; ct < ParametrosDSTE.MaxClassType; ct++) {
			// System.out.println("========" + ct + "========");
			int countSlotDeTempo = 0;
			while (countSlotDeTempo < numeroDeLSPsPorMinuto[ct].length) {

				int numeroDeLSPPorHora = numeroDeLSPPorHoraCT[ct];//GeradorDeNumerosAleatorios.uniform(500,700);
				int lambdaPico = 55;//GeradorDeNumerosAleatorios.uniform(35, 55);
				// System.out.println("Lambda="+lambdaPico);
				int numeroDeLSPs[] = new int[lambdaPico * 3];
				for (int i = 0; i < numeroDeLSPPorHora; i++) {
					int minuto = GeradorDeNumerosAleatorios.poisson(lambdaPico);
					// if(minuto<lambdaPico*2)
					++numeroDeLSPs[minuto];
					// else
					// System.out.println("Discartado="+minuto);

				}

				int iCurva = 0;

				for (int i = 0; i < lambdaPico * 2
						&& numeroDeLSPs[i] < GeradorDeNumerosAleatorios.uniform(1, 3); i++) {
					// System.out.println(i+"="+numeroDeLSPs[i]);
					// System.out.println(numeroDeLSPs[i]);
					iCurva++;
				}
				int fCurva = numeroDeLSPs.length - 1;

				for (int i = numeroDeLSPs.length - 1; i > 0
						&& numeroDeLSPs[i] < GeradorDeNumerosAleatorios.uniform(2, 3); i--) {
					// System.out.println(i+"="+numeroDeLSPs[i]);
					fCurva--;
					// System.out.println(numeroDeLSPs[i]);
				}

				for (int i = iCurva; i <= fCurva && (countSlotDeTempo < numeroDeLSPsPorMinuto[ct].length); i++) {
					// System.out.println(numeroDeLSPs[i]);
					numeroDeLSPsPorMinuto[ct][countSlotDeTempo++] = numeroDeLSPs[i];
				}

			}

		}

		return numeroDeLSPsPorMinuto;
	}

}