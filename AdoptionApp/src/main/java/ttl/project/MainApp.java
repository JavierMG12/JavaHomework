package ttl.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import ttl.project.domain.AdoptionProcess;
import ttl.project.domain.Animal;
import ttl.project.domain.Person;
import ttl.project.Service.AdoptionService;

import java.time.LocalDate;
import java.util.List;
@SpringBootApplication
public class MainApp {
    public static void main(String[] args) {
        SpringApplication.run(MainApp.class,args);
    }
}

@Component
class MyRunner implements CommandLineRunner
{
    @Autowired
    private AdoptionService adoptionService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Here we go with Spring Boot");
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
        List<AdoptionProcess> students = adoptionService.getAllAdoptions();
        System.out.println("Adoptions: " + students.size());
        System.out.println(students);
    }
}

//podman exec -it my-postgres bash
//cat 0-postgres-setup-schema.sql | podman exec -i my-postgres psql -Upostgres
//psql -Ularku
//\c database
//\d