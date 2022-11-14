package pe.com.markat.backend.exporters;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import pe.com.markat.backend.entities.Stock;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;



public class StockExporterExcel {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Stock> stocks;

    public StockExporterExcel(List<Stock> stocks) {
        this.stocks = stocks;
        workbook = new XSSFWorkbook();
    }

    public void createCell(Row row, int column, Object value, CellStyle style) {
        sheet.autoSizeColumn(column);
        Cell cell = row.createCell(column);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if(value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else {
            cell.setCellValue((String) value);
        }

        cell.setCellStyle(style);
    }

    public void writeHeaderLine() {

        Row row = sheet.createRow(0);
        CellStyle style =  workbook.createCellStyle();

        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(14);
        font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);

        createCell(row, 0, "producto", style);
        createCell(row, 1, "marca", style);
        createCell(row, 2, "descripcion", style);
        createCell(row, 3, "stock", style);
        createCell(row, 4, "precio", style);
        createCell(row, 5, "imagen", style);
    }

    public void writeDataLines() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(false);
        font.setFontHeight(12);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);

        for (Stock stock : stocks) {
            Row row = sheet.createRow(rowCount);
            int colCount = 0;
            createCell(row, colCount, stock.getProduct().getProductName(),style);
            createCell(row, colCount+1, stock.getProduct().getBrand(),style);
            createCell(row, colCount+2, stock.getProduct().getProductDescription(),style);
            createCell(row, colCount+3, stock.getNoUnits(),style);
            createCell(row, colCount+4, stock.getPricePerUnit(),style);
            createCell(row, colCount+5, stock.getProduct().getImg(),style);

            rowCount++;
        }

    }


    public void export(HttpServletResponse response, Long id) throws IOException {

        sheet = workbook.createSheet("Inventario");

        writeHeaderLine();
        writeDataLines();

        ServletOutputStream servletOutputStream = response.getOutputStream();
        workbook.write(servletOutputStream);
        workbook.close();
        servletOutputStream.close();

    }
}
