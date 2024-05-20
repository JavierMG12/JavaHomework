package ttl.Project.Domain;

import jakarta.persistence.*;

@Entity
@Table(name = "adopter")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_adopter")
    private int idAdopter;
    @Column(name = "name_adopter")
    private String nameAdopter;
    @Column(name = "phone_adopter")
    private String phonenumberAdopter;

    public Person(){

    }

    public Person(int idAdopter)
    {
        setIdAdopter(idAdopter);
    }

    public Person(int idAdopter, String nameAdopter)
    {
        setIdAdopter(idAdopter);
        setNameAdopter(nameAdopter);
    }

    public Person(int idAdopter,String nameAdopter,String phonenumberAdopter)
    {
        setIdAdopter(idAdopter);
        setNameAdopter(nameAdopter);
        setPhonenumberAdopter(phonenumberAdopter);
    }

    public int getIdAdopter() {
        return idAdopter;
    }

    public void setIdAdopter(int idAdopter) {
        this.idAdopter = idAdopter;
    }

    public String getNameAdopter() {
        return nameAdopter;
    }

    public void setNameAdopter(String nameAdopter) {
        if(nameAdopter != null) {
            this.nameAdopter = nameAdopter;
        }else{
            this.nameAdopter = "";
        }
    }

    public String getPhonenumberAdopter() {
        return phonenumberAdopter;
    }

    public void setPhonenumberAdopter(String phonenumberAdopter) {
        if(phonenumberAdopter != null) {
            this.phonenumberAdopter = phonenumberAdopter;
        }else{
            this.phonenumberAdopter = "";
        }
    }

    @Override
    public String toString() {
        return "Adopter{" +
                "id Adopter=" + idAdopter +
                ", Name='" + nameAdopter + '\'' +
                ", Phone='" + phonenumberAdopter + '\'' +
                '}';
    }
}
