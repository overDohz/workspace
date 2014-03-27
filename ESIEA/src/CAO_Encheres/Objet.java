package CAO_Encheres;

public class Objet {
	
	private String identifiant;
	private String description;
	
	public Objet() {
		// TODO Auto-generated constructor stub
	}
	public Objet(String identifiant, String description) {
		this.identifiant=identifiant;
		this.description=description;
	}
	public String getIdentifiant() {
		return identifiant;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
