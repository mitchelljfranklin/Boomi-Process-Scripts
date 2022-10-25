
/*------------------------------------/
Name: Mitchell Franklin
Script Name: Excel File Read
Platform: Groovy
Last Update: 20/08/2019
Last Change: Reads Excel data in to Boomi & ensures that if a value or null/blank in provided in a cell that the input schema still remains valid
/-------------------------------------*/

import java.util.Locale;
import org.apache.poi.ss.usermodel.*;


for (int i = 0; i < dataContext.getDataCount(); i++) {

    InputStream is = dataContext.getStream(i);

    Properties props = dataContext.getProperties(i);

    DataFormatter formatter = new DataFormatter(Locale.default);

    Workbook wb = WorkbookFactory.create(is);

    List sheetList = wb.sheets;

    StringBuilder sb = new StringBuilder();



    for (int j = 0; j < sheetList.size(); j++) {

        Sheet sheet = wb.getSheetAt(j);
        int maxNumCols = 0;

        for (Row row: sheet) {
            maxNumCols = Math.max(maxNumCols, row.getLastCellNum());
        }

        for (Row row: sheet) {
            for (int cn = 0; cn < maxNumCols; cn++) {

                Cell cell = row.getCell(cn);

                if (cell == null) {

                    sb.append("");

                } else {

                    switch (cell.getCellType()) {

                        case Cell.CELL_TYPE_NUMERIC:

                            if (DateUtil.isCellDateFormatted(cell)) {
                                sb.append("\"" + formatter.formatCellValue(cell) + "\"");
                            } else {

                                sb.append("\"" + cell.getNumericCellValue() + "\"");

                            }

                            break;

                        case Cell.CELL_TYPE_STRING:

                            sb.append("\"" + cell.getStringCellValue() + "\"");

                            break;

                        case Cell.CELL_TYPE_FORMULA:

                            sb.append("\"" + cell.getCellFormula() + "\"");

                            break;

                        case Cell.CELL_TYPE_BOOLEAN:

                            sb.append("\"" + cell.getBooleanCellValue() + "\"");

                            break;

                        case Cell.CELL_TYPE_BLANK:

                            sb.append("");

                            break;

                        default:

                            sb.append("");

                            break;
                    }
                }

                sb.append(",");
            }

            if (sb.length() > 0) {

                sb.setLength(sb.length() - 1);
            }
            sb.append("\r\n");

        }


        String output = sb.toString();

        sb.setLength(0);

        is = new ByteArrayInputStream(output.getBytes());

        props.setProperty("document.dynamic.userdefined.SHEET", sheet.getSheetName());

        dataContext.storeStream(is, props);
    }

}