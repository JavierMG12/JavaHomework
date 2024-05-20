package ttl.project.domain;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.ArrayList;
import java.util.List;

public class JDBCTemplate {
    public static void main(String[] args) {
        JDBCTemplate jtd = new JDBCTemplate();
        String url = "jdbc:postgresql://localhost:5433/adoptapp";
        String user = "larku";
        String pw = "larku";

        DriverManagerDataSource dataSource = new DriverManagerDataSource(url, user, pw);
        JdbcTemplate template = new JdbcTemplate(dataSource);
        jtd.addNewPet(template);
        jtd.getAllPets(template);
       String result = jtd.DeletePetbyID(20,template);
    }


    public void addNewPet(JdbcTemplate template) {
        String insertStudentSql = "Insert into pets(type_pet,name_pet,breed_pet) values (?,?,?)";
        Object [] arr = new Object[]{"DOG","Test","Test"};
        List<Object[]> params = new ArrayList<>();
        params.add(arr);
        int numRows = 0;
        for(Object[] args : params) {
            numRows += template.update(insertStudentSql, args);
        }
        System.out.println("numRows: " + numRows);
    }

    public void getAllPets(JdbcTemplate template) {
        String sql = "select * from pets";
        RowMapper<Animal> rowMapper = (resultSet, rowNum) -> {
            int id = resultSet.getInt("id_pet");
            String typePet = resultSet.getString("type_pet");
            String namePet = resultSet.getString("name_pet");
            String breedPet = resultSet.getString("breed_pet");
            String breedPet2 = resultSet.getString(4);
            var newObj = new Animal();
            newObj.setIdPet(id);
            newObj.setTypePet(typePet);
            newObj.setNamePet(namePet);
            newObj.setBreedPet(breedPet);
            return newObj;
        };
        List<Animal> animals = template.query(sql, rowMapper);
        System.out.println("Pet: " + animals);
    }

    public String DeletePetbyID(Integer id, JdbcTemplate template){
        String sql = "DELETE FROM pets WHERE id_pet = ?";
        Object[] args = new Object[] {id};

        int updt = template.update(sql, args);
        String updatecount = "Failed";
        if (updt == 0) {
            updatecount = "Failed";
        } else {
            updatecount = "SUCCESS";
        }
        return updatecount;
    }
}
