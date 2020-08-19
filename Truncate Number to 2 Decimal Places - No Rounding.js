/*------------------------------------/
Name: Mitchell Franklin
Script Name: Truncate Number to 2 Decimal Places - No Rounding
Platform: JavaScript
Last Update: 22/07/2020
Last Change: Creation of script to allow the truncate of numbers to 2 decimal places without rounding.
/-------------------------------------*/

var str = valuein;
try {
    str = str.toString().split('.');
    var res = str[1].slice(0, 2);
    valueout = str[0] + '.' + res;
} catch (err) {
    valueout = valuein;

}