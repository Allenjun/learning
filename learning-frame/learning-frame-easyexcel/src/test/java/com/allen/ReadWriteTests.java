package com.allen;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

@Slf4j
public class ReadWriteTests {


    @SneakyThrows
    @Test
    public void shouldAnswerWithTrue() {
        ExcelListener listener = new ExcelListener();
        EasyExcel.read(ClassLoader.getSystemClassLoader().getResourceAsStream(new String("列表.xlsx".getBytes(System.getProperty("sun.jnu.encoding")))))
                .registerReadListener(listener)
                .autoCloseStream(true)
                .mandatoryUseInputStream(true)
                .head(Person.class)
                .sheet(0)
                .doRead();
        System.out.println(listener.getPersons().size());
    }

    @Data
    public static class Person {

        @ExcelProperty(value = "0")
        private String serial;
        @ExcelProperty(value = "1")
        private String schema;
        @ExcelProperty(value = "2")
        private String tablename;
        @ExcelProperty(value = "3")
        private String type;
        @ExcelProperty(value = "4")
        private String size;
    }

    public static class ExcelListener extends AnalysisEventListener<Person> {

        private List<Person> persons = Lists.newArrayList();

        @Override
        public void invoke(Person person, AnalysisContext analysisContext) {
            persons.add(person);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext analysisContext) {
            log.info("analysis complete");
        }

        public List<Person> getPersons() {
            return persons;
        }
    }
}
