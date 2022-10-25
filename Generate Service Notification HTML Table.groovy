
/*
    
*/

import java.util.Properties;
import java.io.InputStream;
import com.boomi.execution.ExecutionUtil;
import groovy.json.JsonSlurper;

for( int i = 0; i < dataContext.getDataCount(); i++ ) {
    InputStream is = dataContext.getStream(i);
    Properties props = dataContext.getProperties(i);
    
    def json = new JsonSlurper().parse(dataContext.getStream(i)); 
    def StringBuilder html = new StringBuilder()

    json.each {
        if (it.value != "" && it.value != null) {
            if (it.key == "message") {
                it.value.each {
                    html.append("<tr><td>").append(it.code).append("</td><td>").append(it.error).append("</td><td>").append(it.description).append("</td></tr>")
                }
            }
        }
    }
    
    ExecutionUtil.setDynamicProcessProperty("dpp service notification error table", html.toString(), false);

    dataContext.storeStream(is, props);
}