
import java.util.Properties;
import java.io.InputStream;
import com.boomi.execution.ExecutionUtil;

for( int i = 0; i < dataContext.getDataCount(); i++ ) {
    InputStream is = dataContext.getStream(i);
    Properties props = dataContext.getProperties(i);
    
    
    def guid = UUID.randomUUID().toString()
    
    
    ExecutionUtil.setDynamicProcessProperty("dpp generated gui", guid.toString(), true);
    
    

    dataContext.storeStream(is, props);
}