package com.bft.spring;


import com.bft.spring.model.Company;
import com.bft.spring.model.DomainEntity;
import com.bft.spring.model.SubdivisionPU;
import com.bft.spring.model.TimeZone;
import com.bft.spring.service.DataBaseService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.bft.spring.configuration.AppConfig;


public class AppMain {

	public static void main2(String args[]) {
//		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//		DataBaseService dataBaseService = (DataBaseService) context.getBean("dataBaseService");
//
//
//		//_______________________
//
//		TimeZone timeZone = new TimeZone();
//		timeZone.setName("tz00");
//		//dataBaseService.save(timeZone);
//
//
//		SubdivisionPU subdivisionPU = new SubdivisionPU();
//		subdivisionPU.setName("sn00");
//		//dataBaseService.save(subdivisionPU);
//
//		Company company = new Company();
//		company.setActual(true);
//		company.setActualAdress("adr");
//		company.setTimeZone(timeZone);
//		company.setSubdivisionPU(subdivisionPU);
//		dataBaseService.save(company);
//
//		for (DomainEntity domainEntity : dataBaseService.findAll(SubdivisionPU.class)){
//			System.out.println(((SubdivisionPU)domainEntity).getName());
//		}
//
//		for (DomainEntity domainEntity : dataBaseService.findAll(Company.class)){
//			System.out.println(((Company) domainEntity));
//		}
		//_______________________

//		timeZone = new TimeZone();
//		timeZone.setName("tz12345");
//		dataBaseService.save(timeZone);
//
//
//		subdivisionPU = new SubdivisionPU();
//		subdivisionPU.setName("sn12345");
//		dataBaseService.save(subdivisionPU);
//
//		timeZone = (TimeZone)dataBaseService.findByName("tz12345",TimeZone.class);
//		subdivisionPU = (SubdivisionPU)dataBaseService.findByName("sn12345",SubdivisionPU.class);
//
//		company = new Company();
//		company.setTimeZone(timeZone);
//		company.setSubdivisionPU(subdivisionPU);
//		dataBaseService.saveOrUpdate(company);



		//context.close();
	}
}
