package com.bookaro.server.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bookaro.server.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
