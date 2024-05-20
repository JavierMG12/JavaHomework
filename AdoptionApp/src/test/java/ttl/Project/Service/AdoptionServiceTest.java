package ttl.Project.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ttl.Project.Domain.AdoptionProcess;
import ttl.Project.Domain.Animal;
import ttl.Project.Domain.Person;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AdoptionServiceTest {
    @Autowired
    private AdoptionService adoptionService;
    @Test
    public void TestGetAllAdoptions(){

        List<AdoptionProcess> adoptionProcessList = adoptionService.getAllAdoptions();
        System.out.println("Adoptions: "+ adoptionProcessList);
        assertNotEquals(1,adoptionProcessList.size());
    }
    @Test
    public void TestGetByID(){
        AdoptionProcess adoptionProcess = adoptionService.findById(1);
        assertNotNull(adoptionProcess);
    }
    @Test
    public void TestInsertAdopter(){
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
        AdoptionProcess adp = adoptionService.addAdoption(adoptionProcess);
        assertEquals(2,adp.getId());
    }

    @Test
    public void TestDeleteAdoption(){
        boolean value = adoptionService.deleteAdoption(1);
        assertTrue(value);
    }

}
