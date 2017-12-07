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
    //printing out instructions...
    boolean done =false;

    while (!done){
      System.out.printf("> ");
      String userInput= scanner.nextLine();
      if (userInput.equals("who is your maker")||userInput.equals("who made you")){
        //if user enters "who is your maker" it will print the answer
        System.out.printf("Venus, Gia, Jessie and Iris!%n");
      } else if (!userInput.equals("bye")){
        String[] input = userInput.split(" ");
        //store the user's input line into an array of each word
        double fValue = getFinalValue(input);
        //this calls the getFinalValue method to find the final value after being exchanged
        System.out.printf("%1.2f%n%n",fValue);
      }
      done = userInput.equals("bye");
    }
    System.out.printf("Bye! Thank you for using Currency Siri!%n");
  }

/**
  printInstructions() prompts the user for a sentence by printing a welcome sentence
  it does not return anything
*/
  public static void printInstructions() {
    System.out.println("Hi! Welcome to Currency Siri");
    System.out.println("Please use the three capital letter code for each currency, e.g. USD");
    System.out.println("And make sure that there's a space before the currency code, e.g. 100 USD");
    System.out.println("Enter bye to stop conversing.");
  }
  // print the instructions lines


  /**
    calculate the final amount of money worth
    @param input an array of Strings of user input
    @return the amount of the money converted into
  */
    public static double getFinalValue (String[] input) {
      double iValue = getInitialValue(input);
      // calls getInitialValue method to find user's input numerical value
      int iCurr = getInitialCurr(input);
      // calls getInitialValue method to find the which currency the user wants to exchange from
      int fCurr = getFinalCurr(input);
      // calls up the getFinalCurr method to find which currency the user wants to exchange to
      double rate = Rate.getRate(iCurr,fCurr);
      // calls up getRate method from Rate class to get the exchange rate
      double result = iValue*rate;
      // calculate the final result
      return result;
    }


/**
  recognizes the amount the user want to convert from the user's input
  @param input an array of Strings that will be used to find the index
  @return the amount of the money the user wish to convert
*/
  public static double getInitialValue (String[] input) {
    double value = -1.0;
    int index = findNumber(input,numberValue);
    // calls findNumber method to find the index position of numerical value in "input" array
    if (index>=0 && index<input.length) {
      value = Double.parseDouble(input[index]);
      // use the index found to get the user's input numerical value
    }
    return value;
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
    // This calls up findNumber method to get user's input numerical value
    int[] indexs = findName(input,currs);
    // this calls up findName method to get the index positions of "currency code" in input array
    // and there will two such positions, one for the initial currency, one for the final currency
    for (int i=0;i<indexs.length;i+=2){
      if (indexs[i]-1 == numindex){
        // initial currency will always appear after the numerial value in the input,
        // so we can to distinguish from the two positions by this
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
    // This calls up findNumber method to get user'sinout numerical value
    int[] indexs = findName(input,currs);
    // this calls up findName method to get the index positions of "currency code" in input array
    // and there will two such positions, one for the initial currency, one for the final currency
    for (int i=0;i<indexs.length;i+=2){
      if (indexs[i]-1 != numindex){
        // final currency will not appear after the numerial value in the input,
        // so we can to distinguish from the two positions by this
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
    // we look for the number in the user's input by finding the string
    // that contains one of the digits in the numValue array
    int result=-1;
    for (int i=0;i<input.length;i++){
      for (int j=0;j<numberValue.length;j++){
        if (input[i].contains(numberValue[j])){
          // numberValue is an array with numbers from 0 - 9
          // so if one element in input contains a number it will be found by this
          result = i;
        }
      }
    }
    return result;
  }

/**
  recognize the position of the final currency that the user want to convert in the input
  @param input an array of Strings of user input
  @param currs an array of Strings of all currency types
  @return the array of Strings that contain the two type of currency appers in the
  user input
*/
  public static int[] findName(String[] input,String[] currs){
    // we look for the currency code in the user's input by comparing each string
    // in the input array to all possible currency codes that we stored in another array
    int[] result = new int[4];
    // since there will be two of such strings in the userInput
    // and we want to know both their positions in the input array and
    // the corresponded currency code's position for referrence of finding the exchange rate
    // we use a 4-sized array to store those indexes
    int count =0;
    for (int i=0;i<input.length;i++){
      for (int j=0;j<currs.length;j++){
        if (input[i].equals(currs[j]) && count ==0){
          result[0]=i;
          //this is the position of the first currency code found in the input array
          result[1]=j;
          //this is the position for its corresponded currency code
          count++;
        } else if (input[i].equals(currs[j]) && count ==1){
          result[2]=i;
          //this is the position of the second currency code found in the input arra
          result[3]=j;
          //this is the position for its corresponded currency code
        }
      }
    }
    return result;
  }

  public static String[] numberValue = {"1","2","3","4",
    "5","6","7","8","9","0"};


  public static String[] currs = {
    "AUD","BDT","BRL","CAD","RMB","COP","USD","EGP","EUR","GHS",
    "HKD","INR","IDR","IRR","ILS","JPY","JOD","KES","MYR","MXN",
    "MNT","NPR","NGN","PKR","PHP","RUB","SAR","SGD","KRW","LKR",
    "SEK","TWD","THB","TRY","UAH","GBP","VEF","VND"
  }; // list of currency codes from all countries of Brandeis International Students

}
