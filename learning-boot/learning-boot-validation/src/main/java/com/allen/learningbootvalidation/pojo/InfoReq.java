package com.allen.learningbootvalidation.pojo;

import com.allen.learningbootvalidation.validator.PhoneNumber;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class InfoReq {

    @PhoneNumber
    private String phone;

    @NotBlank
    private String id;

    @Valid
    @NotNull
    private Detail detail;


    @Data
    class Detail {

        @NotBlank
        private String name;
    }
}
