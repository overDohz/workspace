package CAO_UtilisateurDuSysteme;

import java.util.ArrayList;

import CAO_Encheres.Enchere;
import CAO_Encheres.Offre;

public class Systeme {
	
	private ArrayList<Enchere> listeEnchere;
	private ArrayList<Offre> listeOffre;
	Enchere enchere;
	Offre offre;
	Utilisateur utilisateur;
	
	public Systeme(Utilisateur utilisateur, Enchere enchere, Offre offre) {
		// TODO Auto-generated constructor stub
		this.utilisateur=utilisateur;
		this.enchere=enchere;
		this.offre=offre;
	}
	public ArrayList<Enchere> updateEncheres(){
		return listeEnchere;
	}
	
	public ArrayList<Offre> updateoffres(){
		return listeOffre;
	}
}
