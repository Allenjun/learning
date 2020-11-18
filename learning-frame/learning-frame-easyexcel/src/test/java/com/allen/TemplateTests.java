package com.allen;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.enums.WriteDirectionEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.fill.FillWrapper;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @author chan
 * @date 2020/10/19
 * description: TODO
 */
public class TemplateTests {

    @SneakyThrows
    @Test
    public void test() {
        String templateFileName = Class.class.getResource("/template.xlsx").toURI().getPath();
        String fileName = Class.class.getResource("/").toURI().getPath() + "Form.xlsx";

        ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(templateFileName).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        excelWriter.fill(data(), writeSheet);
        excelWriter.fill(new FillWrapper("logs1", list()),
                FillConfig.builder().direction(WriteDirectionEnum.VERTICAL).forceNewRow(true).build(), writeSheet);
        excelWriter.fill(new FillWrapper("logs2", list()),
                FillConfig.builder().direction(WriteDirectionEnum.HORIZONTAL).forceNewRow(true).build(), writeSheet);


        excelWriter.finish();
    }

    private Form data() {
        return new Form("我的电脑", 18, new Date());
    }

    private List<Log> list() {
        return Lists.newArrayList(new Log("1", "11111"), new Log("2", "22222\\{name\\}"));
    }

    @Data
    @AllArgsConstructor
    public static class Form {
        private String name;
        private Integer age;
        private Date time;
    }

    @Data
    @AllArgsConstructor
    public static class Log {
        private String id;
        private String content;
    }
}
