package com.payroll.microservices.employeepayrollservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeePayrollController {
	
	@Autowired
	EmployeePayrollRepository employeePayrollRepository;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	RoleService roleService;

	@PostMapping("/employee/{empId}/role/{roleName}")
	public void insertEmployeePayrollDetails(@PathVariable Long empId, @PathVariable String roleName) {
		//ResponseEntity<EmployeePayroll> employeeEntity = new RestTemplate().getForEntity("http://localhost:8080/employee/{empId}", EmployeePayroll.class, empId);
		//ResponseEntity<EmployeePayroll> roleEntity = new RestTemplate().getForEntity("http://localhost:8101/role/{roleName}", EmployeePayroll.class, roleName);
		EmployeePayroll employeePayroll = employeeService.getEmployeeDetails(empId);
		EmployeePayroll roleDetails = roleService.getRoleByRoleName(roleName);
		employeePayroll.setRoleId(roleDetails.getRoleId());
		employeePayroll.setRoleName(roleDetails.getRoleName());
		employeePayroll.setDescription(roleDetails.getDescription());
		employeePayrollRepository.save(employeePayroll);
	}
	
}
