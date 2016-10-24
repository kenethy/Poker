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
		this.naipe = player.getNaipeIndex((byte) 0);
		for (byte i = 1; i < 5; i++) {
			if (this.naipe != player.getNaipeIndex(i))
				return false;
		}
		return true;
	}

	// VERIFICA SE EXISTE UMA SEQUÊNCIA
	public boolean verificaSequencia(Player player) {
		v = player.getValorIndex((byte) 0); // PEGA O 1º VALOR
		for (byte i = 1; i < 5; i++) {
			// COMPARA COM O VALOR SEGUINTE A SOMA DE 1
			if ((v + 1) == player.getValorIndex(i))
				v = player.getValorIndex(i); // CASO SEQUENCIE O SETA VALOR A v
			else
				return false; // CASO NÃO, RETURN FALSE
		}
		return true;
	}

	// ANÁLISE DOS RANKINGS RELACIONADOS A MÃO DO JOGADOR
	public void rankingMao(Player player) {

		/**
		 * CARTA ALTA
		 */
		player.setCartaAlta(player.getValorIndex((byte) 4));
		player.setMao(Maos.cartaAlta);

		/**
		 * PAR * DOIS PARES * TRINCA * QUADRA
		 */
		v = player.getValorIndex((byte) 0); // RECEBE A PRIMEIRA CARTA
		byte b = 0;
		for (byte i = 1; i < 5;) {
			// A VARIÁVEL A SERVE PARA NO FIM DO FOR CHAMAR O PROXIMO VALOR DE V
			byte a = 1;

			/**
			 * PAR
			 */
			if (v == player.getValorIndex(i)) {
				// SE V IGUAL A CARTA SEGUINTE TEREMOS DUAS CARTAS IGUAIS
				par = true;
				a += 1;

				/**
				 * DOIS PARES
				 */
				// SE CARTA RANKING FOR 0 É PORQUE NÃO TEMOS NENHUM RANKING
				if (player.getCartaRanking() == 0)
					player.setCartaRanking(v);
				else {
					// SE NÃO FOR 0, SETAMOS COM O NOVO VALOR DE v
					player.setCartaRanking(v);
					// MAS TEREMOS DOIS PARES NAS CARTAS, SETAMOS DOIS PARES
					doisPares = true;
					par = false; // DOIS PARES TEM VALOR MAIOR
				}

				/**
				 * TRINCA
				 */
				// PODEMOS TER UMA TRINCA
				if (v == player.getValorIndex((byte) (i + 1)) && i < 4) {
					player.setCartaRanking(v);
					/**
					 * DOIS PARES FOR TRUE SIGNIFICA QUE A TRINCA É DO SEGUNDO
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
					// a É INCREMENTADO POIS ENCONTRAMOS UMA TRINCA
					a += 1;
					/**
					 * QUADRA
					 */
					// PODEMOS TER UMA QUADRA
					if (v == player.getValorIndex((byte) (i + 2)) && i < 3 && !fullhouse) {
						player.setCartaRanking(v);
						quadra = true;
						// TRINCA RECEBE FALSE, POIS UMA QUADRA TEM VALOR MAIOR
						trinca = false;
						// a É INCREMENTADO POIS ENCONTRAMOS UMA TRINCA
						a += 1;
					}
				}
			}
			// SWITCH UTILIZADO PARA INCREMENTAR i PELA POSIÇÃO QUE ENCONTRAMOS
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
				i += 1; // INCREMENTO PADRÃO
				break;
			}

			b += a;
			if (b < 4)
				// PROXIMA POSIÇÃO DE V RELATIVO AO VALOR QUE TEMOS DE a
				v = player.getValorIndex(b);
			// PODE USAR O BREAK NESSAS CONDIÇÕES, COMO UMA CONDIÇÃO DE PARADA
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
		 * SEQUÊNCIA
		 */
		// VERIFICAÇÃO DA SEQUENCIA
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
		// SÓ ENTRA NO CASO SE OS NAIPES DE TODAS AS CARTAS FOREM O MESMO
		if (verifica == true) {
			verifica = verificaSequencia(player);
			// SE A COMPARAÇÃO INDICAR UMA SEQUENCIA RETORNA STRAIGHT FLUSH
			if (verifica == true)
				player.setMao(Maos.straightFlush);
		}

		/**
		 * ROYAL FLUSH
		 */
		verifica = verificaNaipes(player);
		// SE TODOS OS NAIPES IGUAIS, VERIFICA SE SEQUÊNCIA É (A, K, Q, J, T)
		if (verifica == true) {
			if (player.getValorIndex((byte) 4) == 14)
				if (player.getValorIndex((byte) 3) == 13)
					if (player.getValorIndex((byte) 2) == 12)
						if (player.getValorIndex((byte) 1) == 11)
							if (player.getValorIndex((byte) 0) == 10)
								player.setMao(Maos.royalFlush);
		}
	}
}
