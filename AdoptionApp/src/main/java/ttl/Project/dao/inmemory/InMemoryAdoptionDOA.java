package ttl.Project.dao.inmemory;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ttl.Project.Domain.AdoptionProcess;
import ttl.Project.dao.AdoptionDOA;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
@Profile("dev")
public class InMemoryAdoptionDOA implements AdoptionDOA {

    public InMemoryAdoptionDOA(){
        int stop=0;
    }
    private Map<Integer, AdoptionProcess> Adoptions = new HashMap<>();
    private int nextId=1;

    @Override
    public AdoptionProcess insert(AdoptionProcess adoptionProcess)
    {
        int id = nextId++;
        adoptionProcess.setId(id);
        adoptionProcess.getClient().setNameAdopter(adoptionProcess.getClient().getNameAdopter());
        Adoptions.put(adoptionProcess.getId(),adoptionProcess);
        return  adoptionProcess;
    }

    @Override
    public boolean delete(int id){
        return Adoptions.remove(id) != null;
    }

    @Override
    public boolean update(AdoptionProcess adoptionProcess){
        return Adoptions.replace(adoptionProcess.getId(), adoptionProcess) != null;
    }

    @Override
    public AdoptionProcess findById(int id){
        return Adoptions.get(id);
    }

    @Override
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
            if(adoptionProcess.getDateAdoption().equals(date))
            {
                List.add(adoptionProcess);
            }
        }
        return List;
    }


}
