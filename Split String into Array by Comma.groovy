import java.util.Properties;
import java.io.InputStream;
 
for( int i = 0; i < dataContext.getDataCount(); i++ ) {
  InputStream is = dataContext.getStream(i);
  Properties props = dataContext.getProperties(i);
 
  def delimitedString = is.text
  def listOfStrings = delimitedString.split(",")
  listOfStrings.each {
    def newDocument = new ByteArrayInputStream(it.getBytes())
    dataContext.storeStream(newDocument, props); 
  }
}