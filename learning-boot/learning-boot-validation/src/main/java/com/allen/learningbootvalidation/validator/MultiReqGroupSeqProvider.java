package com.allen.learningbootvalidation.validator;

import com.allen.learningbootvalidation.pojo.MultiReq;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;
/**
 * description:
 *  GroupSequenceProvider 通过根据传入对象的参数情况，决定采用哪种校验策略。
 *      具体做法是向 defaultGroupSequence 压入不同的校验接口
 */
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
