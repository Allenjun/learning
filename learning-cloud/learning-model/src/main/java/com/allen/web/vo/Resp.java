package com.allen.web.vo;

import com.allen.web.CodeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resp<T> {

    private CodeStatus codeStatus;
    private T body;
}
