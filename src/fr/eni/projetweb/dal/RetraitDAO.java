package fr.eni.projetweb.dal;

import fr.eni.projetweb.bo.Retrait;
import fr.eni.projetweb.exceptions.BusinessException;

public interface RetraitDAO {
	
	void updateRetraitStatut(int idArticle, int noVendeur, int creditUtilisateur) throws BusinessException;
	Retrait selectRetraitById(int idRetrait) throws BusinessException;
	void updateRetrait(Retrait retrait) throws BusinessException;
}
