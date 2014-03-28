package CAO_UtilisateurDuSysteme;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;

import CAO_AlertesSurEnchere.Alerte;
import CAO_AlertesSurEnchere.Alerte.NOTIFICATION;
import CAO_AlertesSurEnchere.AlerteObserver;
import CAO_Encheres.Enchere;
import CAO_Encheres.Enchere.ETAT;
import CAO_Encheres.Offre;

public class Utilisateur implements Acheteur,Vendeur{
	
	private String nom;
	private String prenom;
	private String login;
	private String role;
	private Enchere enchere;
	private Date dateActuelle;
	private GregorianCalendar calendrier;
	//pour garder en mémoire, en réalité ces tableaux n'existent pas ils sont dans le SQL
	public static ArrayList<Enchere> listeEnchere;
	public static ArrayList<Offre> listeOffre;
	public Alerte alerte,alertePrix,alerteAnnulee,alerteoOffreSup;
	public ArrayList<String> listeNotification;
//	public
	
	public Utilisateur(String login, ROLE role) {
		this.login=login;
		this.role=role.toString();
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getLogin() {
		return login;
	}

	public String getRole() {
		return role;
	}

//	Plusieurs possibilités... exemple 2 solutions: Soit on crée une fois un utilisateur et on le permute soit on fait la distinction avec le login
//	Ici choix de la solution 2
	public void setRole(String role) {
		this.role = role;
	}

	public Utilisateur(String login, ROLE role,String nom, String prenom) {
		this.login=login;
		this.role=role.toString();
		this.nom=nom;
		this.prenom=prenom;
	}
	
//- - - - - - - - - - - - - - - - - - - - - - - - - - - - Partie VENDEUR
	
	@Override
	public void creerEnchere(Date dateLimite, ETAT etat,int prixMinimum,int prixDeReserve, String identifiant, String description, ArrayList<Enchere> listeEnchere, AlerteObserver alerteObserver,ArrayList<String> listeNotification){
		//check si vendeur
		calendrier = new GregorianCalendar(2014, Calendar.JANUARY, 01);
		dateActuelle=calendrier.getTime();
		if(dateLimite.after(dateActuelle) && prixMinimum>0 && prixDeReserve>=prixMinimum){
			if(listeEnchere!=null){
					this.enchere=new Enchere(Utilisateur.listeEnchere,ETAT.CREEE,this,dateLimite,prixMinimum,prixDeReserve,identifiant,description,alerteObserver);
					Utilisateur.listeEnchere.add(enchere);
					this.listeNotification=listeNotification;
			}else{
					Utilisateur.listeEnchere = new ArrayList<Enchere>();
					this.enchere=new Enchere(Utilisateur.listeEnchere,ETAT.CREEE,this,dateLimite,prixMinimum,prixDeReserve,identifiant,description,alerteObserver);
					Utilisateur.listeEnchere.add(enchere);
					this.listeNotification=listeNotification;
			}
		}
	}

	public ArrayList<String> getListeNotification() {
		return listeNotification;
	}

	public void setListeNotification(ArrayList<String> listeNotification) {
		this.listeNotification = listeNotification;
	}

	public ArrayList<Enchere> listeEnchere(){
		return listeEnchere;
	}
	
	@Override
	public void publierEnchere(Enchere enchere){
		//check si vendeur
		if (enchere.getEtat()==ETAT.CREEE && this.getLogin()==enchere.getCreateur().getLogin()){
			enchere.setEtat(ETAT.PUBLIEE);
		}
		
	}
	
	@Override
	public void annulerEnchere(Enchere enchere){
		//checker si offre avec prix de reserve existe
//		&& enchere.PrixDeReserveNonAtteint() 
//		 System.out.println(this);
//		 System.out.println(enchere.getCreateur());
//		 && enchere.getCreateur().equals(this)
		if (enchere!=null && enchere.getEtat()==ETAT.PUBLIEE && this.getLogin()==enchere.getCreateur().getLogin()){
			enchere.setEtat(ETAT.ANNULEE);
		}
	}
	
//- - - - - - - - - - - - - - - - - - - - - - - - - - - - Partie ACHETEUR
	
	@Override
	public void emettreOffre(double prix, Enchere enchere, ArrayList<Offre> listeOffre, AlerteObserver alerteObserver){
//		check :
//		- si acheteur, 
//		- enchere publiée, 
//		- si acheteur!= createur, 
//		- prixProposé>prixDejaPropose
		Utilisateur.listeOffre=listeOffre;
//		this!=enchere.getCreateur()
		if(this.getLogin()!=enchere.getCreateur().getLogin() && enchere.getEtat()==ETAT.PUBLIEE &&  prix>rechercheMeilleurOffre() && prix>enchere.getPrixMinimum()){	
			if(listeOffre!=null){
//				System.out.println("ajout fait");
				Utilisateur.listeOffre.add(new Offre(this, prix, enchere,alerteObserver));
			}else{
				Utilisateur.listeOffre = new ArrayList<Offre>();
				Utilisateur.listeOffre.add(new Offre(this, prix, enchere,alerteObserver));
//				System.out.println("creation ok");
			}		
		}
	}
	
//	public ArrayList<Offre> listeOffre(){
////		Iterator<Offre> it = listeOffre.iterator();
////		 while(it.hasNext()){
////			 System.out.println(it.next());
////		 	return it.next();
////		 }
////		 return null;
//		return this.listeOffre;
//	}
	
	@Override
	public double rechercheMeilleurOffre(){
		if(listeOffre!=null){
			double prixLePlusEleve=listeOffre.get(0).getPrix();
			for(int i = 1 ; i < listeOffre.size(); i++){
				if(listeOffre.get(i).getPrix()>listeOffre.get(i-1).getPrix()){
					prixLePlusEleve=listeOffre.get(i).getPrix();
				}
			}
			return prixLePlusEleve;
		}
		return 0;
	}
	
	public boolean existeOffre(){
		return listeOffre!=null;
	}
	
	@Override
	public Alerte creerAlerte(Alerte.NOTIFICATION notification , Enchere enchere, HashSet<Alerte> listeAlerte){
		//checker si acheteur
		if(true){
			this.alerte = new Alerte(this, Alerte.NOTIFICATION.ENCHEREANNULEE, enchere);
		}
		Alerte.NOTIFICATION notif=NOTIFICATION.ENCHEREANNULEE;
		if(true){
			if(listeAlerte!=null){
				this.alerte=new Alerte(this, Alerte.NOTIFICATION.ENCHEREANNULEE, enchere);
				listeAlerte.add(alerte);
			}else{
//				listeAlerte=new ;
				this.alerte=new Alerte(this, Alerte.NOTIFICATION.ENCHEREANNULEE, enchere);
				listeAlerte.add(alerte);
			}
		}
		
		return this.alerte;
	}
	
//	public String desactiverTouteAlerte(){
//		//checker si offre avec prix de reserve existe
//		return "";
//	}
	
	@Override
	public void desactiverAlerte(int i){
		switch(i){
		case 1:
			//supp alerte sur prix de reserve
			alertePrix=null;
			break;
		case 2:
			//supp alerte sur annulerEnchere
			alerteAnnulee=null;
			break;
		case 3:
			//supp alerte sur offre supérieure concurrente
			alerteoOffreSup=null;
			break;
		default:
			//supprimer toutes les alertes
			alertePrix=null;
			alerteAnnulee=null;
			alerteoOffreSup=null;
			break;
		}
	}
	
//	public String supprimerEnchere(){
//		return "";
//	}
	
	public enum ROLE {
		ACHETEUR, VENDEUR
	}

	@Override
	public String toString() {
		return "Utilisateur [nom=" + nom + ", prenom=" + prenom + ", login="
				+ login + ", role=" + role + "]";
	}
	
	
}
