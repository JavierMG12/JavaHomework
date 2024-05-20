package ttl.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ttl.Project.Domain.AdoptionProcess;
import ttl.Project.Domain.Animal;
import ttl.Project.Domain.Person;
import ttl.Project.Service.AdoptionService;
import ttl.Project.dao.AdoptionDOA;
import ttl.Project.jconfig.AdoptionConfig;

import java.time.LocalDate;
import java.util.ArrayList;
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