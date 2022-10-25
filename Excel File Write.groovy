
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

for (int i = 0; i < dataContext.getDataCount(); i++) {
InputStream is = dataContext.getStream(i);
Properties props = dataContext.getProperties(i);
Workbook wb = new XSSFWorkbook();
Sheet sheet = wb.createSheet("Sheet1");
BufferedReader reader = new BufferedReader(new InputStreamReader(is));
int rownum = 0;

while ((line = reader.readLine()) != null) {
  Row row = sheet.createRow(rownum);
  rownum++;
  int cellnum = 0;
  String[] fields = line.split(",");
  for (int j = 0; j < fields.length; j++) {
   row.createCell(cellnum).setCellValue(fields[j]);
   cellnum++;
  }

}

ByteArrayOutputStream baos = new ByteArrayOutputStream();
wb.write(baos);
is = new ByteArrayInputStream(baos.toByteArray());
dataContext.storeStream(is, props);
}