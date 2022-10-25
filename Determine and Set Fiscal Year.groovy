
import java.util.Properties;
import java.io.InputStream;
import java.util.Calendar;

for( int i = 0; i < dataContext.getDataCount(); i++ ) {
    InputStream is = dataContext.getStream(i);
    Properties props = dataContext.getProperties(i);
    
    
    String FiscalYear;

    //Get the current Year and Month, being June having assigned number 1 according to the finance calender
    String year = new Date().format('yyyy');
    String YearIn2Digit = new Date().format('yy');
    String mon = new Date().format('MM');
    int month = mon.toInteger();
    
    if(month < 7){
        int LastYear = year.toInteger()-1;
        FiscalYear = LastYear.toString() + '/' + YearIn2Digit;
    } else {
        int NextYear = YearIn2Digit.toInteger()+1;
        FiscalYear = year + '/' + NextYear.toString();
    }
    
 
    props.setProperty("document.dynamic.userdefined.ddp fiscal year", FiscalYear);

    dataContext.storeStream(is, props);
}