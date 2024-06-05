package com.example.school.service.util.spec;

import com.example.school.entity.Course;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Data
public class CourseSpec implements Specification<Course> {

    private final CourseFilter courseFilter;
    List<Predicate>  predicates = new ArrayList<>();

    @Override
    public Predicate toPredicate(Root<Course> courseRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if(courseFilter.getTitle() != null){
           Predicate title =  cb.like(courseRoot.get("title"), "%"+ courseFilter.getTitle() +"%");
           predicates.add(title);

        }
        if(courseFilter.getPrice() != null){
            Predicate pricePredicate = cb.equal(courseRoot.get("price"), courseFilter.getPrice());
            predicates.add(pricePredicate);
        }
        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
