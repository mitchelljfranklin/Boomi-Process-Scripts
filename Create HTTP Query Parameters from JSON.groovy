
/*
    Create HTTP Query Parameters from JSON Input

    This function takes a basic JSON file and converts it to HTTP query parameters
    to be added to the end of a HTTP URL request. It will ignore any key/value
    pairs where the value is blank or null.

    For example:

    {
        "query1": "sample",
        "query2": "",
        "query2": "data"
    }

    will convert to:

    ?query1=sample&query3=data



------------------------------------------------------------------------------

*/


import java.util.Properties;
import java.io.InputStream;
import com.boomi.execution.ExecutionUtil;
import groovy.json.JsonSlurper;
import java.net.URLEncoder;

for( int i = 0; i < dataContext.getDataCount(); i++ ) {
    InputStream is = dataContext.getStream(i);
    Properties props = dataContext.getProperties(i);
    
    def json = new JsonSlurper().parse(dataContext.getStream(i)); 
    StringBuilder queryStr = new StringBuilder()
    
    json.each {
        if (it.value?.trim()) {
            String k = URLEncoder.encode(it.key, "UTF-8")
            String v = URLEncoder.encode(it.value, "UTF-8")
            queryStr.append(k).append("=").append(v).append("&");
        }
    }
    
    if(queryStr.length() > 0) {
        queryStr.setLength(queryStr.length() - 1);
        queryStr.insert(0, "?");
    }

    ExecutionUtil.setDynamicProcessProperty("dpp http api endpoint parameters", queryStr.toString(), false);
    
    dataContext.storeStream(is, props);
    
}