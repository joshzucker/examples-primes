public class PrintPrimes {
  int numberOfPrimes;
  int RR;
  int CC;
  int WW;
  int ORDMAX;
  int listOfPrimes[];

  public PrintPrimes(int numberOfPrimes, int RR, int CC, int WW, int ORDMAX) {
    this.numberOfPrimes   = numberOfPrimes;
    this.RR  = RR;
    this.CC  = CC;
    this.WW  = WW;
    this.ORDMAX = ORDMAX;
    this.listOfPrimes = new int[numberOfPrimes + 1];
  }


  public static void main(String[] args) {
      PrintPrimes printPrimes = new PrintPrimes(300, 50, 4, 10, 30);
      printPrimes.calculatePrimes();
      printPrimes.printPrimes();
  }

  public void calculatePrimes() {
      /* Two is the only even prime. All other prime numbers are odd.
       * To simplify the code, we simply add 2 as a prime number, and
       * delegate the task of finding all odd prime numbers to a helper
       * function.
       */
      listOfPrimes[1] = 2;
      calculateOddPrimes();
  }

  private void calculateOddPrimes() {
      boolean numIsPrime;
      int N;
      int MULT[] = new int[ORDMAX + 1];

      int currentNum = 1;
      int ORD = 2;
      int SQUARE = 9;

      for(int primesFoundSoFar = 2; primesFoundSoFar <= numberOfPrimes; primesFoundSoFar++) {
        do {
          currentNum = currentNum + 2;
          if (currentNum == SQUARE) {
            ORD = ORD + 1;
            SQUARE = listOfPrimes[ORD] * listOfPrimes[ORD];
            MULT[ORD - 1] = currentNum;
          }
          N = 2;
          numIsPrime = true;
          while (N < ORD && numIsPrime) {
            while (MULT[N] < currentNum)
              MULT[N] = MULT[N] + listOfPrimes[N] + listOfPrimes[N];
            if (MULT[N] == currentNum)
              numIsPrime = false;
            N = N + 1;
          }
        } while (!numIsPrime);
        listOfPrimes[primesFoundSoFar] = currentNum;
      }
    }

    public void printPrimes() {
        int  pageNumber = 1;
        int  pageOffset = 1;
        while (pageOffset <= numberOfPrimes) {
          System.out.println("The First " + numberOfPrimes +
                               " Prime Numbers --- Page " + pageNumber);
          System.out.println("");
          for (int rowOffset = pageOffset; rowOffset< pageOffset + RR; rowOffset++){
            for (int C = 0; C < CC;C++)
              if (rowOffset+ C * RR <= numberOfPrimes)
                System.out.format("%10d", listOfPrimes[rowOffset + C * RR]);
            System.out.println("");
          }
          System.out.println("\f");
          pageNumber = pageNumber + 1;
          pageOffset = pageOffset + RR * CC;
        }
    }
}

					 
