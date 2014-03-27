package CAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import CAO.Enchere.ETAT;

public class Utilisateur implements Acheteur,Vendeur{
	
	private String nom;
	private String prenom;
	private String login;
	private String role;
	private Enchere enchere;
	private Date dateActuelle;
	private GregorianCalendar calendrier;
	ArrayList<Enchere> listeEnchere;
	ArrayList<Offre> listeOffre;
	
	public static void main(String Args[]){
		
		
	}
	
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

	public void setLogin(String login) {
		this.login = login;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Utilisateur(String login, ROLE role,String nom, String prenom) {
		this.login=login;
		this.role=role.toString();
		this.nom=nom;
		this.prenom=prenom;
	}
//- - - - - - - - - - - - - - - - - - - - - - - - - - - -	
//	public boolean estVendeur(){
//		return this.role=="VENDEUR";
//	}
	
	public String creerEnchere(Date dateLimite, ETAT etat,int prixMinimum,int prixDeReserve, String identifiant, String description){
		//check si vendeur
		calendrier = new GregorianCalendar(2014, Calendar.JANUARY, 01);
		dateActuelle=calendrier.getTime();
		if(dateLimite.after(dateActuelle) && prixMinimum>0 && prixDeReserve>=prixMinimum){
			if(listeEnchere!=null){
					this.enchere=new Enchere(listeEnchere,ETAT.CREEE,this,null,dateLimite,prixMinimum,prixDeReserve,identifiant,description);
					listeEnchere.add(enchere);
				}else{
					listeEnchere = new ArrayList<Enchere>();
					this.enchere=new Enchere(listeEnchere,ETAT.CREEE,this,null,dateLimite,prixMinimum,prixDeReserve,identifiant,description);
					listeEnchere.add(enchere);
				}				
		}
		
		return "";
	}
	public ArrayList<Enchere> listeEnchere(){
		return listeEnchere;
	}
	
	public void publierEnchere(Enchere enchere){
		//check si vendeur
		if (enchere.getEtat()==ETAT.CREEE){
			enchere.setEtat(ETAT.PUBLIEE);
		}
		
	}
	
	public void annulerEnchere(Enchere enchere){
		//checker si offre avec prix de reserve existe
//		&& enchere.PrixDeReserveNonAtteint() 
		if (enchere!=null && enchere.getEtat()==ETAT.PUBLIEE  ){
			enchere.setEtat(ETAT.ANNULEE);
		}
	}
	
	public void afficherEncheres(){
	
	}
	
//- - - - - - - - - - - - - - - - - - - - - - - - - - - -	
//	public boolean estAcheteur(){
//		return this.role=="ACHETEUR";
//	}
	
	public void emettreOffre(double prix, Enchere enchere, ArrayList<Offre> listeOffre){
		//check si acheteur et enchere publiée
		 this.listeOffre=listeOffre;
		if( this!=enchere.getCreateur() && enchere.getEtat()==ETAT.PUBLIEE &&  prix>rechercheMeilleurOffre()){
			if(listeOffre!=null){
				System.out.println("ajout fait");
				this.listeOffre.add(new Offre(this, prix, enchere));
			}else{
				this.listeOffre = new ArrayList<Offre>();
				this.listeOffre.add(new Offre(this, prix, enchere));
				System.out.println("creation ok");
			}		
		}
	}
	public ArrayList<Offre> listeOffre(){
//		Iterator<Offre> it = listeOffre.iterator();
//		 while(it.hasNext()){
//			 System.out.println(it.next());
//		 	return it.next();
//		 }
//		 return null;
		return this.listeOffre;
	}
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
	public boolean existeEnchere(){
		return false;
	}
	public boolean existeOffre(){
		return listeOffre!=null;
	}
	public String creerAlerte(){
		//checker si acheteur
		return "";
	}
	
//	public String desactiverTouteAlerte(){
//		//checker si offre avec prix de reserve existe
//		return "";
//	}
	
	public String desactiverAlerte(int i){
		switch(i){
		case 1:
			//supp alerte sur prix de reserve
			break;
		case 2:
			//supp alerte sur annulerEnchere
			break;
		case 3:
			//supp alerte sur offre supérieure concurrente
			break;
		default:
			//supprimer toutes les alertes
			break;
		}
		return "";
	}
	
//	public String supprimerEnchere(){
//		return "";
//	}
	
	public enum ROLE {
		ACHETEUR, VENDEUR
	}

//	public static Date date(){
//		Date aujourdhui=new Date();
//		
//		return aujourdhui;
//	}
	
	@Override
	public String toString() {
		return "Utilisateur [nom=" + nom + ", prenom=" + prenom + ", login="
				+ login + ", role=" + role + "]";
	}
	
	
}
