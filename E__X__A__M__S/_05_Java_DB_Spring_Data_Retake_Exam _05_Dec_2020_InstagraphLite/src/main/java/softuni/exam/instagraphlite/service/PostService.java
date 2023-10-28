package softuni.exam.instagraphlite.service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface PostService {
    boolean areImported();

    String readFromFileContent() throws IOException, JAXBException;

    String importPosts() throws IOException, JAXBException;

}
