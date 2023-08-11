package softuni.exam.service;

import javax.xml.bind.JAXBException;

public interface PictureService {
    String importPictures() throws JAXBException;

    boolean areImported();

    String readPicturesXmlFile();
}
