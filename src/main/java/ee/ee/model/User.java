package ee.ee.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Data 
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;
    @Column(name =  "username")
    private String username;
    @Column(name = "password")
    private String password;
    
}
