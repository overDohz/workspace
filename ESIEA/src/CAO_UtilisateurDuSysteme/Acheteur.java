package CAO_UtilisateurDuSysteme;

import java.util.ArrayList;

import CAO_Encheres.Enchere;
import CAO_Encheres.Offre;

public interface Acheteur {
	public void emettreOffre(double offre, Enchere enchere, ArrayList<Offre> listeOffre);
	public String creerAlerte();
	public String desactiverAlerte(int i);
	public double rechercheMeilleurOffre();
}
