package ttl.Project.dao.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ttl.Project.Domain.AdoptionProcess;
import ttl.Project.dao.AdoptionDOA;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository()
@Profile("prod")
public class JPAAdoptionDOA implements AdoptionDOA {
    private Map<Integer, AdoptionProcess> Adoptions = new HashMap<>();
    private int nextId=1;

    public AdoptionProcess insert(AdoptionProcess adoptionProcess)
    {
        int id = nextId++;
        adoptionProcess.setId(id);
        adoptionProcess.getClient().setNameAdopter("JPA: "+ adoptionProcess.getClient().getNameAdopter());
        Adoptions.put(adoptionProcess.getId(),adoptionProcess);
        return  adoptionProcess;
    }

    public boolean delete(int id){
        return Adoptions.remove(id) != null;
    }

    public boolean update(AdoptionProcess adoptionProcess){
        return Adoptions.replace(adoptionProcess.getId(), adoptionProcess) != null;
    }

    public AdoptionProcess findById(int id){
        return Adoptions.get(id);
    }

    public List<AdoptionProcess> findAll(){
        return new ArrayList<>(Adoptions.values());
    }

    @Override
    public AdoptionProcess findByName(String name){
        Integer Idreturn = 0;
        for(Map.Entry<Integer, AdoptionProcess> entry :  Adoptions.entrySet()){
            Integer key = entry.getKey();
            AdoptionProcess adoptionProcess = entry.getValue();
            if(adoptionProcess.getClient().getNameAdopter() == name)
            {
                Idreturn = key;
                break;
            }
        }
        return Adoptions.get(Idreturn);
    }

    @Override
    public List<AdoptionProcess> findbyDate(LocalDate date){
        List<AdoptionProcess> List = new ArrayList<>();
        for(Map.Entry<Integer, AdoptionProcess> entry :  Adoptions.entrySet()){
            Integer key = entry.getKey();
            AdoptionProcess adoptionProcess = entry.getValue();
            if(adoptionProcess.getDateAdoption() == date)
            {
                List.add(adoptionProcess);
            }
        }
        return List;
    }

}
