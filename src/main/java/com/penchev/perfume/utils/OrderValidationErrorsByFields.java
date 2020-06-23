package com.penchev.perfume.utils;

import com.penchev.perfume.models.binding.RegisterBindingModel;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

@Data
@Component
public class OrderValidationErrorsByFields {

    private List<String> allFieldBinding;

    private void getAllFieldsFromBindingModel() {
        List<String> privateFields = new ArrayList<>();

        Field[] allFields = RegisterBindingModel.class.getDeclaredFields();
        for (Field field : allFields) {
            if (Modifier.isPrivate(field.getModifiers())) {
                privateFields.add(field.getName());
            }
        }

        this.allFieldBinding = Collections.unmodifiableList(privateFields);
    }

    public Set<String> orderValidationError(Set<ConstraintViolation<Object>> cvErrors) {
        this.getAllFieldsFromBindingModel();

        Set<String> order = new LinkedHashSet<>();

        for (String field : this.allFieldBinding) {
            for (ConstraintViolation<Object> cv : cvErrors) {
                if(cv.getPropertyPath().toString().equals("")){
                    order.add(cv.getMessage());
                    continue;
                }

                if (cv.getPropertyPath().toString().equals(field)) {
                    order.add(cv.getMessage());
                }
            }
        }

        return Collections.unmodifiableSet(order);
    }
}
