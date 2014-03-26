package CAO;

public interface Acheteur {
	public void emettreOffre(double offre, Enchere enchere);
	public String creerAlerte();
	public String desactiverAlerte(int i);
}
