package com.backend.airline_tickets_agency_management.model.entity.news;
import com.backend.airline_tickets_agency_management.model.entity.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsId;
    private String newsCode;
    private String newsTitle;
    private String newsImage;
    @Column(columnDefinition = "TEXT")
    private String newsContent;
    @Column(columnDefinition = "date")
    private String newsWriteDay;
    @Column(columnDefinition = "bigint default 0")
    private long newsViews;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(columnDefinition = "boolean default true")
    private boolean flag = true;

    public News() {
    }

    public News(Long newsId, String newsCode, String newsTitle, String newsImage, String newsContent, String newsWriteDay, long newsViews, Employee employee, Category category, boolean flag) {
        this.newsId = newsId;
        this.newsCode = newsCode;
        this.newsTitle = newsTitle;
        this.newsImage = newsImage;
        this.newsContent = newsContent;
        this.newsWriteDay = newsWriteDay;
        this.newsViews = newsViews;
        this.employee = employee;
        this.category = category;
        this.flag = flag;
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

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
