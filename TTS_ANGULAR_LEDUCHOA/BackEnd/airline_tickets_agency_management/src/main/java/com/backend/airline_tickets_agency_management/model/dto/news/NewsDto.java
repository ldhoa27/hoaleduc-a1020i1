package com.backend.airline_tickets_agency_management.model.dto.news;
import com.backend.airline_tickets_agency_management.model.entity.employee.Employee;
import com.backend.airline_tickets_agency_management.model.entity.news.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.*;


public class NewsDto {
    private Long newsId;
    @NotBlank(message = " Not Null")
    private String newsCode;
    @NotBlank(message = " Not Null")
    @Size(max = 120, min = 66)
    private String newsTitle;
    @NotBlank(message = " Not Null")
    private String newsImage;
    @NotBlank(message = " Not Null")
    @Size(max = 120000, min = 666)
    private String newsContent;
    @NotNull(message="Status date is a required field")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}", message="Invalid status date")
    private String newsWriteDay;
    @Min(value = 0)
    private long newsViews;
    private boolean flag = true;
    private Employee employee;
    private Category category;

    public NewsDto() {
    }


    public NewsDto(Long newsId, String newsCode, String newsTitle, String newsImage, String newsContent, String newsWriteDay,
                   long newsViews, boolean flag, Employee employee, Category category) {
        this.newsId = newsId;
        this.newsCode = newsCode;
        this.newsTitle = newsTitle;
        this.newsImage = newsImage;
        this.newsContent = newsContent;
        this.newsWriteDay = newsWriteDay;
        this.newsViews = newsViews;
        this.flag = flag;
        this.employee = employee;
        this.category = category;
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public String getNewsCode() {
        return newsCode;
    }

    public void setNewsCode(String newsCode) {
        this.newsCode = newsCode;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsWriteDay() {
        return newsWriteDay;
    }

    public void setNewsWriteDay(String newsWriteDay) {
        this.newsWriteDay = newsWriteDay;
    }

    public long getNewsViews() {
        return newsViews;
    }

    public void setNewsViews(long newsViews) {
        this.newsViews = newsViews;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
