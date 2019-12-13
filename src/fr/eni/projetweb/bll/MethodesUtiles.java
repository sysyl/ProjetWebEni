/**
 * 
 */
package fr.eni.projetweb.bll;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

import fr.eni.projetweb.dal.CodesResultatDAL;
import fr.eni.projetweb.dal.DAOFactory;
import fr.eni.projetweb.dal.UtilisateurDAO;
import fr.eni.projetweb.exceptions.BusinessException;

/**
 * Classe en charge de 
 * @version ProjetWebENI - V1.0
 * @author jpietras2019
 * @date 5 déc. 2019 - 14:11:14
 *
 */
public class MethodesUtiles {

	/**
	 * methode qui convertit un String en int
	 * @param string nombre
	 * @return int nombre
	 */
	public static int parseStringToInt(String string) {
		int nombre = Integer.parseInt(string);
		return nombre;
	}

	public static Timestamp getTimestampFromString(String dateTimeString) throws BusinessException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		LocalDateTime ldt = null;
		try {
			ldt = LocalDateTime.parse(dateTimeString, formatter);
		}catch(DateTimeParseException e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.PARSE_DATE_ERREUR);
			throw businessException;
		}
		Timestamp ts = Timestamp.valueOf(ldt);
		return ts;
	}
	
	public static String getStringDateFromTimestamp(Timestamp ts) {
		Date date1 = new Date(ts.getTime());
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy 'à' HH:mm");  
        String strDate = dateFormat.format(date1);  
        return strDate;
	}
	
	public static String getStringDateFromTimestampForFormInputDefaultValue(Timestamp ts) {
		Date date1 = new Date(ts.getTime());
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");  
        String strDate = dateFormat.format(date1);  
        return strDate;
	}
	
	/**
	 * Methode en charge de verifier si le string passe
	 * en param est vide ou pas
	 * @param String string
	 * @param businessException
	 */
	public static void verifierChampVide(String string, BusinessException businessException) {
		if (string == null || string.trim().length() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CHAMP_VIDE_ERREUR);		
		}
	}
	
	/**
	 * methode qui permet de verifier la validite des champs composant une adresse : rue, codepostal, ville 
	 */
	public static int verifierAdresse(String rue, String ville, String codePostalS, BusinessException businessException) {
		
		if (rue == null || rue.trim().length() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_RUE_NOM_ERREUR);		
		}
	
		if (ville == null || ville.trim().length() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_NOM_VILLE_NOM_ERREUR);		
		}
		
		int codePostal = verifierCodePostal(codePostalS, businessException);
		return codePostal;
	}
	
	
	/**
	 * Methode en charge de verifier si le code postal est compose de 5 chiffres
	 * et de le transformer de String en int
	 * @param String codePostalS
	 * @return int codePostal 
	 */
	public static int verifierCodePostal(String codePostalS, BusinessException businessException) {
		//accepte uniquement code postal compose de 5 chiffres
		String  pattern = "^[0-9]{5}$";  	
		int codePostal=0;
		
		if(!codePostalS.trim().matches(pattern)){
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CODE_POSTAL_NOM_ERREUR);		
		}else {
			try {
				codePostal = Integer.parseInt(codePostalS);
		
			}catch(NumberFormatException e){
				businessException.ajouterErreur(CodesResultatBLL.REGLE_CODE_POSTAL_NOM_ERREUR);		
			}
		}
		
		return codePostal;
	}
	
	
		
	
		

	
	/**
	 * methode qui hashe le mot de passe passe en parametre
	 * 
	 * @param passwordToHash
	 * @return String mot de passe hashe
	 */
	public static String hasherMotDePasse(String passwordToHash) {
	
		String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        return generatedPassword;
	}
	

	/**
	 * Methode en charge de 
	 * - verifier si le mot de passe a ete renseigne
	 * - si le mot de passe et sa confirmation (pas hashes !) sont equivalents
	 * - si tout va bien -> appel methode qui hashe le mdp
	 * 	 pour l'inserer dans la bdd
	 * 
	 * @param motDePasse
	 * @param confirmation
	 * @param businessException
	 */
	public static String validerMotDePasse(String motDePasse, String confirmation, BusinessException businessException) {
		
		String generatedPassword = null;
		
		if (motDePasse == null || motDePasse.trim().length() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_MOT_DE_PASSE_NOM_ERREUR);		
		}
		
		else if (!motDePasse.equals(confirmation)) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_MOTS_DE_PASSE_IDENTIQUES_NOM_ERREUR);
		}
		else {
			generatedPassword = MethodesUtiles.hasherMotDePasse(motDePasse);
		}
		return generatedPassword;
	}

	/**
	 * Methode qui verifie les champs date rensegines par l'utilisateur.
	 * Exception levee quand :
	 *   -la fin d'enchere a lieu avant son debut  
	 *   -le debut ou fin enchere ont lieu avant le moment present
	 *   une  est levee 
	 * @param dateDebut, dateFin, businessException
	 */
	public static void validerDatesEnchere(Timestamp dateDebut, Timestamp dateFin,
			BusinessException businessException) {
		
		if(dateDebut.after(dateFin) || dateDebut.equals(dateFin)) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_PERIODE_ENCHERE_ERREUR);	
		}
		else if(dateDebut.before(new Timestamp(System.currentTimeMillis())) || dateFin.before(new Timestamp(System.currentTimeMillis()))) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_DATE_ENCHERE_INVALIDE);
		}
		
		
	}
	
	
	public static String statutRetraitString(boolean statut) {
			String statutString = null;
			if(statut) {
				statutString = "retrait effectué";
			}
			else {
				statutString = "article non retiré";
			}
			
			return statutString;
		}

	public static void verifierLienImage(String lienImage, BusinessException businessException) {
		
		if(lienImage.trim().length()!= 0 || lienImage != null) {
			if(lienImage.length() > 300) {
				businessException.ajouterErreur(CodesResultatBLL.LIEN_IMAGE_TROP_LONG);
			}
		}
		
		
	}

	/**
	 * Méthode en charge de verifier si un champ text ne depasse pas
	 * la longueur max de la bdd
	 * @param string
	 * @param businessException
	 */
	public static void verifierLimiteChaine30(String string, BusinessException businessException) {
		if(string.length() >30) {
			businessException.ajouterErreur(CodesResultatBLL.CHAMP_TEXT_30_TROP_LONG);
		}
		
	}
		

}
