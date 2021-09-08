package com.example.EMS_UST;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EMSAddService 
{
	
	@Autowired
	EMSRepository repository;
	
	public boolean checkEMPAlreadyExist(String id)
	{
				Optional<EMS> ems=repository.findById(id);
				
				if(ems.isPresent())
				{
					return true;
				}
				
				else
				{
					return false;
				}
				
				//The Optional type was introduced in Java 8. It provides a clear and explicit way to convey the message that there may not be a value, without using null. 
	}
	

}
