import java.util.Scanner;

public class CurrencySiri {

  public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    System.out.println("Hi! Welcome to Currency Siri");

    while (!done){
      String userInput= scanner.nextLine();
      String[] input = userInput.split("\\s+");
      Double fValue = getFinalValue(input);
      System.out.printf("%1.2f%n%n",fValue);
      done = userResponse.equals("bye");
    }



  }

  public static Double getFinalValue (String[] input) {
    Double iValue = getInitialValue(input);
    Double rate = getRate(getInitialCurr(input),getFinalCurr(input));
    return (iValue/rate);
  }

  public static Double getInitialValue (String[] input) {
    // this recognizes the number in the user's input as the initial value
  }

  public static String getInitialCurr (String[] input){
    // this recognizes the initial currency in the user's input
  }

  public static String getFinalCurr (String[] input){
    // this recognizes the final currency in the user's input
  }

  public static Double getRate (String iCurr, String fCurr){
    // this gets the exchange rate between the two currency
    // it shall be used in the formula final value = initial value / rate
    // be careful of its order

  }


}
