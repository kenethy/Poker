package br.ufrpe.paradigmas.projeto;

//REGRAS DO POKER - https://www.pokerstars.com/br/poker/games/rules/hand-rankings/

public class Regras {

	private boolean verifica;
	private static byte v;
	private char naipe;
	private boolean par = false;
	private boolean doisPares = false;
	private boolean trinca = false;
	private boolean quadra = false;
	private boolean fullhouse = false;

	// VERIFICA SE TODAS AS CARTAS POSSUEM NAIPES IGUAIS
	public boolean verificaNaipes(Player player) {
		this.naipe = player.carta.get(0).getNaipe();
		for (byte i = 1; i < 5; i++) {
			if (this.naipe != player.carta.get(i).getNaipe())
				return false;
		}
		return true;
	}

	// VERIFICA SE EXISTE UMA SEQU�NCIA
	public boolean verificaSequencia(Player player) {
		v = player.carta.get(0).getValor(); // PEGA O 1� VALOR
		for (byte i = 1; i < 5; i++) {
			// COMPARA COM O VALOR SEGUINTE A SOMA DE 1
			if ((v + 1) == player.carta.get(i).getValor())
				v = player.carta.get(i).getValor(); // CASO SEQUENCIE O SETA VALOR A v
			else
				return false; // CASO N�O, RETURN FALSE
		}
		return true;
	}

	// AN�LISE DOS RANKINGS RELACIONADOS A M�O DO JOGADOR
	public void rankingMao(Player player) {

		/**
		 * CARTA ALTA
		 */
		player.setCartaAlta(player.carta.get(4).getValor());
		player.setMao(Maos.cartaAlta);

		/**
		 * PAR * DOIS PARES * TRINCA * QUADRA
		 */
		v = player.carta.get(0).getValor(); // RECEBE A PRIMEIRA CARTA
		byte b = 0;
		for (byte i = 1; i < 5;) {
			// A VARI�VEL A SERVE PARA NO FIM DO FOR CHAMAR O PROXIMO VALOR DE V
			byte a = 1;

			/**
			 * PAR
			 */
			if (v == player.carta.get(i).getValor()) {
				// SE V IGUAL A CARTA SEGUINTE TEREMOS DUAS CARTAS IGUAIS
				par = true;
				a += 1;

				/**
				 * DOIS PARES
				 */
				// SE CARTA RANKING FOR 0 � PORQUE N�O TEMOS NENHUM RANKING
				if (player.getCartaRanking() == 0)
					player.setCartaRanking(v);
				else {
					// SE N�O FOR 0, SETAMOS COM O NOVO VALOR DE v
					player.setCartaRanking(v);
					// MAS TEREMOS DOIS PARES NAS CARTAS, SETAMOS DOIS PARES
					doisPares = true;
					par = false; // DOIS PARES TEM VALOR MAIOR
				}

				/**
				 * TRINCA
				 */
				// PODEMOS TER UMA TRINCA
				if (v == player.carta.get(i+1).getValor() && i < 4) {
					player.setCartaRanking(v);
					/**
					 * DOIS PARES FOR TRUE SIGNIFICA QUE A TRINCA � DO SEGUNDO
					 * PAR COM ISSO, TEMOS UM PAR E UMA TRINCA (FULLHOUSE)
					 */
					if (doisPares) {
						fullhouse = true;
					} else {
						trinca = true;
					}
					// PAR SETADA COMO FALSE, POIS UMA TRINCA TEM VALOR MAIOR
					par = false;
					doisPares = false;
					// a � INCREMENTADO POIS ENCONTRAMOS UMA TRINCA
					a += 1;
					/**
					 * QUADRA
					 */
					// PODEMOS TER UMA QUADRA
					if (v == player.carta.get(i+2).getValor() && i < 3 && !fullhouse) {
						player.setCartaRanking(v);
						quadra = true;
						// TRINCA RECEBE FALSE, POIS UMA QUADRA TEM VALOR MAIOR
						trinca = false;
						// a � INCREMENTADO POIS ENCONTRAMOS UMA TRINCA
						a += 1;
					}
				}
			}
			// SWITCH UTILIZADO PARA INCREMENTAR i PELA POSI��O QUE ENCONTRAMOS
			switch (a) {
			case 2: // PAR
				i += 2;
				break;
			case 3: // TRINCA
				i += 3;
				break;
			case 4: // QUADRA
				i += 4;
				break;
			default:
				i += 1; // INCREMENTO PADR�O
				break;
			}

			b += a;
			if (b < 4)
				// PROXIMA POSI��O DE V RELATIVO AO VALOR QUE TEMOS DE a
				v = player.carta.get(b).getValor();
			// PODE USAR O BREAK NESSAS CONDI��ES, COMO UMA CONDI��O DE PARADA
			else
				break;
		}

		if (par)
			player.setMao(Maos.par);
		else if (doisPares)
			player.setMao(Maos.doisPares);
		else if (trinca)
			player.setMao(Maos.trinca);
		else if (quadra)
			player.setMao(Maos.quadra);

		/**
		 * SEQU�NCIA
		 */
		// VERIFICA��O DA SEQUENCIA
		verifica = verificaSequencia(player);
		// SE A VERIFICA INDICAR UMA SEQUENCIA
		if (verifica && !fullhouse)
			player.setMao(Maos.sequencia);

		/**
		 * FLUSH
		 */
		// VERIFICA SE OS NAIPES SAO IGUAIS, SE SIM INDICA QUE EXISTE UM FLUSH
		verifica = verificaNaipes(player);
		if (verifica && !fullhouse)
			player.setMao(Maos.flush);

		/**
		 * FULL HOUSE (UM PAR E UMA TRINCA)
		 */
		if (fullhouse)
			player.setMao(Maos.fullHouse);

		/**
		 * STRAIGHT FLUSH
		 */
		// S� ENTRA NO CASO SE OS NAIPES DE TODAS AS CARTAS FOREM O MESMO
		if (verifica == true) {
			verifica = verificaSequencia(player);
			// SE A COMPARA��O INDICAR UMA SEQUENCIA RETORNA STRAIGHT FLUSH
			if (verifica == true)
				player.setMao(Maos.straightFlush);
		}

		/**
		 * ROYAL FLUSH
		 */
		verifica = verificaNaipes(player);
		// SE TODOS OS NAIPES IGUAIS, VERIFICA SE SEQU�NCIA � (A, K, Q, J, T)
		if (verifica == true) {
			if (player.carta.get(4).getValor() == 14)
				if (player.carta.get(3).getValor() == 13)
					if (player.carta.get(2).getValor() == 12)
						if (player.carta.get(1).getValor() == 11)
							if (player.carta.get(0).getValor() == 10)
								player.setMao(Maos.royalFlush);
		}
	}
}
