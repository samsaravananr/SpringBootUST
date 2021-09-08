package com.example.EMS_UST;


import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class EMPUSTController 
{
	
	AtomicInteger counter=new AtomicInteger();
	
	@Autowired
	EMSRepository
	repository;
	
	@Autowired
	EMSAddService emsservice;
	
	
	@PostMapping("/addEmployee")
	public ResponseEntity<AddResponse> addEMP(@RequestBody EMS ems)
	{
		HttpHeaders headers=new HttpHeaders();
		String id=ems.geteNAME()+counter.getAndIncrement();
		AddResponse add=new AddResponse();
		
		if(!emsservice.checkEMPAlreadyExist(id))
		{
		ems.seteID(id);
		repository.save(ems);
		// NOW to get the response, We will create one bean class with ID and MSG (class Name AddResponse)
		
		add.setId(id);
		add.setMsg("Success : Employee is Added");
		headers.add("Unique", id);
		return new ResponseEntity<AddResponse>(add,headers,HttpStatus.CREATED);
		}
		else
		{
			//if it is true
			add.setId(id);
			add.setMsg("Employee Already Exists");
			
			return new ResponseEntity<AddResponse>(add,headers,HttpStatus.ACCEPTED); 
		}
	}
	

	@GetMapping("/getEmployee/{id}")
	public EMS getEmployeeByID(@PathVariable(value="id")String id)
	{
		try
		{
		EMS ems=repository.findById(id).get();
		
		
		return ems;
		}
		catch(Exception  e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		// 1. It should display NOT FOUND HTTP STATUS CODE for INVALID DATA
		// Try and Catch (Hint ResponseStatusException)
	}

	

@GetMapping("/getEmployee")
public List<EMS> getEMployeeByName(@RequestParam(value = "EmployeeName")String EmployeeName)
{
	
	//return repository.findAllByeName(EmployeeName);
	return repository.findAllByeName(EmployeeName);
	
	//0. Add EMSRepositoryCusom to the EMSRepository interface  
			//1. public interface EMSRepository extends JpaRepository<EMS, String>,EMSRepositoryCustom
			
			//2. Create an Interface called EMSRepositoryCustom
			//IN the EMSRepository --> Rightlick on the error statement (EMSRepositoryCustom) --> Add Interface 
			
			//3. Add the following line to the EMSRepositoryCustom interface - List<EMS> findAllByEMPName(String employeename);
			
			//4. Create One Class Called EMSRepositoryImpl
			//5. Add implements EMSRepositoryCustom to this class
			//6. Add the unimplemented method to the above class
			//7. Autowire EMSRepository Interface
			//8. Write the code inside the EMSRepository Interface method that you called in the EMSRepositoryImpl Class
			//9. Write GetMapping Logic in the Controller Class
	
}

@PutMapping("/updateEmployee/{id}")
public ResponseEntity<EMS> updateEmployee(@PathVariable(value = "id")String id,@RequestBody EMS ems)
{
	EMS empRecord=repository.findById(id).get();
	empRecord.seteNAME(ems.geteNAME());
	empRecord.seteSAL(ems.geteSAL());
	repository.save(empRecord);
	return new ResponseEntity<EMS>(empRecord,HttpStatus.OK);
	
}

@DeleteMapping("/deleteEmployee")
public ResponseEntity<String> deleteEmployee(@RequestBody EMS ems)
{
	EMS delID=repository.findById(ems.geteID()).get();
	repository.delete(delID);
	return new ResponseEntity<>("Employee Record Deleted",HttpStatus.CREATED);
	
}


@DeleteMapping("/deleteAll")
public ResponseEntity<String> deleteAllEmployee()
{
	repository.deleteAll();
	return new ResponseEntity<>("All Employee Record Deleted",HttpStatus.CREATED);
}



//1 Implement The Delete Code
//2 Develop the Below COntract - Delete All Records
		//END PONIT: http://localhost:8080/deleteAll
		//HTTP Method : Delete
		//NO Pay Load
		//Response Body : It should print "All Employee Record Deleted"
		//Response Status Code : 201 – Created
}	
		

