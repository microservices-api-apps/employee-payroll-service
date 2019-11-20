package com.payroll.microservices.employeepayrollservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "employee-service", url = "localhost:8001")
@FeignClient(name = "employee-service")
@RibbonClient(name = "employee-service")
public interface EmployeeService {

	@GetMapping("/employee/{empId}")
	public EmployeePayroll getEmployeeDetails(@PathVariable("empId") Long empId);

}
