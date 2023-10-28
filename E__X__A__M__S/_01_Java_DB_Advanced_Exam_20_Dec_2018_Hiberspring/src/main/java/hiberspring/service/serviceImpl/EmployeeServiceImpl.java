package hiberspring.service.serviceImpl;

import hiberspring.common.Constants;
import hiberspring.domain.dtos.ImportEmployeeDTO;
import hiberspring.domain.dtos.ImportEmployeesRootDTO;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Employee;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.repository.EmployeeRepository;
import hiberspring.repository.ProductRepository;
import hiberspring.service.EmployeeService;
import hiberspring.util.ValidationUtilImpl;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final String EMPLOYEES_FILE_PATH = Constants.PATH_TO_FILES + "employees.xml";
    private final EmployeeRepository employeeRepository;
    private final EmployeeCardRepository employeeCardRepository;

    private final BranchRepository branchRepository;

    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtilImpl validator;

    private final ProductRepository productRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               EmployeeCardRepository employeeCardRepository,
                               BranchRepository branchRepository,
                               ModelMapper modelMapper,
                               XmlParser xmlParser,
                               ValidationUtilImpl validator, ProductRepository productRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeCardRepository = employeeCardRepository;
        this.branchRepository = branchRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validator = validator;
        this.productRepository = productRepository;
    }


    @Override
    public Boolean employeesAreImported() {
        return employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesXmlFile() throws IOException {
        return Files.readString(Path.of(EMPLOYEES_FILE_PATH));
    }

    @Override
    public String importEmployees() throws JAXBException {
        ImportEmployeesRootDTO employeesRootDTOs = this.xmlParser.fromFile(EMPLOYEES_FILE_PATH, ImportEmployeesRootDTO.class);
        return employeesRootDTOs.getEmployees().stream().map(this::importProduct).collect(Collectors.joining("\n"));
    }

    private String importProduct(ImportEmployeeDTO dto) {
        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return Constants.INCORRECT_DATA_MESSAGE;
        }

        Optional<Employee> optEmployee = this.employeeRepository.findByEmployeeCardNumber(dto.getCard());


        if (optEmployee.isPresent()) {
            return Constants.INCORRECT_DATA_MESSAGE;
        }
        Employee employee = this.modelMapper.map(dto, Employee.class);

        /// SET Employee-card
        Optional<EmployeeCard> emplCard = this.employeeCardRepository.findByNumber(dto.getCard());
        if (emplCard.isEmpty()) {
            return Constants.INCORRECT_DATA_MESSAGE;
        }
        employee.setEmployeeCard(emplCard.get());
        ////

        //// SET Employee branch
        Optional<Branch> branch = this.branchRepository.findByName(dto.getBranch());
        if (branch.isEmpty()) {
            return Constants.INCORRECT_DATA_MESSAGE;
        }
        employee.setBranch(branch.get());

        this.employeeRepository.save(employee);

        //SUCCESSFUL_IMPORT_MESSAGE = "Successfully imported %s %s.";
        // SHOULD IMPORT " static hiberspring.common.Constants.*;" IN ORDER NOT TO READ THE CLASS NAME FIRST
        String name = employee.getLastName() + " " + employee.getLastName();
        return String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, employee.getClass().getSimpleName(), name);
    }

    @Override
    public String exportProductiveEmployees() {
        //Extract all Employees, who are working in a Branch, which has at least one product.

        // We need all branches with at least one product, we use Set because we do not need repeating

        Set<String> branchNamesWithMoreThanOneProduct = this.productRepository.findAll()
                .stream().map(p -> p.getBranch().getName()).collect(Collectors.toSet());

        //    • Extract the Employee’s full name (first name + ‘ ’ + last name), the Employee’s position, and the Employee’s Card’s Number.
        //    • Order the data by full name in alphabetical order, and then by length of position in descending order.
        // ! Now we need to find all the employees working in a branch from those branches we found


        List<Employee> employees = employeeRepository
                .findAllByBranch_NameInOrderByFirstNameAscLastNameAscLengthOfPositionDesc(branchNamesWithMoreThanOneProduct);


        return employees.stream()
                .map(Employee::toString)
                .collect(Collectors.joining("\n"));

    }
}
