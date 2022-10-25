/*Elkng Created this super fly thing to encode stupid URL*/
import java.util.Properties;
import java.io.InputStream;
import java.net.URLEncoder;
import com.boomi.execution.ExecutionUtil; 

String encodedString = ''

for( int i = 0; i < dataContext.getDataCount(); i++ ) 
{
InputStream is = dataContext.getStream(i);
Properties props = dataContext.getProperties(i);
//
//String stringToEncode = props.getProperty("document.dynamic.userdefined.ddp stringToEncode")
encodedString = URLEncoder.encode(stringToEncode, "UTF-8")
// props.setProperty("document.dynamic.userdefined.ddp encodedString", encodedString);
//
dataContext.storeStream(is, props);
}