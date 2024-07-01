package com.dhinesh.cache.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookRequest {

    @JsonProperty("title")
    @NotNull(message = "Title Cannot be Null")
    private String title;

    @JsonProperty("author")
    @NotBlank(message = "Author Name cannot be Blank")
    private String author;

    @JsonProperty("price")
    private long price;
}
