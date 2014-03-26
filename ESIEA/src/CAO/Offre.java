package CAO;

import java.util.ArrayList;
import java.util.Iterator;

public class Offre {

	private Utilisateur utilisateur;
	private double prix;
	private Enchere enchere;
//	ArrayList<Offre> listeOffre;

	public Offre(Utilisateur utilisateur, double prix, Enchere enchere,ArrayList<Offre> listeOffre) {
		if (prix>0.0){
			this.utilisateur=utilisateur;
			this.prix=prix;
			this.enchere=enchere;
			
			Alerte alerte=new Alerte();
			
			if (!listeOffre.isEmpty()){
				listeOffre = new ArrayList<Offre>();
			}else{
				listeOffre.add(this);
			}
		}
	}
	private boolean existeUtilisateur(){
		return true;
	}

	public double getPrix() {
		return prix;
	}
}
