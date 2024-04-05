package com.employee.demoproject.service.impl;

import com.employee.demoproject.dao.EmployeeHasLeaveDAO;
import com.employee.demoproject.dto.EmployeeHasLeaveDTO;
import com.employee.demoproject.entity.EmployeeHasLeave;
import com.employee.demoproject.entity.LeavePolicy;
import com.employee.demoproject.service.EmployeeHasLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeHasLeaveServiceImpl implements EmployeeHasLeaveService {

    @Autowired
    private EmployeeHasLeaveDAO employeeHasLeaveDAO;

    @Override
    public void assignLeaveForEmployee(int id, List<EmployeeHasLeaveDTO> employeeHasLeaveDTO) {
        employeeHasLeaveDAO.assignLeaveForEmployee(id,employeeHasLeaveDTO);
    }

    @Override
    public List<EmployeeHasLeaveDTO> getAllEmployeeLeaves() {
        return employeeHasLeaveDAO.getAllEmployeesLeaves();
    }
}
