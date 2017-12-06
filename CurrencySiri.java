package project;
import java.util.Scanner;
import project.Rate;

public class CurrencySiri {

  public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    printInstructions();
    boolean done =false;

    while (!done){
      String userInput= scanner.nextLine();
      String[] input = userInput.split("\\s+");
      double fValue = getFinalValue(input);
      System.out.printf("%1.2f%n%n",fValue);
      done = userInput.equals("bye");
    }
    System.out.println("Bye! Thank you for using Currency Siri");
  }

  public static void printInstructions() {
    System.out.println("Hi! Welcome to Currency Siri");
    System.out.println("Please use the three capital letter code for each currency, e.g. USD");
    System.out.println("Enter bye to stop conversing.");
  }

/**
  recognizes the amount the user want to convert from the user's input
  @param input an array of Strings that will be used to find the index
  @return the amount of the money the user wish to convert
*/

  public static double getInitialValue (String[] input) {
    int index = findNumber(input,numberValue);
    double value = Double.parseDouble(input[index]);
    return value;
  }


/**
  calculate the final amount of money worth
  @param input an array of Strings
*/
  public static double getFinalValue (String[] input) {
    double iValue = getInitialValue(input);
    int iCurr = getInitialCurr(input);
    int fCurr = getFinalCurr(input);
    double rate = Rate.getRate(iCurr,fCurr);
    double result = iValue*rate;
    return result;
  }


  public static int getInitialCurr (String[] input){
    // this recognizes the initial currency in the user's input
    int index = -1;
    int numindex = findNumber(input,numberValue);
    int[] indexs = findName(input,currs);
    for (int i=0;i<indexs.length;i+=2){
      if (indexs[i]-1 == numindex){
        index = indexs[i+1];
      }
    }
    return index;
  }

  public static int getFinalCurr (String[] input){
    // this recognizes the final currency in the user's input
    int index = -1;
    int numindex = findNumber(input,numberValue);
    int[] indexs = findName(input,currs);
    for (int i=0;i<indexs.length;i+=2){
      if (indexs[i]-1 != numindex){
        index = indexs[i+1];
      }
    }
    return index;
  }



  public static int findNumber(String[] input,String[] numberValue){
    int result=-1;
    for (int i=0;i<input.length;i++){
      for (int j=0;j<numberValue.length;j++){
        if (input[i].contains(numberValue[j])){
          result = i;
        }
      }
    }
    return result;
  }

  public static int[] findName(String[] input,String[] currs){
    int[] result = new int[4];
    int count =0;
    for (int i=0;i<input.length;i++){
      for (int j=0;j<currs.length;j++){
        if (input[i].equals(currs[j]) && count ==0){
          result[0]=i;
          result[1]=j;
          count++;
        } else if (input[i].equals(currs[j]) && count ==1){
          result[2]=i;
          result[3]=j;
        }
      }
    }
    return result;
  }

  public static String[] numberValue = {"1","2","3","4",
    "5","6","7","8","9","0"};
    // this recognizes the number in the user's input as the initial value


  public static String[] currs = {
    "AUD","BDT","BRL","CAD","RMB","COP","USD","EGP","EUR","GHS",
    "HKD","INR","IDR","IRR","ILS","JPY","JOD","KES","MYR","MXN",
    "MNT","NPR","NGN","PKR","PHP","RUB","SAR","SGD","KRW","LKR",
    "SEK","TWD","THB","TRY","UAH","GBP","VEF","VND"
    };

  }
