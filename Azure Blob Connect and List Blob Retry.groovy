
/*------------------------------------------------/
Script Name:  Azure Blob Connect and List Blob Retry Process Script
Creation Date: 09-03-2020
Author: Mitchell Franklin
Description: This script handles Exception handling done from Custom Scripting and allows for Retries to occur prior to just ending the process.
Last Update Date: 09-03-2020
Last Update By: Mitchell Franklin
Last Update Description: Initial Script Creation
/-------------------------------------------------*/

import java.util.Properties;
import java.io.InputStream;
import com.boomi.execution.ExecutionUtil;

/*--------Boomi Do not touch--------*/
for( int i = 0; i < dataContext.getDataCount(); i++ ) {
    InputStream is = dataContext.getStream(i);
    Properties props = dataContext.getProperties(i);
    /*----------------------------------*/

//Set the Maximum number of retrys this process is required to handle    
int maxRetry = 3
//0 Seconds will always be first; add in the wait times for the remainer; the number should be based on MaxRetry - in this instance 3 so we want to wait 60 for 1, 180 for 2 & 300 seconds for the last retry being 3
def retryWait = [0,60,180,300]

//Retrieve the Dynamic Process Property use to store the retry count; this assumes that it has been set once in the flow with a default value
String retrycountTemp = ExecutionUtil.getDynamicProcessProperty("dpp Azure Connection Retry Count")
int retryCount = Integer.parseInt(retrycountTemp)  //Convert the Process Property into an integer to allow for Math

//Set the retry count Number
retryCount = retryCount + 1

//Store the now current retry count into the Dynamic process property for use
ExecutionUtil.setDynamicProcessProperty("dpp Azure Connection Retry Count", Integer.toString(retryCount), true);

//If the retry count is equal to or less the the max rety then we want the process to advise that a retry is required; in this intance we are also setting the thread to sleep based on the retrWait periods set earlier
if(retryCount <= maxRetry){
     ExecutionUtil.setDynamicProcessProperty("dpp Azure Connection Retry", 'true', true); //Retry still required
        int waitFor = retryWait[retryCount];
        Thread.sleep(waitFor * 1000);

} else {
     ExecutionUtil.setDynamicProcessProperty("dpp Azure Connection Retry", 'false', true); //Retry no longer required
}


/*--------Boomi Do not touch--------*/   
    dataContext.storeStream(is, props);
}
/*----------------------------------*/