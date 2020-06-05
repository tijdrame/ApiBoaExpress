package com.boa.api.repository;

import java.time.Instant;
import java.util.List;

import com.boa.api.domain.Transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Transaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    Transaction findByNumeroTransaction(String numeroTransaction);

    @Query("Select t FROM Transaction t  WHERE (t.dateConfirmed BETWEEN ?7 AND ?8) "+
    "and upper(t.referenceBancaire)like ?1 and upper(t.senderTelephone)like ?2 "+
    "and upper(t.senderCompte)like ?3 and upper(t.numeroTransaction)like ?4 "+
    "and t.isConfirmed= ?5 and upper(t.paysEnvoi)like ?6 "+
    "and upper(t.paysDestination)like ?9"
    )
    Page<Transaction> findByCriterias
    (String refBancaire, String sendertel, String senderCompte, String numTransation, 
    Boolean isConfirmed, String paysEnvoi,Instant fromD, Instant toD, String PaysDest, Pageable pageable);
    //Page<Collaborateur> findByPrenomLikeIgnoreCaseAndNomLikeIgnoreCaseAndTelephoneLikeIgnoreCaseAndDeleted(
        //String prenom, String nom, String tel, Boolean deleted, Pageable pageable);

	Transaction findByReferenceBancaire(String refence);

    @Query("Select count(t.id) FROM Transaction t where t.isConfirmed =?1")
    Long getTotalTransaction(Boolean status);

    @Query("Select SUM(t.montant), SUM(t.montantFrais) FROM Transaction t where t.isConfirmed =true")
    Object getMontantAndFrais();
    
}
