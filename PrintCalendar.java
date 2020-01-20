/*
*
*
*/

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;

public class PrintCalendar {
  /** Main method */
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    

    // Prompt the user to enter year
    
    int year = getUserYear();

    // Prompt the user to enter month
    
    int month = getUserMonth();
    
    Calendar gregCal = new GregorianCalendar(year, month, 1);
    
    // Print calendar for the month of the year
    printMonth(year, month);
  }

  /** Print the calendar for a month in a year */
  public static void printMonth(int year, int month) {
    // Print the headings of the calendar
    printMonthTitle(year, month);

    // Print the body of the calendar
    printMonthBody(year, month);
  }

  /** Print the month title, e.g., May, 1999 */
  public static void printMonthTitle(int year, int month) {
    System.out.println("         " + getMonthName(year, month)
      + " " + year);
    System.out.println("-----------------------------");
    System.out.println(" Sun Mon Tue Wed Thu Fri Sat");
  }

  /** Get the English name for the month */
  public static String getMonthName(int year, int month) {
    Calendar gregCal = new GregorianCalendar(year, month - 1, 1);
    String monthName = gregCal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
    

    return monthName;
  }

  /** Print month body */
  public static void printMonthBody(int year, int month) {
    
    
    
    // Get start day of the week for the first date in the month
    int startDay = getStartDay(year, month);

    // Get number of days in the month
    int numberOfDaysInMonth = getNumberOfDaysInMonth(year, month);

    // Pad space before the first day of the month
    int i = 0;
    for (i = 0; i < startDay; i++)
      System.out.print("    ");

    for (i = 1; i <= numberOfDaysInMonth; i++) {
      System.out.printf("%4d", i);

      if ((i + startDay) % 7 == 0)
        System.out.println();
    }

    System.out.println();
  }

  /** Get the start day of month/1/year */
  public static int getStartDay(int year, int month) {
    final int START_DAY_FOR_JAN_1_1800 = 3;
    // Get total number of days from 1/1/1800 to month/1/year
    int totalNumberOfDays = getTotalNumberOfDays(year, month);

    // Return the start day for month/1/year
    return (totalNumberOfDays + START_DAY_FOR_JAN_1_1800) % 7;
  }

  /** Get the total number of days since January 1, 1800 */
  public static int getTotalNumberOfDays(int year, int month) {
    int total = 0;

    // Get the total days from 1800 to 1/1/year
    for (int i = 1800; i < year; i++)
      if (isLeapYear(i))
        total = total + 366;
      else
        total = total + 365;

    // Add days from Jan to the month prior to the calendar month
    for (int i = 1; i < month; i++)
      total = total + getNumberOfDaysInMonth(year, i);

    return total;
  }

  /** Get the number of days in a month */
  public static int getNumberOfDaysInMonth(int year, int month) {
    Calendar gregCal = new GregorianCalendar(year, month, 1);
    int numberOfDays = gregCal.getMaximum(Calendar.DAY_OF_MONTH);
    if (month == 1 || month == 3 || month == 5 || month == 7 ||
      month == 8 || month == 10 || month == 12)
      return numberOfDays;

    if (month == 4 || month == 6 || month == 9 || month == 11)
      return numberOfDays - 1;

    if (month == 2) return isLeapYear(year) ? numberOfDays - 2 : numberOfDays - 3;

    return 0; // If month is incorrect
  }

  /** Determine if it is a leap year */
  public static boolean isLeapYear(int year) {
    return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
  }
  public static int getUserYear(){
      int userYear = 0;
      Scanner readInp;
      
      while(true){
          try{
              readInp = new Scanner(System.in);
              System.out.print("Enter the month you would like to view: ");
              userYear = readInp.nextInt();
              return userYear;
          }
          catch(Exception e){
              System.out.println("this is not a number");

          }
          
      }
  }
  
  public static int getUserMonth(){
      int userMonth = 0;
      Scanner readInp;
      
      while(true){
          try{
              readInp = new Scanner(System.in);
              System.out.print("Enter the year you would like to view: ");
              userMonth = readInp.nextInt();
              return userMonth;
          }
          catch(Exception e){
              System.out.println("this is not a number");

          }
          
      }
  }
}