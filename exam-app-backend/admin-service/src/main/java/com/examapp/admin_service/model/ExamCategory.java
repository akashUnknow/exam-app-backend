package com.examapp.admin_service.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "exam_categories")
public class ExamCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String shortName;
    private String icon;
    private String image;
    private Integer testCount;
    private String color;
    
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Question> questions;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getShortName() { return shortName; }
    public void setShortName(String shortName) { this.shortName = shortName; }
    
    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
    
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    
    public Integer getTestCount() { return testCount; }
    public void setTestCount(Integer testCount) { this.testCount = testCount; }
    
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    
    public List<Question> getQuestions() { return questions; }
    public void setQuestions(List<Question> questions) { this.questions = questions; }
}