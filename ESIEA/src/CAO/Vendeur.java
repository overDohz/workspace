package CAO;

import java.util.Date;

public interface Vendeur {
	public String creerEnchere(Date dateLimite, boolean estPubliee,int prixMinimum,int prixDeReserve, String identifiant, String description);
	public void publierEnchere(Enchere enchere);
	public void annulerEnchere(Enchere enchere);
}
