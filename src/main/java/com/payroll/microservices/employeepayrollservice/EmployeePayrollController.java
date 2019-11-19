package com.payroll.microservices.employeepayrollservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class EmployeePayrollController {
	
	@Autowired
	EmployeePayrollRepository employeePayrollRepository;

	@PostMapping("/employee/{empId}/role/{roleName}")
	public void insertEmployeePayrollDetails(@PathVariable Long empId, @PathVariable String roleName) {
		ResponseEntity<EmployeePayroll> employeeEntity = new RestTemplate().getForEntity("http://localhost:8080/employee/{empId}", EmployeePayroll.class, empId);
		ResponseEntity<EmployeePayroll> roleEntity = new RestTemplate().getForEntity("http://localhost:8101/role/{roleName}", EmployeePayroll.class, roleName);
		EmployeePayroll employeePayroll = employeeEntity.getBody();
		employeePayroll.setRoleId(roleEntity.getBody().getRoleId());
		employeePayroll.setRoleName(roleEntity.getBody().getRoleName());
		employeePayroll.setDescription(roleEntity.getBody().getDescription());
		employeePayrollRepository.save(employeePayroll);
	}
	
}
