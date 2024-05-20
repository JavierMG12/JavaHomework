package ttl.Project.Domain;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JDBCDemo {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5433/adoptapp";
        String user = "larku";
        String password = "larku";
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            List<AdoptionProcess> list = getAdoptions(connection);
            list.forEach(System.out::println);
            Animal pet = new Animal();
            pet.setNamePet("Bluey");
            pet.setTypePet("DOG");
            pet.setBreedPet("Australian Shepard");
            addPet(connection, pet);
            Person adopter = new Person();
            adopter.setNameAdopter("Javier Test");
            adopter.setPhonenumberAdopter("12345678");
            addAdopter(connection, adopter);
            List<Integer> listPets = new ArrayList<>();
            listPets.add(1);
            listPets.add(4);
            addNewAdoption(connection, 1, listPets);
            System.out.println("Inserts Completed!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addPet(Connection connection, Animal pet) {
        String sql = "Insert into pets(type_pet,name_pet,breed_pet) values (?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pet.getTypePet());
            ps.setString(2, pet.getNamePet());
            ps.setString(3, pet.getBreedPet());

            int rows = ps.executeUpdate();
            System.out.println("Rows: " + rows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void addAdopter(Connection connection, Person adopter) {
        String sql = "Insert into adopter(name_adopter,phone_adopter) values (?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, adopter.getNameAdopter());
            ps.setString(2, adopter.getPhonenumberAdopter());
            int rows = ps.executeUpdate();
            System.out.println("Rows: " + rows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void addNewAdoption(Connection connection, Integer Id_adopter, List<Integer> ListPets) {
        String sql = "Insert into adoption_process(adoption_date,id_adopter) values (?,?)";
        //Insert the AdoptionProcess and get ID to add the list of pets
        int newId = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, LocalDate.parse("2024-05-18"));
            ps.setInt(2, Id_adopter);

            int rows = ps.executeUpdate();

            try (var keys = ps.getGeneratedKeys();) {
                keys.next();
                newId = keys.getInt(1);
            }
            System.out.println("rowsAffected: " + rows + ", newId: " + newId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sqlAddListPets = "Insert into pets_list(id_order,id_pet) values(?,?)";
        for (Integer id_pet : ListPets) {
            try {
                PreparedStatement ps = connection.prepareStatement(sqlAddListPets);
                ps.setInt(1, newId);
                ps.setInt(2, id_pet);
                int rows = ps.executeUpdate();
                System.out.println("rowsAffected: " + rows);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Finished!");
        }
    }

    public static List<AdoptionProcess> getAdoptions(Connection connection)
    {
        String sql = """
                SELECT
                    ap.id_order as "id_order",
                    ap.adoption_date as "adoption_date",
                    ad.name_adopter as "name_adopter",
                    ad.phone_adopter as "phone_adopter"
                FROM adoption_process ap
                INNER JOIN adopter ad on ap.id_adopter = ad.id_adopter;""";
        List<AdoptionProcess> adoptionProcessList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                AdoptionProcess adp = new AdoptionProcess();
                adp.setId(resultSet.getInt("id_order"));
                adp.setDateAdoption(resultSet.getObject("adoption_date",LocalDate.class));
                Person adopter = new Person();
                adopter.setNameAdopter(resultSet.getString("name_adopter"));
                adopter.setPhonenumberAdopter(resultSet.getString("phone_adopter"));
                adp.setClient(adopter);
                adoptionProcessList.add(adp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sqlGetPets = """
                SELECT
                    ap.id_order,
                    p.type_pet as "type_pet",
                    p.name_pet as "name_pet",
                    p.breed_pet as "breed_pet"
                FROM adoption_process ap
                INNER JOIN public.pets_list pl on ap.id_order = pl.id_order
                INNER JOIN public.pets p on p.id_pet = pl.id_pet
                WHERE ap.id_order = ?;""";

        for(AdoptionProcess adoptionProcess : adoptionProcessList) {
            List<Animal> petlist = new ArrayList<>();
            try {
                PreparedStatement ps2 = connection.prepareStatement(sqlGetPets);
                ps2.setInt(1,adoptionProcess.getId());
                ResultSet results2 = ps2.executeQuery();
                while (results2.next())
                {
                    Animal pet = new Animal();
                    pet.setNamePet(results2.getString("name_pet"));
                    pet.setTypePet(results2.getString("type_pet"));
                    pet.setBreedPet(results2.getString("breed_pet"));
                    petlist.add(pet);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            adoptionProcess.setPet(petlist);
        }

        return adoptionProcessList;
    }
}
