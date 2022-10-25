
/*
    Output current date with the required timezone offset.

    How to use:
    
        Set Dynamic Process Property [dpp timezone] to State; ie:
    
            WA
            QLD
            VIC
            SA
            NT
            TAS
            ACT
            NSW
            NZ
            
    
        Call script and result will be saved into Dynamic Process Properties:
        
            dpp daylight savings - boolean
            dpp timezone time - Date/Time

Change log


*/
import java.util.Properties;
import java.io.InputStream;
import java.util.Date;
import com.boomi.execution.ExecutionUtil; 

for( int i = 0; i < dataContext.getDataCount(); i++ ) {
    InputStream is = dataContext.getStream(i);
    Properties props = dataContext.getProperties(i);
    
    String checktimeZone = ExecutionUtil.getDynamicProcessProperty("dpp timezone")
    checktimeZone = checktimeZone.toLowerCase()
    
    def inDayLight = false;
    def offset;
    def defoffset;
    if (checktimeZone == 'wa') {
        offset = 'Australia/Perth'
    } else if (checktimeZone == 'qld') {
        offset = 'Australia/Brisbane'
    } else if (checktimeZone == 'vic') {
        offset = 'Australia/Melbourne'
    } else if (checktimeZone == 'sa') {
        offset = 'Australia/Adelaide'
    } else if (checktimeZone == 'nt') {
        offset = 'Australia/Darwin'
    } else if (checktimeZone == 'tas') {
        offset = 'Australia/Hobart'
    } else if (checktimeZone == 'act') {
        offset = 'Australia/Canberra'
    } else if (checktimeZone == 'nsw') {
        offset = 'Australia/Sydney'
    } else if (checktimeZone == 'nz') {
        offset = 'Pacific/Auckland'
    }
    
    // Set Date/Time Values
    def checktz = TimeZone.getTimeZone(offset.toString())
    checktz = new Date().format("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = checktz)
    inDayLight = TimeZone.getTimeZone(offset.toString()).inDaylightTime(new Date());
    
    // Save values to Dynamic Process Properties
    ExecutionUtil.setDynamicProcessProperty("dpp daylight savings", inDayLight.toString(), false);
    ExecutionUtil.setDynamicProcessProperty("dpp timezone time", checktz.toString(), false);
    
    dataContext.storeStream(is, props);
}