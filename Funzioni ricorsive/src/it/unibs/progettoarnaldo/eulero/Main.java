package it.unibs.progettoarnaldo.eulero;

public class Main {

	public static void main(String[] args) {
		int n = 0;
		do {
			n = InputDati.leggiIntero("inserire intero da calcolare o fabrizio t'ammazza");
		} while(n < 1);
		System.out.println(n + " è formato da " + eulero(n) + " coprimi");
	}
	
	//ho messo static perchè non è legato ad un oggetto, sennò dovevo fare oggetto.eulero per chiamarlo
	public static int eulero(int n) {
		
		int risultato = 0;
		int k = 0;
		int p;
		int a;
		int b;
		boolean scovato = false;

		for(p = 2; p <= n; p++) {
			if(isPrimo(p)) {
				for(k = 1; Math.pow(p, k) <= n; k++) {
					if(Math.pow(p, k) == n) {
						scovato = true;
						break;
					}
				}
				if(scovato) {
					break;
				}
			}
		}	
		if(scovato) {
			risultato = (int) ((p - 1) * Math.pow(p, (k - 1)));
		}
		else{
			for(a = 2; a <= n / 2; a++) {
				for(b = 2; b <= n / 2 && a*b <= n; b++) {
					if(a * b == n && mcd(a, b) == 1) {
						risultato = eulero(a) * eulero(b);
					}
				}
			}
			
		}
										
		return risultato;
	}

	/**
	 * metodo per verificare che il numero sia primo
	 * @param n
	 * @return
	 */
	public static boolean isPrimo(int n) {
		boolean giacomino = true;
		
		for(int i = 2; i < n; i++) {
			if(n % i == 0) {
				giacomino = false;
				break;
			}
		}		
		return giacomino;
	}
	
	/**
	 * metodo ricorsivo calcolo mcd
	 * @param a
	 * @param b
	 * @return
	 */
	public static int mcd(int a, int b) {
		  if (b == 0)
		    return a;  
		  else
		    return mcd(b, a % b); //prima di ritornare il valore richiama se stessa fino a quando non ritorna la a;
		}
}

