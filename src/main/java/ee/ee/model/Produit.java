package ee.ee.model;
import java.io.Serializable;

import jakarta.persistence.*;
import lombok.Data;


@Data 
@Entity
@Table(name = "produit")

public class Produit implements Serializable {

   @Id
   @GeneratedValue(strategy =  GenerationType.AUTO)
   private Integer id;
   @Column(name =  "name")
   private String name;
   @Column(name = "price")
   private double price;

    
}
