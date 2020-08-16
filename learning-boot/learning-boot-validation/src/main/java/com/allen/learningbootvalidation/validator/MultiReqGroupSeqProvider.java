package com.allen.learningbootvalidation.validator;

import com.allen.learningbootvalidation.pojo.MultiReq;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class MultiReqGroupSeqProvider implements DefaultGroupSequenceProvider<MultiReq> {

    @Override
    public List<Class<?>> getValidationGroups(MultiReq o) {
        ArrayList<Class<?>> defaultGroupSequence = new ArrayList<>();
        defaultGroupSequence.add(MultiReq.class);
        if (o != null) {
            if (StringUtils.isNotBlank(o.getId())) {
                defaultGroupSequence.add(MultiReq.MultiReqById.class);
            } else {
                defaultGroupSequence.add(MultiReq.MultiReqByLike.class);
            }
        }
        return defaultGroupSequence;
    }
}