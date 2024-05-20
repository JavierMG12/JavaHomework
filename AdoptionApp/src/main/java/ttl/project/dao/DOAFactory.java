package ttl.project.dao;

import ttl.project.dao.inmemory.InMemoryAdoptionDOA;
import ttl.project.dao.jpa.JPAAdoptionDOA;

import java.util.ResourceBundle;

public class DOAFactory {

    public static AdoptionDOA adoptionDOA(){
        ResourceBundle bundle = ResourceBundle.getBundle("settings");
        String profile = bundle.getString("settings.profile");

        return switch (profile){
            case "dev" -> new InMemoryAdoptionDOA();
            case "prod" -> new JPAAdoptionDOA();
            default -> throw new RuntimeException("Unkown profile:"+profile);
        };
    }
}
