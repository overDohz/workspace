package CAO;

import java.util.ArrayList;

public interface Acheteur {
	public void emettreOffre(double offre, Enchere enchere, ArrayList<Offre> listeOffre);
	public String creerAlerte();
	public String desactiverAlerte(int i);
}
