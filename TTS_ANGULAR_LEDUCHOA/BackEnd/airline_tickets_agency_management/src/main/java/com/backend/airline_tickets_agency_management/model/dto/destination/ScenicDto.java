package com.backend.airline_tickets_agency_management.model.dto.destination;

import com.backend.airline_tickets_agency_management.model.entity.destinations_scenic.Destination;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ScenicDto {
    private Long scenicId;
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z àáãạảăắằẳẵặâấầẩẫậèéẹẻẽêềếểễệđìíĩỉịòóõọỏôốồổỗộơớờởỡợùúũụủưứừửữựỳỵỷỹýÀÁÃẠẢĂẮẰẲẴẶÂẤẦẨẪẬÈÉẸẺẼÊỀẾỂỄỆĐÌÍĨỈỊÒÓÕỌỎÔỐỒỔỖỘƠỚỜỞỠỢÙÚŨỤỦƯỨỪỬỮỰỲỴỶỸÝ]*$")
    @Size(min = 5,max = 20)
    private String scenicName;
    @NotEmpty
    @Size(min = 10,max = 200)
    private String scenicDescription;
    @NotEmpty
    private String scenicImage;
    private Destination destination;

    public ScenicDto() {
    }

    public ScenicDto(String scenicName, String scenicDescription, String scenicImage, Destination destination) {
        this.scenicName = scenicName;
        this.scenicDescription = scenicDescription;
        this.scenicImage = scenicImage;
        this.destination = destination;
    }

    public Long getScenicId() {
        return scenicId;
    }

    public void setScenicId(Long scenicId) {
        this.scenicId = scenicId;
    }

    public String getScenicName() {
        return scenicName;
    }

    public void setScenicName(String scenicName) {
        this.scenicName = scenicName;
    }

    public String getScenicDescription() {
        return scenicDescription;
    }

    public void setScenicDescription(String scenicDescription) {
        this.scenicDescription = scenicDescription;
    }

    public String getScenicImage() {
        return scenicImage;
    }

    public void setScenicImage(String scenicImage) {
        this.scenicImage = scenicImage;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}
