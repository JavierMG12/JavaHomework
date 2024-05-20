package ttl.Project.Domain;

import jakarta.persistence.*;

@Entity
@Table(name = "pets")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pet")
    private int idPet;
    @Column(name = "type_pet")
    private String typePet;
    @Column(name = "name_pet")
    private String namePet;
    @Column(name="breed_pet")
    private String breedPet;

    private enum Pets{
        DOG,
        CAT,
        TURTLE
    }
    public Animal(){

    }

    public Animal(int idPet){
        setIdPet(idPet);
    }

    public  Animal(int idPet,String namePet)
    {
        setIdPet(idPet);
        setNamePet(namePet);
    }
    public  Animal(int idPet,String namePet,String typePet)
    {
        setIdPet(idPet);
        setNamePet(namePet);
        setTypePet(typePet);
    }

    public  Animal(int idPet,String namePet,String typePet, String breedPet)
    {
        setIdPet(idPet);
        setNamePet(namePet);
        setTypePet(typePet);
        setBreedPet(breedPet);
    }

    public int getIdPet() {
        return idPet;
    }

    public void setIdPet(int idPet) {
            this.idPet = idPet;
    }

    public String getTypePet() {
        return typePet;
    }

    public void setTypePet(String typePet) {
        if (Pets.valueOf(typePet.toUpperCase()) == Pets.DOG || Pets.valueOf(typePet.toUpperCase()) == Pets.CAT || Pets.valueOf(typePet.toUpperCase()) == Pets.TURTLE) {
            this.typePet = typePet;
        }else {
            throw new RuntimeException("The pet type does not exist: " + typePet);
        }
    }

    public String getNamePet() {
        return namePet;
    }

    public void setNamePet(String namePet) {
        if(namePet != null) {
            this.namePet = namePet;
        }else{
            this.namePet = "";
        }
    }

    public String getBreedPet() {
        return breedPet;
    }

    public void setBreedPet(String breedPet) {
        if(breedPet != null) {
            this.breedPet = breedPet;
        }else{
            this.breedPet = "";
        }
    }

    @Override
    public String toString() {
        return "Animal{" +
                "idPet=" + idPet +
                ", typePet='" + typePet + '\'' +
                ", namePet='" + namePet + '\'' +
                ", breedPet='" + breedPet + '\'' +
                '}';
    }
}
