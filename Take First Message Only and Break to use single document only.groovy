
import java.util.Properties;
import java.io.InputStream;
for( int i = 0; i < dataContext.getDataCount(); i++ ) {
InputStream is = dataContext.getStream(i);
Properties props = dataContext.getProperties(i);
dataContext.storeStream(is, props);
break; //Only take the first record if multiple found
}
