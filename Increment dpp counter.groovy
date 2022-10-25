
/*------------------------------------/

Description: A generic function that gets a Dynamic Process Property called 'dpp counter name' to identify a dpp counter that has been previously defined in a process. 
             It will retrieve the count value from the defined dpp counter, increment the count using 'dpp counter increment' and then update the dpp counter for future use within the process. 
             This can be used for such things as recording total number of records etc.
             
             **NOTE: Ensure that the defined dpp counter has been initialised at the beginning of the process.
             
             Refer to the Wiki for details of how to use this function
             https://boltonclarke.visualstudio.com/Development%20Services/_wiki/wikis/Support%20project.wiki/552/CS-Increment-dpp-Counter
/-------------------------------------*/


import java.util.Properties;
import java.io.InputStream;
import com.boomi.execution.ExecutionUtil;

for (int i = 0; i < dataContext.getDataCount(); i++ )
{
    dppCounterName = ExecutionUtil.getDynamicProcessProperty('dpp counter name')
    InputStream is = dataContext.getStream(i);
    Properties props = dataContext.getProperties(i);
    
    // Retrieve current Process Property value
    propValue = ExecutionUtil.getDynamicProcessProperty(dppCounterName);

    // Convert string value to int to do math
    int intValue = Integer.parseInt(propValue);
    boolean_variable = true;

    // Increment value by 1
    intValue = intValue + Integer.parseInt(ExecutionUtil.getDynamicProcessProperty('dpp counter increment'));

    // Convert int value back to String
    propValue = Integer.toString(intValue);

    // Set the process property
    ExecutionUtil.setDynamicProcessProperty(dppCounterName, propValue, boolean_variable);
    dataContext.storeStream(is, props);
}