package com.allen.scheme;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class Wensinvcheck {

    private final static String SOURCEEXCEL = "D:/in.excel";
    private final static String OUTCODEFILE = "D:/out.txt";
    String code = "MoveTo 738, 61\n" +
            "LeftClick 1\n" +
            "Delay 500\n" +
            "MoveTo 110, @y\n" +
            "LeftClick 1\n" +
            "SayString \"@mat\"\n" +
            "Delay 500\n" +
            "MoveTo 375, @y\n" +
            "LeftClick 1\n" +
            "SayString \"@unit\"\n" +
            "Delay 500\n" +
            "MoveTo 1021, @y\n" +
            "LeftClick 1\n" +
            "SayString \"@price\"\n" +
            "Delay 500\n" +
            "MoveTo 279, 62\n" +
            "LeftClick 1\n" +
            "Delay 500";

    @Test
    @SneakyThrows
    public void test1() {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OUTCODEFILE)));
        List<InvEntry> invs = getInvs();
        int y = 190;
        for (InvEntry inv : invs) {
            String mat = inv.getMat();
            String unit = inv.getUnit();
            String price = inv.getPrice();
            writer.write(code.replaceAll("@y", String.valueOf(y)).replaceAll("@mat", mat).replaceAll("@unit", unit).replaceAll("@price", price));
            writer.newLine();
            y += 20;
        }
        writer.flush();
    }

    private List<InvEntry> getInvs() {
        WensinvcheckListener listener = new WensinvcheckListener();
        EasyExcel.read(ClassLoader.getSystemClassLoader().getResourceAsStream(SOURCEEXCEL))
                .registerReadListener(listener)
                .autoCloseStream(true)
                .mandatoryUseInputStream(true)
                .head(InvEntry.class)
                .sheet(0)
                .headRowNumber(1)
                .doRead();
        return listener.getInvs();
    }

    @Data
    @Builder
    public static class InvEntry {

        @ExcelProperty("")
        private String mat;
        @ExcelProperty("")
        private String unit;
        @ExcelProperty("")
        private String price;

    }

    public static class WensinvcheckListener extends AnalysisEventListener<InvEntry> {

        private List<InvEntry> invs = Lists.newArrayList();

        @Override
        public void invoke(InvEntry person, AnalysisContext analysisContext) {
            invs.add(person);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            
        }

        public List<InvEntry> getInvs() {
            return invs;
        }
    }

}
