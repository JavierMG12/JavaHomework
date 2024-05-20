package ttl.project.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
@Entity
@Table(name = "adoption_process")
public class AdoptionProcess {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_order")
    private int id;

    @Column(name = "adoption_date")
    private LocalDate dateAdoption;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Person client;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Animal> pets;

    public AdoptionProcess(){

    }

    public AdoptionProcess(int id, LocalDate dateAdoption, Person client, List<Animal> pets) {
        setId(id);
        setDateAdoption(dateAdoption);
        setClient(client);
        setPet(pets);
    }


    public Person getClient() {
        return client;
    }

    public void setClient(Person client) {
        this.client = client;
    }

    public List<Animal> getPets() {
        return pets;
    }

    public void setPet(List<Animal> pets) {
        this.pets = pets;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateAdoption() {
        return dateAdoption;
    }

    public void setDateAdoption(LocalDate dateAdoption) {
        this.dateAdoption = dateAdoption;
    }

    @Override
    public String toString() {
        return "AdoptionProcess{" +
                "id=" + id +
                ", dateAdoption=" + dateAdoption +
                ", Id Client=" + client.getIdAdopter() +
                ", Client Name=" + client.getNameAdopter() +
                ", Client phone number=" + client.getPhonenumberAdopter()+
                ", Pets= "+ getPets();
    }
}
