package com.Journal011.journal11.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;

@Document(collection = "journal_entries") // jkl: Username ...ll , m  , k
@Data
@NoArgsConstructor
public class JournalEntry {
    @Id
    private ObjectId id;

    @NotBlank(message = "Title is required")
    private String title;
    private String content;
    private LocalDateTime date;


}


