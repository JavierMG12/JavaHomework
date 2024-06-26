package ttl.Project.dao;

import ttl.Project.Domain.AdoptionProcess;

import java.time.LocalDate;
import java.util.List;

public interface AdoptionDOA {
    AdoptionProcess insert(AdoptionProcess adoptionProcess);

    boolean delete(int id);

    boolean update(AdoptionProcess adoptionProcess);

    AdoptionProcess findById(int id);

    List<AdoptionProcess> findAll();

    AdoptionProcess findByName(String Name);

    List<AdoptionProcess> findbyDate(LocalDate date);
}
