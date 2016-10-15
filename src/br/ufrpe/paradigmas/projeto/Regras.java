package br.ufrpe.paradigmas.projeto;

public class Regras {

	private boolean verify;

	public Maos rankingMao(Player player) {
		// ROYAL FLUSH
		char naipe = player.getNaipeIndex((byte) 0);
		verify = true;

		// VERIFICA SE TODAS AS CARTAS POSSUEM NAIPES IGUAIS
		for (byte i = 1; i < 5 && verify == true; i++) {
			if (naipe != player.getNaipeIndex(i))
				verify = false;
		}

		if (verify == true) {
			if (player.getValorIndex((byte) 4) == 14)
				if (player.getValorIndex((byte) 3) == 13)
					if (player.getValorIndex((byte) 2) == 12)
						if (player.getValorIndex((byte) 1) == 11)
							if (player.getValorIndex((byte) 0) == 10)
								return Maos.royalFlush;
		}

		// STRAIGHT FLUSH
		
		// QUADRA
		// FULL HOUSE
		// FLUSH
		// SEQUÊNCIA
		// TRINCA
		// DOIS PARES
		// PAR
		// CARTA ALTA

		return null;
	}
}
