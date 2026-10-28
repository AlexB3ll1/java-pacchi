package simulazione11;

import java.util.Map;

public class RegistroPacchiUrbano extends RegistroPacchi{
	public RegistroPacchiUrbano(String nomeCentro, Map<Integer, Integer> postiDisponibili) throws PaccoException{
		super(nomeCentro, postiDisponibili);
	}

	@Override
	protected double calcolaCosto(Pacco pacco) throws PaccoException {
		if(pacco == null) {
			throw new PaccoException("pacco null");
		}
		
		if(pacco.getTipo() == TipoPacco.STANDARD) {
			return pacco.getPeso() * 2;
		}
		else {
			return (pacco.getPeso() * 4) + 5;
		}
	}
	
	public double costoTotaleSpedizioni() throws PaccoException{
		double costo = 0;
		for(Pacco p : pacchi) {
			costo += calcolaCosto(p);
		}
		return costo;
	}
}
