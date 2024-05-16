package ee.ee.controller;

import org.springframework.web.bind.annotation.RestController;

import ee.ee.model.Produit;
import ee.ee.repo.ProduitRepo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("produit")
@CrossOrigin(origins = "http://localhost:3000")
public class ProduitController {

    @Autowired
    ProduitRepo repo;

    @PostMapping("ajouter")
    public ResponseEntity<String> addProdcut(@RequestBody Produit entity) {
        try {
            repo.save(entity);
            return ResponseEntity.ok("Produit ajouté");
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Une erreur s'est produite lors de l'ajout du produit.");
        }
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("getProducts")
    public ResponseEntity getProducts() {
        try {
            List<Produit> products = repo.findAll();
           
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Une erreur s'est produite lors de la récupération des produits.");
        }
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("getProductById")
    public ResponseEntity getProductsById(@RequestParam int id) {
        try {
            Produit product = repo.findById(id).orElse(null);
            if (product != null) {
                return ResponseEntity.ok(product);
            } else {
                return ResponseEntity.status(404).body("No product with this id");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Une erreur s'est produite lors de la récupération des produits.");
        }
    }
    

    @SuppressWarnings("rawtypes")
    @DeleteMapping("deleteProduct")
    public ResponseEntity deleteProduct(@RequestParam int id) {
        try {
            // Check if the product exists
            if (!repo.existsById(id)) {
                return ResponseEntity.notFound().build();
            }

            // Delete the product
            repo.deleteById(id);

            return ResponseEntity.ok("Product with ID " + id + " has been deleted successfully.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Une erreur s'est produite lors de la récupération des produits.");
        }
    }


    



   


}
