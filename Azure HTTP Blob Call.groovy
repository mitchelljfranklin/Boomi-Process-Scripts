
import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.util.Properties;
import java.io.ByteArrayInputStream;
import com.boomi.execution.ExecutionUtil;
import java.io.InputStreamReader;
import java.io.BufferedReader;

for( int i = 0; i < dataContext.getDataCount(); i++ ) {
    InputStream is = dataContext.getStream(i);
    Properties props = dataContext.getProperties(i);
    
    StringBuffer response = new StringBuffer();
    String host = ExecutionUtil.getDynamicProcessProperty("DPP_Blob_Storage_Host");
    //String signature =  props.getProperty("document.dynamic.userdefined.DDP_Azure_Signature");
    String signature =  ExecutionUtil.getDynamicProcessProperty("DPP_Azure_Signature");
    String parameters = "&comp=list"
    URL obj = new URL(host + signature + parameters);
	HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

	con.setRequestMethod("GET");
    int responseCode = con.getResponseCode();
    BufferedReader inReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    while ((inputLine = inReader.readLine()) != null) {
        response.append(inputLine);
    }
    inReader.close();

    dataContext.storeStream(new ByteArrayInputStream(response.toString().getBytes()), props);
}