package CAO;

import java.util.ArrayList;
import java.util.Date;

public class Enchere {

	private Utilisateur createur;
	private Date dateLimite;
	private double prixMinimum;
	private double prixDeReserve;
	private ETAT etat;
	
	public Enchere(ArrayList<Enchere> listeEnchere,ETAT etat, Utilisateur createur, Enchere enchere, Date dateLimite, double prixMinimum,double prixDeReserve, String identifiant, String description) {
		// TODO Auto-generated constructor stub
		//modif liste Enchere
		this.etat=etat;
		this.createur=createur;
		this.dateLimite=dateLimite;
		this.prixMinimum=prixMinimum;
		this.prixDeReserve=prixDeReserve;
		Objet objet=new Objet(identifiant,description);
	}
	
	public Utilisateur getCreateur() {
		return createur;
	}

	public double getPrixDeReserve(Utilisateur utilisateur) {
		//check si vendeur
		if (utilisateur.getRole()==createur.getRole()){
			return prixDeReserve;
		}else{
			return 0;
		}
	}
	
	public boolean PrixDeReserveNonAtteint(){
		if (prixDeReserve>createur.rechercheOffre()){
			return true;
		}else{
			return false;
		}
	}

	public double getPrixMinimum() {
		return prixMinimum;
	}

	public ETAT getEtat() {
		return etat;
	}

	public void setEtatPubliee() {
		etat=ETAT.PUBLIEE;
		
	}
	public void setEtat(ETAT etat){
		this.etat=etat;
	}

	public enum ETAT {
		CREEE, PUBLIEE, TERMINEE, ANNULEE
	}
}
