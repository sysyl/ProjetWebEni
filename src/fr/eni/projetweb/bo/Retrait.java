/**
 * 
 */
package fr.eni.projetweb.bo;

/**
 * Classe en charge de 
 * @version Projet 1 - V1.0
 * @author euznansk2019
 * @date 2 d�c. 2019 - 15:19:04
 *
 */
public class Retrait {
	
	private int idRetrait;
	private String rue;
	private int codePostal;
	private String ville;
	private boolean status;
	
	public Retrait() {
		
	}

	/**
	 * Constructeur.
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param article
	 */
	public Retrait(String rue, int codePostal, String ville, boolean status) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.status = status;
	}
	
	public Retrait(int idRetrait, String rue, int codePostal, String ville, boolean status) {
		super();
		this.idRetrait = idRetrait;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.status = status;
	}

	/**
	 * Getter pour idRetrait.
	 * @return the idRetrait
	 */
	public int getIdRetrait() {
		return idRetrait;
	}

	/**
	 * Setter pour idRetrait.
	 * @param idRetrait the idRetrait to set
	 */
	public void setIdRetrait(int idRetrait) {
		this.idRetrait = idRetrait;
	}

	/**
	 * Getter pour rue.
	 * @return the rue
	 */
	public String getRue() {
		return rue;
	}

	/**
	 * Setter pour rue.
	 * @param rue the rue to set
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}

	

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	/**
	 * Getter pour ville.
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * Setter pour ville.
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	/**
	 * Getter pour article.
	 * @return the article
	 */

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Retrait [rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + "]";
	}
	
	

}
