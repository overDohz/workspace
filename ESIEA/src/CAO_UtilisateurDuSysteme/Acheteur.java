package CAO_UtilisateurDuSysteme;

import java.util.ArrayList;
import java.util.HashSet;

import CAO_AlertesSurEnchere.Alerte;
import CAO_AlertesSurEnchere.AlerteObserver;
import CAO_Encheres.Enchere;
import CAO_Encheres.Offre;

public interface Acheteur {
	public void emettreOffre(double offre, Enchere enchere, ArrayList<Offre> listeOffre, AlerteObserver alerteObserver);
	public Alerte creerAlerte(Alerte.NOTIFICATION notification , Enchere enchere, HashSet<Alerte> alerte);
	public void desactiverAlerte(int i);
	public double rechercheMeilleurOffre();
}
