package ttl.Project.App;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ttl.Project.Domain.AdoptionProcess;
import ttl.Project.Service.AdoptionService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/adoption")
public class AdoptionsController {
    @Autowired
    private AdoptionService adoptionService;

    @GetMapping("/hello")
    public String Hello()
    {
        return "hello";
    }

    @GetMapping("/GetAllAdoptions")
    public List<AdoptionProcess> GetAllAdoptions(){
        List<AdoptionProcess> list = adoptionService.getAllAdoptions();
        return  list;
    }

    @PostMapping("/addAdoption")
    public ResponseEntity<?> addAdoption(@RequestBody AdoptionProcess adoptionProcess){
        AdoptionProcess newAdoption = adoptionService.addAdoption(adoptionProcess);
        URI newResource = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newAdoption.getId())
                .toUri();
        return ResponseEntity.created(newResource).build();
    }

    @PutMapping("/updateAdoption")
    public ResponseEntity<?> updateAdoption(@RequestBody AdoptionProcess adoptionProcess){
        boolean result = adoptionService.updateAdoption(adoptionProcess);
        if(!result){
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("No Adoption with the ID: "+ adoptionProcess.getId());
        }
        return  ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAdoption/{id}")
    public ResponseEntity<?> deleteAdoption(@PathVariable("id") int id){
        boolean result = adoptionService.deleteAdoption(id);
        if(!result){
            return  ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("No Adoption with the ID: "+id);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAdoptionByID/{id}")
    public ResponseEntity<?> findByIdAdoption(@PathVariable("id") int id){
        AdoptionProcess adoptionProcess = adoptionService.findById(id);
        if(adoptionProcess == null){
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("No Adoption with id: "+ id);
        }
        return ResponseEntity.ok(adoptionProcess);
    }


}
