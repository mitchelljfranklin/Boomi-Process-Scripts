
/*
    Upload Azure Blob Contents

    This function accepts in an azure blob connection details and uploads the
    contents to the URL.

Change log
==============================================================================
Date         Version     Modified By     Description
2022-07-11   1.0         Alan Miller     New function
------------------------------------------------------------------------------
*/

import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.util.Properties;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import com.boomi.execution.ExecutionUtil;

for( int i = 0; i < dataContext.getDataCount(); i++ ) {
    InputStream is = dataContext.getStream(i);
    Properties props = dataContext.getProperties(i);
    
    String url = ExecutionUtil.getDynamicProcessProperty("dpp azure blob url");
    URL obj = new URL(url);
	HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

	//add reuqest header
	con.setRequestMethod("PUT");
	con.setRequestProperty("x-ms-blob-type", "BlockBlob");
	
	// Send post request
	con.setDoOutput(true);
	
	OutputStream wr = con.getOutputStream();
	ByteArrayOutputStream baos = new ByteArrayOutputStream();				
	byte[] buffer = new byte[1024];
	int read = 0;
	while ((read = is.read(buffer, 0, buffer.length)) != -1) {
		baos.write(buffer, 0, read);
	}		
	baos.flush();
	wr.write(baos.toByteArray());
	baos.close();
	wr.flush();
	wr.close();

	int responseCode = con.getResponseCode();
	ExecutionUtil.setDynamicProcessProperty("dpp response code", responseCode.toString(), false);
	
    String output = "" + responseCode;
    is = new ByteArrayInputStream(output.getBytes("UTF-8"));
    dataContext.storeStream(is, props);
}