package com.java.diogo.challenge.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class ApiResponse<T> {
    private T transacao;

}