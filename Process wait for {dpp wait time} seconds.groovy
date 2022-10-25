
import java.util.Properties;
import java.io.InputStream;
import com.boomi.execution.ExecutionUtil;


// Leave the rest of the script as-is to pass the Documents to the next step.
for ( int i = 0; i < dataContext.getDataCount(); i++ ) {
    InputStream is = dataContext.getStream(i);
    Properties props = dataContext.getProperties(i);

    // Get specified wait time from dynamic process property
    dppValue = ExecutionUtil.getDynamicProcessProperty("dpp wait time");

    try {
        waitFor = dppValue.toInteger();
    } catch (java.lang.NumberFormatException nfEx) {
        waitFor = 5
    }

    Thread.sleep(waitFor * 2000);

    dataContext.storeStream(is, props);
}
