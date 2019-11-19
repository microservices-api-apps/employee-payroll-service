package com.payroll.microservices.employeepayrollservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "role-service", url = "localhost:8101")
public interface RoleService {

	@GetMapping("/role/{roleName}")
	public EmployeePayroll getRoleByRoleName(@PathVariable("roleName") String roleName);
}
