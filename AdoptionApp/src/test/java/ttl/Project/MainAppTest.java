package ttl.Project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
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
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles({"prod"})
public class MainAppTest {
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
        System.out.println("Adoption: "+ inseradoption);
        assertNotNull(inseradoption);
    }
}
