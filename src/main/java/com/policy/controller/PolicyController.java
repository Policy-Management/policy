package com.policy.controller;

import com.policy.entity.PolicyEntity;
import com.policy.repository.PolicyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Optional;

@CrossOrigin
@RestController
@Slf4j
public class PolicyController {
    HashMap<String,String> map = new HashMap<>();
    @Autowired
    private PolicyRepository policyRepo;

    @GetMapping("/getall")
    public ResponseEntity<?> getPolicy(){
        return new ResponseEntity(policyRepo.findAll(), HttpStatus.CREATED);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPolicy(@PathVariable String id){

        return new ResponseEntity(policyRepo.findById(id), HttpStatus.CREATED);

    }

    @PostMapping("/save")
    public ResponseEntity<?> savePolicy(@RequestBody PolicyEntity policy){

        map.put("VehicleInsurance","VI");
        map.put("TravelInsurance","TI");
        map.put("LifeInsurance","LI");
        map.put("HealthInsurance","HI");
        map.put("ChildPlans","CP");
        map.put("RetirementPlans","RP");
        log.info("inside policy controller");
        LocalDate localDate = policy.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year  = localDate.getYear();
        long count = policyRepo.countAllDocuments()+1;
        policy.setId(map.get(policy.getPolicyType())+year+"-"+count);
        return new ResponseEntity<>(policyRepo.save(policy),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name="id") String id,@RequestBody PolicyEntity policy){
        Optional<PolicyEntity> policy1 = policyRepo.findById(id);
        if(policy1!=null){
            policy.setId(id);
            policy.setStartDate(policy1.get().getStartDate());
            policy.setPolicyType(policy1.get().getPolicyType());
            policyRepo.save(policy);
        }
        return new ResponseEntity<>(policyRepo.findById(id),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") String id){
        log.info("deleting policy");
        if(policyRepo.findById(id).isPresent()){
            log.info(String.valueOf(policyRepo.findById(id)));
            policyRepo.deleteById(id);
            return new ResponseEntity<>("Deleted",HttpStatus.CREATED);
        }
        log.info("out of if statement");
        return new ResponseEntity<>("Not Deleted",HttpStatus.NOT_FOUND);
    }
}