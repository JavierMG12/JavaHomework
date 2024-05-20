package ttl.Project.dao;

import ttl.Project.dao.inmemory.InMemoryAdoptionDOA;
import ttl.Project.dao.jpa.JPAAdoptionDOA;

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
