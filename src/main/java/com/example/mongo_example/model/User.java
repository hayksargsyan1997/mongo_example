package com.example.mongo_example.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String name;
    private String surname;

}
