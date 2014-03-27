package CAO;

import java.util.Date;

import CAO.Enchere.ETAT;

public interface Vendeur {
	public String creerEnchere(Date dateLimite, ETAT etat,int prixMinimum,int prixDeReserve, String identifiant, String description);
	public void publierEnchere(Enchere enchere);
	public void annulerEnchere(Enchere enchere);
}
