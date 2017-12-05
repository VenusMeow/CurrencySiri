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
    // we uses the exchange rate of each currency to USD to do the caculatation
    // e.g RMB to JPY:
    // (RMB/(RMB/USD)) * (JPY/USD) = JPY
    // RMB * ((JPY/USD)/(RMB/USD)) = JPY
    // rate = (JPY/USD) / (RMB/USD)
    double rate;
    double iR,fR;
    double [][] rateUSDs = {
      {"AUD",1.31},{"BDT",82.89},{"BRL",3.24},{"CAD",1.27},{"RMB",6.61},
      {"COP",2994.40},{"USD",1.00},{"EGP",17.70},{"EUR",0.84},{"GHS",4.47},
      {"HKD",7.82},{"INR",64.30},{"IDR",13512.00},{"IRR",35240.00},{"ILS",3.49},
      {"JPY",112.46},{"JOD",0.71},{"KES",103.20},{"MYR",4.05},{"MXN",18.59},
      {"MNT",2443.50},{"NPR",102.93},{"NGN",360.00},{"PKR",105.31},{"PHP",50.56},
      {"RUB",58.73},{"SAR",3.75},{"SGD",1.35},{"KRW",1083.99},{"LKR",153.39},
      {"SEK",8.42},{"TWD",30.02},{"THB",32.55},{"TRY",3.88},{"UAH",27.20},
      {"GBP",0.74},{"VEF",9.98},{"VND",22692.00}
    }
    for (int i=0; i<rateUSDs.length,i++){
      if (iCurr.equals(rateUSDs[0])) {
        iR = rateUSDs[1];
      } else if (fCurr.equals(rateUSDs[0])){
        fR = rateUSDs[1];
      }
    }
    rate = fR/iR;
    return rate;
  }


}
