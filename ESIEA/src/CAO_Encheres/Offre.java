package CAO_Encheres;

import java.util.Observable;

import CAO_AlertesSurEnchere.AlerteObserver;
import CAO_UtilisateurDuSysteme.Utilisateur;

public class Offre extends Observable{

	private Utilisateur utilisateur;
	private double prix;
	private Enchere enchere;
//	ArrayList<Offre> listeOffre;
//,ArrayList<Offre> listeOffre
	public Offre(Utilisateur utilisateur, double prix, Enchere enchere, AlerteObserver alerteObserver) {
		if (prix>0.0){
			this.utilisateur=utilisateur;
			this.prix=prix;
			this.enchere=enchere;
			this.addObserver(alerteObserver);
//			Alerte alerte=new Alerte();
			
//			if (!listeOffre.isEmpty()){
//				listeOffre = new ArrayList<Offre>();
//			}else{
//				listeOffre.add(this);
//			}
		}
	}
//	private boolean existeUtilisateur(){
//		return true;
//	}

	public double getPrix() {
		return prix;
	}
}
