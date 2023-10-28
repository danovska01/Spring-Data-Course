package softuni.exam.models.dto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class CarImportDTO {

    @Size(min = 2, max = 20)
    private String make;
    @Size(min = 2, max = 20)
    private String model;
    @Positive
    private int kilometers;
    private String registeredOn;

    public CarImportDTO() {

    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getKilometers() {
        return kilometers;
    }

    public String getRegisteredOn() {
        return registeredOn;
    }
}
