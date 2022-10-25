/*Elkng Created this super fly thing to encode stupid URL*/
import java.util.Properties;
import java.io.InputStream;
import java.net.URLEncoder;
import com.boomi.execution.ExecutionUtil; 

String stringToEncode = ExecutionUtil.getDynamicProcessProperty("dpp stringToEncode")
String encodedString = ''

for( int i = 0; i < dataContext.getDataCount(); i++ ) 
{
InputStream is = dataContext.getStream(i);
Properties props = dataContext.getProperties(i);
//
encodedString = URLEncoder.encode(stringToEncode, "UTF-8")
ExecutionUtil.setDynamicProcessProperty("dpp encodedString", encodedString, false);
//
dataContext.storeStream(is, props);
}
