package ee.ee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ee.ee.model.User;
import ee.ee.repo.UserRepo;

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    UserRepo repo;

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody User entity) {

        try {
            User user = repo.findByUsernameAndPassword(entity.getUsername(), entity.getPassword());
          if(user == null){
            repo.save(entity);
            return ResponseEntity.ok("Utilisateur a inscrir");
          }else{
            return ResponseEntity.status(404).body("User alredy exist");
          }
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Une erreur s'est produite lors de l'inscription.");
        }
    }

    @SuppressWarnings("rawtypes")
    
    
    @PostMapping("login")
    public ResponseEntity login(@RequestBody User body) {
        try{
            
            User user = repo.findByUsernameAndPassword(body.getUsername(), body.getPassword());
            System.err.println(user);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(404).body("Wrong credentials");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("there is problem in server");
        }
    }
    
    
}
