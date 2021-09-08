package com.example.EMS_UST;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class EMSRepositoryImpl implements EMSRepositoryCustom 
{

	@Autowired
	EMSRepository repository;
	
	
	@Override
	public List<EMS> findAllByeName(String eName) 
	{
	
		List<EMS> eNames=new ArrayList<EMS>();
		List<EMS> emps=repository.findAll();
		
		for(EMS items:emps)
		{
			if(items.geteNAME().equalsIgnoreCase(eName))
			{
				eNames.add(items);
			}
			
		}
		return eNames;

	}
	

}
