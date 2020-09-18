package com.allen.learningbootvalidation.pojo;

import com.allen.learningbootvalidation.validator.PhoneNumber;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class InfoReq {

    @PhoneNumber
    private String phone;

    @Min(
            value = 2,
            groups = {Idyes.class})
    private Integer id;

    @Valid
    private Detail detail;

    public interface Idno {

    }

    public interface Idyes {

    }

    @Data
    class Detail {

        @NotNull
        private String name;
    }
}
