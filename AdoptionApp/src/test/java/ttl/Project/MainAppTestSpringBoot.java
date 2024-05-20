package ttl.Project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ttl.Project.Domain.AdoptionProcess;
import ttl.Project.Domain.Animal;
import ttl.Project.Domain.Person;
import ttl.Project.Service.AdoptionService;
import ttl.Project.jconfig.AdoptionConfig;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AdoptionConfig.class})
@ActiveProfiles({"prod"})
@SpringBootTest
public class MainAppTestSpringBoot {
    @Autowired
    private AdoptionService as;
    @Test
    public void testAdoptionInsert(){
        List<Animal> pets = List.of(
                new Animal(
                        0,
                        "Chicken",
                        "DOG",
                        "Chihuahua"
                ),
                new Animal(
                        0,
                        "Rocket",
                        "DOG",
                        "Corgi"
                ));
        AdoptionProcess adoptionProcess = new AdoptionProcess(
                0,
                LocalDate.of(2024,4,29),
                new Person(0,"Javier","867123456"),
                pets
        );
        AdoptionProcess inseradoption = as.addAdoption(adoptionProcess);

//        AdoptionProcess adoption1 = new AdoptionProcess(
//                0,
//                LocalDate.of(2024,3,15),
//                new Person(
//                        1,
//                        "Javier",
//                        "123456789"
//                ),
//                new Animal(
//                        2,
//                        "Balto",
//                        "Dog",
//                        "German Shepherd")
//        );
//        AdoptionProcess inseradoption = as.addAdoption(adoption1);
        System.out.println("Adoption: "+ inseradoption);
        assertNotNull(inseradoption);
    }
}
