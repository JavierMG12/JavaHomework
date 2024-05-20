package ttl.Project.jconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ttl.Project.Service.AdoptionService;
import ttl.Project.dao.AdoptionDOA;
import ttl.Project.dao.inmemory.InMemoryAdoptionDOA;

@Configuration
@ComponentScan({"ttl.Project"})
public class AdoptionConfig {
//    @Bean
//    public AdoptionDOA adoptionDOA(){
//        AdoptionDOA doa = new InMemoryAdoptionDOA();
//        return doa;
//    }

//    @Bean
//   public  AdoptionService adoptionService(){
//       AdoptionService service = new AdoptionService();
//       AdoptionDOA doa = adoptionDOA();
//        service.setAdoptionDOA(doa);
//        return service;
//    }

//    @Bean
//    public AdoptionService adoptionService(AdoptionDOA adoptionDOA) {
//        AdoptionService newService = new AdoptionService();
//
//        AdoptionDOA dao = adoptionDOA;
////      StudentDAO dao = studentDAO();
//
//        newService.setAdoptionDOA(dao);
//        return newService;
//    }
}
