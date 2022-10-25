

import java.util.Properties;
import java.io.InputStream;





// Leave the rest of the script as-is to pass the Documents to the next step.

for ( int i = 0; i < dataContext.getDataCount(); i++ ) {

InputStream is = dataContext.getStream(i);

Properties props = dataContext.getProperties(i);


// Specify the length of time to wait in seconds.

int waitFor = 5;
Thread.sleep(waitFor * 1000);


dataContext.storeStream(is, props);

}