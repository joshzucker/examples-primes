public class PrintPrimes {
  int numberOfPrimes;
  int primesPerColumn;
  int columnsPerPage;
  int listOfPrimes[];

  public PrintPrimes(int numberOfPrimes, int primesPerColumn, int columnsPerPage) {
    this.numberOfPrimes   = numberOfPrimes;
    this.primesPerColumn  = primesPerColumn;
    this.columnsPerPage  = columnsPerPage;
    this.listOfPrimes = new int[numberOfPrimes + 1];
  }


  public static void main(String[] args) {
      PrintPrimes printPrimes = new PrintPrimes(300, 50, 4);
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
      int multIndex;
      int oddMultiples[] = new int[numberOfPrimes/10 + 1];

      int currentNum = 1;
      int primeIndex = 2;
      int primeSquared = 9;

      for(int primesFoundSoFar = 2; primesFoundSoFar <= numberOfPrimes; primesFoundSoFar++) {
        do {
          currentNum = currentNum + 2;
          if (currentNum == primeSquared) {
            primeSquared = listOfPrimes[primeIndex] * listOfPrimes[primeIndex];
            oddMultiples[primeIndex] = currentNum;
            primeIndex ++;
          }
          multIndex= 2;
          numIsPrime = true;
          while (multIndex < primeIndex && numIsPrime) {
            while (oddMultiples[multIndex] < currentNum)
              oddMultiples[multIndex] = oddMultiples[multIndex] + 2*listOfPrimes[multIndex];  
            if (oddMultiples[multIndex] == currentNum)
              numIsPrime = false;
            multIndex++;
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
          for (int rowOffset = pageOffset; rowOffset< pageOffset + primesPerColumn; rowOffset++){
            for (int C = 0; C < columnsPerPage;C++)
              if (rowOffset+ C * primesPerColumn <= numberOfPrimes)
                System.out.format("%10d", listOfPrimes[rowOffset + C * primesPerColumn]);
            System.out.println("");
          }
          System.out.println("\f");
          pageNumber = pageNumber + 1;
          pageOffset = pageOffset + primesPerColumn * columnsPerPage;
        }
    }
}

					 
