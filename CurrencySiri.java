package project;
import java.util.Scanner;
import project.Rate;

public class CurrencySiri {
  /**
    Prompts the user to enter sentences
    Last sentence should be "bye"
    Recognize the inital value, the inital currency type and final currency type
    Prints out the amount after calculations
    Exits the program after saying "bye".
    @param args the command line arguments which are ignored
  */
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
  /**
    printInstructions() prompts the user for a sentence by printing a welcome sentence
    it does not return anything
  */
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
  @param input an array of Strings of user input
  @return the amount of the money converted into
*/
  public static double getFinalValue (String[] input) {
    double iValue = getInitialValue(input);
    int iCurr = getInitialCurr(input);
    int fCurr = getFinalCurr(input);
    double rate = Rate.getRate(iCurr,fCurr);
    double result = iValue*rate;
    return result;
  }

/**
  recognize the position of the inital currency that the user want to convert in the input
  @param input an array of Strings of user input
  @return the index of the inital currency in the user input
*/
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

  /**
    recognize the position of the final currency that the user want to convert in the input
    @param input an array of Strings of user input
    @return the index of the final currency in the user input
  */
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

/**
  recognize the position of the final currency that the user want to convert in the input
  @param input an array of Strings of user input
  @param numberValue an array of Strings of digits
  @return the index of the monetary value in the user input
*/

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

/**
  recognize the position of the final currency that the user want to convert in the input
  @param input an array of Strings of user input
  @param numberValue an array of Strings of all currency types
  @return the array of Strings that contain the two type of currency appers in the
  user input
*/
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
