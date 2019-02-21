package com.osbg;

import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String REPORT_SCRAP = "C:\\Users\\OSBG-HP\\Desktop\\REPORT_SCRAP.xlsx";
    private static final String REPORT_REPAIRED = "C:\\Users\\OSBG-HP\\Desktop\\REPORT_REPAIRED.xlsx";
    private static final String REPORT_PARTS = "C:\\Users\\OSBG-HP\\Desktop\\REPORT_PARTS.xlsx";

    public static void main(String[] args) throws FileNotFoundException {
        BeanListProcessor<Asset> rowProcessor = new BeanListProcessor<Asset>(Asset.class);

        CsvParserSettings parserSettings = new CsvParserSettings();
        parserSettings.setRowProcessor(rowProcessor);

        parserSettings.setHeaderExtractionEnabled(true);
        parserSettings.getFormat().setLineSeparator("\n");
        parserSettings.getFormat().setDelimiter(",");

        CsvParser parser = new CsvParser(parserSettings);

//And parse!
//this submits all rows parsed from the input to the BeanListProcessor
        parser.parse(new FileReader(new File("C:\\Users\\OSBG-HP\\Desktop\\depotall.csv")));

        List<Asset> assetList = rowProcessor.getBeans();

        List<Asset> scrapAssetsList = new ArrayList<>();
        List<Asset> repairedAssetsList = new ArrayList<>();
        List<Asset> partsAssetsList = new ArrayList<>();


//        Asset asset = assetList.get(0);


        for (Asset asset : assetList) {
            if(asset.getLocation().equals("Scrap")) {
                scrapAssetsList.add(asset);
            }
            if(asset.getLocation().matches("[0-9]{2}-[0-9]{2}-[0-9]{2}-[0-9]{2}")) {
                partsAssetsList.add(asset);
            }
            if(asset.getLocation().matches("[a-zA-Z0-9]{12}")) {
                repairedAssetsList.add(asset);
            }
        }
        System.out.println("Total scrap count: " + scrapAssetsList.size());
        System.out.println("Total parts count: " + partsAssetsList.size());
        System.out.println("Total repaired count: " + repairedAssetsList.size());

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet1");
        XSSFRow header = sheet.createRow(0);
        sheet.setDefaultColumnWidth(30);
        header.createCell(0).setCellValue("Serial number");
        header.createCell(1).setCellValue("Make");
        header.createCell(2).setCellValue("Model");
        header.createCell(3).setCellValue("Status");

        int rowCount = 1;

        for(Asset asset : repairedAssetsList) {
            XSSFRow row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(asset.getSerial());
            row.createCell(1).setCellValue(asset.getMake());
            row.createCell(2).setCellValue(asset.getModel());
            row.createCell(3).setCellValue("Repaired");
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(REPORT_REPAIRED);
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }
}
