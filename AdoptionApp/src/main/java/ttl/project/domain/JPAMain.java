//Package Names should be *ALL* lower case
package ttl.project.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

public class JPAMain {
    public static void main(String[] args) {
        initApp();
        insertData();
        //Method names should start in lower case !!!
        selectAllAdoptions();

    }
    private static EntityManagerFactory emf;
    public static void initApp() {
        String pw = System.getenv("DB_PASSWORD");

        var props = Map.of(
                //"jakarta.persistence.jdbc.url", "jdbc:postgresql://localhost:5432/larku",
                "jakarta.persistence.jdbc.url", "jdbc:postgresql://localhost:5433/adoptapp",
                "jakarta.persistence.jdbc.user", "larku",
                "jakarta.persistence.jdbc.password", "larku",
                "hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect",

                "jakarta.persistence.spi.PersistenceProvider", "org.hibernate.jpa.HibernatePersistenceProvider"
        );
        emf = Persistence.createEntityManagerFactory("LarkUPU_SE", props);
    }

    public static void insertData() {
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

        try (EntityManager manager = emf.createEntityManager();) {
            manager.getTransaction().begin();

//            pets.forEach(pet -> manager.persist(pet));
//            pets.forEach(manager::persist);

            manager.persist(adoptionProcess);

            manager.getTransaction().commit();
        }

    }

    public static void SelectAllpets() {
        try (EntityManager manager = emf.createEntityManager();) {

            TypedQuery<Animal> query = manager.createQuery("select c from Animal c", Animal.class);

            List<Animal> pets = query.getResultList();

            out.println("num Pets: " + pets.size());
            pets.forEach(out::println);
        }
    }
    //Error with id_
    public static void selectAllAdoptions() {
        try (EntityManager manager = emf.createEntityManager();) {

            TypedQuery<AdoptionProcess> query = manager.createQuery("select c from AdoptionProcess c", AdoptionProcess.class);

            List<AdoptionProcess> adoptionsList = query.getResultList();

            out.println("num Adoptions: " + adoptionsList.size());
            adoptionsList.forEach(out::println);
        }
    }

    public static void SelectAllAdopters() {
        try (EntityManager manager = emf.createEntityManager();) {

            TypedQuery<Person> query = manager.createQuery("select c from Person c", Person.class);

            List<Person> adopters = query.getResultList();

            out.println("num Adopters: " + adopters.size());
            adopters.forEach(out::println);
        }
    }

}
