package com.example.EMS_UST;



import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class EmsUstApplicationTests 
{
	
	
	@Autowired 
	EMPUSTController con;
	

	
	void contextLoads() 
	{
		
			System.out.println("Hello World");
		
	}
	
	
	//@Test
	public void TS001()
	{
		GetAndSet GS=new GetAndSet();
		GS.setColor("Red");
		System.out.println(GS.getColor());
		assertEquals("Yellow", GS.getColor());
		
	}
	
	
	@Test
	public void TS002()
	{
		EMS ems=EMSDATA();
		
		ResponseEntity response=con.addEMP(ems);
		System.out.println(response.getStatusCode());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	
	public EMS EMSDATA()
	{
		EMS ems=new EMS();
		//ems1.seteID("Azees1");
		ems.seteNAME("Aishwarya");
		ems.seteSAL("50000");
		return ems;
	}
	
	
	// Mockito ---> Framework for mocking external dependencies 
	
	
	

}
