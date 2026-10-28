package simulazione11;

public class TipoPacco {
	  public static final int STANDARD = 1;
	  public static final int EXPRESS = 2;

	   public static boolean valido(int tipo) {
	       return tipo == STANDARD || tipo == EXPRESS;
	   }
}
