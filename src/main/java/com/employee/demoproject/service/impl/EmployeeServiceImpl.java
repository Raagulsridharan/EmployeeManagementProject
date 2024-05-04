package com.employee.demoproject.service.impl;

import com.employee.demoproject.dao.DepartmentDAO;
import com.employee.demoproject.dao.EmployeeDAO;
import com.employee.demoproject.dto.EmployeeDTO;
import com.employee.demoproject.dto.LeaveAssignDTO;
import com.employee.demoproject.dto.LoginDetailsDTO;
import com.employee.demoproject.entity.Department;
import com.employee.demoproject.entity.Employee;
import com.employee.demoproject.entity.LoginDetails;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.exceptions.DataServiceException;
import com.employee.demoproject.pagination.FilterOption;
import com.employee.demoproject.service.EmployeeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private DepartmentDAO departmentDAO;

    static Logger logger = Logger.getLogger(EmployeeServiceImpl.class);

    @Override
    public LoginDetailsDTO createEmployee(EmployeeDTO employeeDTO) throws BusinessServiceException{
        try{
            logger.info("Creating employee in service");
            return mapToLoginDTO(employeeDAO.createEmployee(mapToEntity(employeeDTO)));
        }catch (DataServiceException ex){
            logger.error("Error while creating employee in service. "+ex);
            throw new BusinessServiceException("Employee already exists!", ex);
        }
    }

    @Override
    public EmployeeDTO updateEmployee(int empId, EmployeeDTO employeeDTO) throws BusinessServiceException {
        try {
            logger.info("Updating employee in service");
            Employee employee = employeeDAO.updateEmployee(empId,employeeDTO);
            return mapToDTO(employee);
        }catch (DataServiceException e){
            logger.error("Error in updating employee in service layer. "+e);
            throw new BusinessServiceException("Exception in updating employee in service layer",e);
        }
    }

    @Override
    public List<EmployeeDTO> getAllEmployee() throws BusinessServiceException{
        try{
            logger.info("getting all employees in service layer. ");
            List<Employee> employeeList = employeeDAO.getAllEmployee();
            List<EmployeeDTO> empDTOList = new ArrayList<>();
            for(Employee employee: employeeList){
                empDTOList.add(mapToDTO(employee));
            }
            return empDTOList;
        }catch (DataServiceException e){
            logger.error("Error in getting all employees in service layer. "+e);
            throw new BusinessServiceException("Exception in getting all employees in service layer. ",e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(int empId) throws BusinessServiceException{
        try{
            logger.info("Getting employee by ID in service layer. ");
            return mapToDTO(employeeDAO.getEmployeeById(empId));
        }catch (DataServiceException e){
            logger.error("Error in getting employee by ID in service layer. "+e);
            throw new BusinessServiceException("Exception in getting employee by ID in service layer.",e);
        }
    }

    @Override
    public List<EmployeeDTO> getAllEmployeeByDeptForRoleAssign(int deptId)  throws BusinessServiceException {
        try {
            logger.info("getting employees for role assign in service layer");
            List<Employee> employeeList = employeeDAO.getAllEmployeeByDeptForRoleAssign(deptId);
            List<EmployeeDTO> employeeDTOList = new ArrayList<>();
            for(Employee employee : employeeList){
                employeeDTOList.add(mapToDTO(employee));
            }
            return employeeDTOList;
        }catch (DataServiceException e){
            logger.error("Error in getting employees for role assign in service layer. "+e);
            throw new BusinessServiceException("Exception in getting employees for role assign in service layer. ",e);
        }
    }

    @Override
    public List<EmployeeDTO> getAllEmployeeByDeptForPayroll(int deptId)  throws BusinessServiceException {
        try {
            logger.info("getting employees for payroll in service layer");
            List<Employee> employeeList = employeeDAO.getAllEmployeeByDeptForPayroll(deptId);
            List<EmployeeDTO> employeeDTOList = new ArrayList<>();
            for(Employee employee : employeeList){
                employeeDTOList.add(mapToDTO(employee));
            }
            return employeeDTOList;
        }catch (DataServiceException e){
            logger.error("Error in getting employees for payroll in service layer. "+e);
            throw new BusinessServiceException("Exception in getting employees for payroll in service layer. ",e);
        }
    }

    @Override
    public List<EmployeeDTO> getAllEmployeeByDeptForLeaveAssign(int deptId)  throws BusinessServiceException {
        try {
            logger.info("getting employees for leave assign in service layer");
            List<Employee> employeeList = employeeDAO.getAllEmployeeByDeptForLeaveAssign(deptId);
            List<EmployeeDTO> employeeDTOList = new ArrayList<>();
            for(Employee employee : employeeList){
                employeeDTOList.add(mapToDTO(employee));
            }
            return employeeDTOList;
        }catch (DataServiceException e){
            logger.error("Error in getting employees for leave assign in service layer. "+e);
            throw new BusinessServiceException("Exception in getting employees for leave assign in service layer. ",e);
        }
    }

    @Override
    public Long getEmpCount() throws BusinessServiceException {
        try {
            logger.info("Getting employees count in service layer.");
            return employeeDAO.getTotalEmployeeCount();
        }catch (DataServiceException e){
            logger.error("Error in Getting employees count in service layer.");
            throw new BusinessServiceException("Exception in Getting employees count in service layer.",e);
        }

    }

    @Override
    public void deleteEmployee(int empId) throws BusinessServiceException {
        try{
            logger.info("Deleting employee in service layer. ");
            employeeDAO.deleteEmployee(empId);
        }catch (DataServiceException e){
            logger.error("Error in Deleting employee in service layer. ");
            throw new BusinessServiceException("Exception in Deleting employee in service layer. ",e);
        }
    }

    @Override
    public LeaveAssignDTO getEmployeeDetailCard(int empId) throws BusinessServiceException {
        try{
            return employeeDAO.getEmployeeDetailCard(empId);
        }catch (DataServiceException e){
            throw new BusinessServiceException("Exception in getting employee details card in service layer",e);
        }
    }

    @Override
    public List<EmployeeDTO> filterEmployees(FilterOption filterOption) throws BusinessServiceException {
        try{
            List<Employee> departmentList = employeeDAO.filterEmployee(filterOption);
            return Optional.ofNullable(departmentList)
                    .map(list -> list.stream()
                            .map(this::mapToDTO)
                            .collect(Collectors.toList()))
                    .orElse(null);
        }catch (DataServiceException e){
            throw new BusinessServiceException("Exception in service layer for filtering",e);
        }
    }

    private EmployeeDTO mapToDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setEmp_name(employee.getName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setMobile(employee.getMobile());
        employeeDTO.setAddress(employee.getAddress());
        employeeDTO.setGender(employee.getGender());
        employeeDTO.setBirthday(employee.getBirthday());
        employeeDTO.setDepartmentId(employee.getDepartment().getId());
        employeeDTO.setDepartment(employee.getDepartment().getName());
        return employeeDTO;
    }

    private Employee mapToEntity(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        employee.setName(employeeDTO.getEmp_name());
        employee.setGender(employeeDTO.getGender());
        employee.setEmail(employeeDTO.getEmail());
        employee.setMobile(employeeDTO.getMobile());
        employee.setBirthday(employeeDTO.getBirthday());
        employee.setAddress(employeeDTO.getAddress());
        Department department = departmentDAO.getDepartmentById(employeeDTO.getDepartmentId());
        employee.setDepartment(department);
        return employee;
    }

    private LoginDetailsDTO mapToLoginDTO(LoginDetails loginDetails){
        LoginDetailsDTO loginDetailsDTO = new LoginDetailsDTO();
        loginDetailsDTO.setId(loginDetails.getId());
        loginDetailsDTO.setUsername(loginDetails.getUsername());
        loginDetailsDTO.setPassword(loginDetails.getPassword());
        loginDetailsDTO.setFlag(loginDetails.getFlag());
        loginDetailsDTO.setDeptId(loginDetails.getEmployee_login().getDepartment().getId());
        if(loginDetails.getActivated_on()!=null){
            loginDetailsDTO.setActivatedOn(loginDetails.getActivated_on());
        }
        loginDetailsDTO.setEmployee(loginDetails.getEmployee_login());
        loginDetailsDTO.setEmpId(loginDetails.getEmployee_login().getId());
        return loginDetailsDTO;
    }
}
