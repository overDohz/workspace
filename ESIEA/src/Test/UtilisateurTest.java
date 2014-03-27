package Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import CAO.Enchere;
import CAO.Enchere.ETAT;
import CAO.Offre;
import CAO.Utilisateur;
import CAO.Utilisateur.ROLE;

public class UtilisateurTest {

	private Date d;
	private GregorianCalendar calendar;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Utilisateur AAch = new Utilisateur("login1",ROLE.ACHETEUR,"nom1","prenom1");
		Utilisateur AVen = new Utilisateur("login2",ROLE.VENDEUR,"nom2","prenom2");
		Utilisateur BAch = new Utilisateur("login1",ROLE.ACHETEUR,"nom1","prenom1");
		Utilisateur BVen = new Utilisateur("login2",ROLE.VENDEUR,"nom2","prenom2");
		Utilisateur CAch = new Utilisateur("login1",ROLE.ACHETEUR,"nom1","prenom1");
//		System.out.println(u1.getRole());
//		System.out.print(u1.toString());
//		System.out.println(u1.estVendeur());
		calendar = new GregorianCalendar(2014, Calendar.MARCH, 26);
		d=calendar.getTime();
		AVen.creerEnchere(d, ETAT.CREEE,1,2, "id", "desc");
		AVen.creerEnchere(d, ETAT.CREEE,1,2, "id", "desc");
		System.out.println(d);
//		afficherEnchere()
//		ArrayList<Enchere> ae=AVen.afficherEnchere();
		Iterator<Enchere> it = AVen.listeEnchere().iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		AVen.publierEnchere(AVen.listeEnchere().get(0));
		//get etat
		System.out.println(AVen.listeEnchere().get(0).getEtat());
		//annuler enchere
		AVen.annulerEnchere(AVen.listeEnchere().get(0));
		System.out.println(AVen.listeEnchere().get(0).getEtat());
		// - - - - - - - - - ACHETEUR
		AAch.emettreOffre(5,AVen.listeEnchere().get(0),AAch.listeOffre());
//		if(AAch.listeOffre()!=null){
//			Iterator<Offre> ite = AAch.listeOffre().iterator();
//			while(ite.hasNext()){
//				System.out.println(ite.next());
//			}
//		}
		// repasser l'enchere en publiee
		AVen.publierEnchere(AVen.listeEnchere().get(1));
		AAch.emettreOffre(5,AVen.listeEnchere().get(1),AAch.listeOffre());
		AAch.emettreOffre(7,AVen.listeEnchere().get(1),AAch.listeOffre());
		BAch.emettreOffre(7,AVen.listeEnchere().get(1),AAch.listeOffre());
		if(AAch.listeOffre()!=null){
			Iterator<Offre> ite = AAch.listeOffre().iterator();
			while(ite.hasNext()){
				System.out.println(ite.next());
			}
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUtilisateur() {

		//fail("Not yet implemented");
	}

}
