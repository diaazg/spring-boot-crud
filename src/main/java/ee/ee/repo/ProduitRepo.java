package ee.ee.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ee.ee.model.Produit;

@Repository
public interface ProduitRepo extends JpaRepository<Produit,Integer>{
    
   

    
}
