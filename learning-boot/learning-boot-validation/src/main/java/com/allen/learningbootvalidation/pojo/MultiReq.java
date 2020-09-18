package com.allen.learningbootvalidation.pojo;

import com.allen.learningbootvalidation.validator.MultiReqGroupSeqProvider;
import lombok.Data;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.constraints.NotBlank;

@Data
@GroupSequenceProvider(MultiReqGroupSeqProvider.class)
public class MultiReq {

    @NotBlank(groups = MultiReqById.class)
    private String id;

    @NotBlank(groups = MultiReqByLike.class)
    private String name;

    @NotBlank(groups = MultiReqByLike.class)
    private String sex;

    public interface MultiReqById {

    }

    public interface MultiReqByLike {

    }
}
