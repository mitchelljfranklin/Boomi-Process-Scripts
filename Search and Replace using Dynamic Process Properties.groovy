
//Script created to provide the ability to provide two dynamic process properties to use as search and replace method.
//Set the following dpp as the required values and call this script to get the desired output.


import java.util.Properties;
import com.boomi.execution.ExecutionUtil; 
import java.io.InputStream;

def fullString = ExecutionUtil.getDynamicProcessProperty("dpp fullString"); //Full string that will be provided to enable the search to be carried out in
def findString = ExecutionUtil.getDynamicProcessProperty("dpp findString"); //The String that you want to find
def replaceString = ExecutionUtil.getDynamicProcessProperty("dpp replaceString"); //The string you want to replace the found string with



for( int i = 0; i < dataContext.getDataCount(); i++ ) {
    InputStream is = dataContext.getStream(i);
    Properties props = dataContext.getProperties(i);

     
  def newfullString = fullString.replace(findString, replaceString);

   

   ExecutionUtil.setDynamicProcessProperty("dpp fullString", newfullString, false); //update the dpp fullString with the now created value



    dataContext.storeStream(is, props);

}