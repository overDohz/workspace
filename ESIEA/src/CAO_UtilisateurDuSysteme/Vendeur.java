package CAO_UtilisateurDuSysteme;

import java.util.ArrayList;
import java.util.Date;

import CAO_AlertesSurEnchere.AlerteObserver;
import CAO_Encheres.Enchere;
import CAO_Encheres.Enchere.ETAT;

public interface Vendeur {
	public void creerEnchere(Date dateLimite, ETAT etat,int prixMinimum,int prixDeReserve, String identifiant, String description, ArrayList<Enchere> listeEnchere,AlerteObserver alerteObserver,ArrayList<String> listeNotification);
	public void publierEnchere(Enchere enchere);
	public void annulerEnchere(Enchere enchere);
}
