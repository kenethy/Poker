package br.ufrpe.paradigmas.projeto;

//REGRAS DO POKER - https://www.pokerstars.com/br/poker/

public class Regras {

	private boolean verifica;
	private static byte v;
	private char naipe;

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
	public boolean verificaSequencia(Player player){
		v = player.getValorIndex((byte) 0); // PEGA O 1º VALOR
		for (byte i = 1; i < 5; i++) {
			if ((v + 1) == player.getValorIndex(i)) // COMPARA COM O VALOR SEGUINTE A SOMA DE 1
				v = player.getValorIndex(i); 		// CASO SEQUENCIE ADICIONA O VALOR A v
			else
				return false; // CASO NÃO, ALTERA VERIFICA PARA FALSE E SAI DO LAÇO
		}
		return true;
	}

	public void rankingMao(Player player) {

		/**
		 * ROYAL FLUSH
		 */
		
		verifica = verificaNaipes(player);

		// SE TODOS OS NAIPES FOREM IGUAIS, VERIFICA SE ESTÁ SEQUENCIADO DE (A, K, Q, J, T)
		if (verifica == true) {
			if (player.getValorIndex((byte) 4) == 14)
				if (player.getValorIndex((byte) 3) == 13)
					if (player.getValorIndex((byte) 2) == 12)
						if (player.getValorIndex((byte) 1) == 11)
							if (player.getValorIndex((byte) 0) == 10)
								player.setMao(Maos.royalFlush);
		}

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
		 * QUADRA
		 */

		/**
		 * FULL HOUSE  (UM PAR E UMA TRINCA)
		 */

		/**
		 * FLUSH
		 */
		// VERIFICA SE OS NAIPES SAO IGUAIS, SE SIM INDICA QUE EXISTE UM FLUSH
		verifica = verificaNaipes(player);
		if(verifica == true)
			player.setMao(Maos.flush);

		/**
		 * SEQUÊNCIA
		 */
		// VERIFICAÇÃO DA SEQUENCIA
		verifica = verificaSequencia(player);
		// SE A VERIFICA INDICAR UMA SEQUENCIA  
		if (verifica == true)
			player.setMao(Maos.sequencia);

		/**
		 * TRINCA
		 */
		

		/**
		 * DOIS PARES
		 */
		

		/**
		 * PAR
		 */
		

		/**
		 * CARTA ALTA
		 */
		player.setCartaAlta(player.getValorIndex((byte)4));
	}
}
