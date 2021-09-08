package com.example.EMS_UST;



import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmsUstApplicationTests 
{

	
	void contextLoads() 
	{
		
			System.out.println("Hello World");
		
	}
	
	
	@Test
	public void TS001()
	{
		GetAndSet GS=new GetAndSet();
		GS.setColor("Red");
		System.out.println(GS.getColor());
		assertEquals("Yellow", GS.getColor());
		
	}
	
	
	
	
	

}
