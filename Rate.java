package project;

public class Rate {
  /**
    getRate(iCurr,fCurr) gets the exchange rate between the two currency
    @param iCurr an integer of the inital Currency index
    @param fCurr an integer of the final Currency index
    @return the exhange rate 
  */
  public static double getRate (int iCurr, int fCurr){
    // this gets the exchange rate between the two currency
    // we uses the exchange rate of each currency to USD to do the caculatation
    // e.g RMB to JPY:
    // (RMB/(RMB/USD)) * (JPY/USD) = JPY
    // RMB * ((JPY/USD)/(RMB/USD)) = JPY
    // rate = (JPY/USD) / (RMB/USD)

    double rate;
    double iR,fR;
    iR = rateUSDs[iCurr];
    fR = rateUSDs[fCurr];
    rate = fR/iR;
    return rate;
  }

  public static double[] rateUSDs = {
    1.31,82.89,3.24,1.27,6.61,2994.40,1.00,17.70,0.84,4.47,
    7.82,64.30,13512.00,35240.00,3.49,112.46,0.71,103.20,4.05,18.59,
    2443.50,102.93,360.00,105.31,50.56,58.73,3.75,1.35,1083.99,153.39,
    8.42,30.02,32.55,3.88,27.20,0.74,9.98,22692.00
  };

}
