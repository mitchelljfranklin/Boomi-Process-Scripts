
/*------------------------------------/
Name: Mitchell Franklin
Script Name: Get First Day of the Month
Platform: Groovy
Last Update: 20-12-2019
Last Change: Initial Script Creation
/-------------------------------------*/


import com.boomi.execution.ExecutionUtil; //Boomi Process
import static java.util.Calendar.*

/*-----------Boomi Process - DO NOT TOUCH-----------*/
for( int i = 0; i < dataContext.getDataCount(); i++ ) {
    InputStream is = dataContext.getStream(i);
    Properties props = dataContext.getProperties(i);
/*--------------------------------------------------*/    

    def today = new Date();
    def firstdateofMonth;
    def month = today[MONTH]
    def lastYear = today[YEAR] - 1
    def year = today[YEAR]
    String temp1 = month


    //If the month is a single digit then add the leading zero to the month i.e. 1 would equal 01
    if(temp1.length()== 1){
       month = '0' + month 
    }

    //Check to see if Month is January we need to set the period back the the previosue year i.e. current date is 15-01-2020 then the date would need to be 01-12-2019
    if (month == '00') {
        firstdateofMonth = lastYear + '-12-01'

    } else {
     //If not then set it to the current month, year and date of 01   
        firstdateofMonth = year + '-' + month + '-01'
    }

        println firstdateofMonth; //Debug Line

    //Set Dynamic Process Property dpp date of month with the firstdateofMonth value for use within the flow
    ExecutionUtil.setDynamicProcessProperty("dpp date of month", firstdateofMonth, false);

/*-----------Boomi Process - DO NOT TOUCH-----------*/
        dataContext.storeStream(is, props);
/*--------------------------------------------------*/        
}