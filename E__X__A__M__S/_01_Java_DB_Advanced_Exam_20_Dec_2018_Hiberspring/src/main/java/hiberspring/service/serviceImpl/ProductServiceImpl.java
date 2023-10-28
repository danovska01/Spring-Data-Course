package hiberspring.service.serviceImpl;

import hiberspring.common.Constants;
import hiberspring.domain.dtos.ImportProductDTO;
import hiberspring.domain.dtos.ImportProductRootDTO;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Product;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.ProductRepository;
import hiberspring.service.ProductService;
import hiberspring.util.ValidationUtilImpl;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    // SHOULD IMPORT "static hiberspring.common.Constants.*;" IN ORDER NOT TO READ THE CLASS NAME FIRST
    private static final String PRODUCTS_FILE_PATH = Constants.PATH_TO_FILES + "products.xml";
    private final ProductRepository productRepository;

    private final BranchRepository branchRepository;

    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtilImpl validator;

    public ProductServiceImpl(ProductRepository productRepository,
                              BranchRepository branchRepository,
                              ModelMapper modelMapper,
                              XmlParser xmlParser,
                              ValidationUtilImpl validator) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validator = validator;
    }

    @Override
    public Boolean productsAreImported() {
        return productRepository.count() > 0;
    }

    @Override
    public String readProductsXmlFile() throws IOException {
        return Files.readString(Path.of(PRODUCTS_FILE_PATH));
    }

    @Override
    public String importProducts() throws JAXBException {
        ImportProductRootDTO productsRootDTOs = this.xmlParser.fromFile(PRODUCTS_FILE_PATH, ImportProductRootDTO.class);
        return productsRootDTOs.getProducts().stream().map(this::importProduct).collect(Collectors.joining("\n"));
    }

    private String importProduct(ImportProductDTO dto) {
        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return Constants.INCORRECT_DATA_MESSAGE;
        }

        Optional<Product> optProduct = this.productRepository.findByName(dto.getName());


        if (optProduct.isPresent()) {
            return Constants.INCORRECT_DATA_MESSAGE;
        }
        Product product = this.modelMapper.map(dto, Product.class);
        Optional<Branch> branch = this.branchRepository.findByName(dto.getBranch());
        product.setBranch(branch.get());
        this.productRepository.save(product);

        //SUCCESSFUL_IMPORT_MESSAGE = "Successfully imported %s %s.";
        return String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, product.getClass().getSimpleName(), product.getName());
    }
}
