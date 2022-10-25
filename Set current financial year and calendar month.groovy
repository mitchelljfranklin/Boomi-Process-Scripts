
import java.util.Properties;
import java.io.InputStream;
import java.util.Calendar;
import com.boomi.execution.ExecutionUtil;

for(int i = 0; i < dataContext.getDataCount(); i++) {
    InputStream is = dataContext.getStream(i);
    Properties props = dataContext.getProperties(i);
    
   	//Create year and month wise folder structure based on the current date
    String year = new Date().format('YYYY');
    String month = "";
    String mon = new Date().format('MM');
    int mm = mon.toInteger();
    
      if (mon == "12"){
        month = "12_December";
    } else if (mon == "02"){
        month = "01_January";
    } else if (mon == "03"){
        month = "02_February"; 
    } else if (mon == "04"){
        month = "03_March";
    } else if (mon == "05"){
        month = "04_April";
    } else if (mon == "06"){
        month = "05_May";
    } else if (mon == "07"){
        month = "06_June";
    } else if (mon == "08"){
        month = "07_July";
    } else if (mon == "09"){
        month = "08_August";
    } else if (mon == "10"){
        month = "09_September";
    } else if (mon == "11"){
        month = "10_October";
    } else {
        month = "11_November";
    }; 
    
    if (mm > 8){
        financialYear = year.toInteger()+1;
    } else {
        financialYear = year.toInteger();
    }
    
    spyear = 'FY' + financialYear.toString();
    
 
    ExecutionUtil.setDynamicProcessProperty("dpp calendar month", month, false);
    ExecutionUtil.setDynamicProcessProperty("dpp financial year", spyear, false);

    dataContext.storeStream(is, props);
}