package ttl.Project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ttl.Project.Domain.AdoptionProcess;
import ttl.Project.dao.AdoptionDOA;
import ttl.Project.dao.DOAFactory;

import java.time.LocalDate;
import java.util.List;
@Service
public class AdoptionService {
    @Autowired
    private AdoptionDOA adoptionDOA;

    public AdoptionService(){
        //adoptionDOA = DOAFactory.adoptionDOA();
        int stop = 1;
    }

    public AdoptionProcess addAdoption(AdoptionProcess adoptionProcess){
        AdoptionProcess insertAdoption = adoptionDOA.insert(adoptionProcess);
        return  adoptionProcess;
    }

    public boolean deleteAdoption(int id){
        return adoptionDOA.delete(id);
    }

    public boolean updateAdoption(AdoptionProcess adoptionProcess){
        return adoptionDOA.update(adoptionProcess);
    }

    public AdoptionProcess findById(int id){
        return adoptionDOA.findById(id);
    }

    public List<AdoptionProcess> getAllAdoptions(){
        return adoptionDOA.findAll();
    }

    public AdoptionProcess findByName(String name){
        return adoptionDOA.findByName(name);
    }

    public List<AdoptionProcess> findbyDate(LocalDate date){
        return adoptionDOA.findbyDate(date);
    }

    public AdoptionDOA getAdoptionDOA() {
        return adoptionDOA;
    }

    public void setAdoptionDOA(AdoptionDOA adoptionDOA) {
        this.adoptionDOA = adoptionDOA;
    }

}
