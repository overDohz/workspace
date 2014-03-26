package Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
		calendar = new GregorianCalendar(2014, Calendar.APRIL, 15);
		d=calendar.getTime();
		AVen.creerEnchere(d, false,0,0, "id", "desc");
		System.out.println(d);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUtilisateur() {

		//fail("Not yet implemented");
	}

}
