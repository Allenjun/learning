package com.allen;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.val;
import org.apache.commons.compress.utils.Lists;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        val file = new File("D:/1.xlsx");
        ExcelListener excelListener = new ExcelListener();
        try {
            val excelReader =
                    new ExcelReader(
                            new BufferedInputStream(new FileInputStream(file)),
                            ExcelTypeEnum.XLSX,
                            excelListener);
            excelReader.read(new Sheet(1, 1, Person.class));
            List<Person> data = excelListener.getData();
            System.out.println(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class Person extends BaseRowModel {

        @ExcelProperty(value = "姓名", index = 1)
        private String name;

        @ExcelProperty(value = "年龄", index = 2)
        private String age;
    }

    class ExcelListener extends AnalysisEventListener<Person> {

        private List<Person> data = Lists.newArrayList();

        @Override
        public void invoke(Person person, AnalysisContext analysisContext) {
            data.add(person);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        }

        public List<Person> getData() {
            return data;
        }
    }
}
