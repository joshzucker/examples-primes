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
      int oddMultiples[] = new int[numberOfPrimes/10 + 1];
      int currentNum = 1;
      int primeIndex = 2;
      int primeSquared = 9;

      for(int primesFoundSoFar = 2; primesFoundSoFar <= numberOfPrimes; primesFoundSoFar++) {
        do {
          // go to the next odd number
           currentNum = currentNum + 2;		
            if (currentNum == primeSquared) { 		// If the current number is equal to the last prime squared
             primeSquared = listOfPrimes[primeIndex] * listOfPrimes[primeIndex]; //calculate the next prime squared	
             oddMultiples[primeIndex] = currentNum;	// list the current num as an odd multiple 
             primeIndex ++;
          }
          numIsPrime = true;
          
          for ( int multIndex =2; multIndex < primeIndex && numIsPrime; multIndex ++) {
         	
            while (oddMultiples[multIndex] < currentNum)
              oddMultiples[multIndex] = oddMultiples[multIndex] + 2*listOfPrimes[multIndex];  
           //  The integer would only be a multiple if after the addition it evaluates to itself.
            if (oddMultiples[multIndex] == currentNum)
           //  If it is a multiple than it is not prime
              numIsPrime = false; 
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
            for (int j = 0; j < columnsPerPage; j++)
              if (rowOffset+ j * primesPerColumn <= numberOfPrimes)
                System.out.format("%10d", listOfPrimes[rowOffset + j * primesPerColumn]);
            System.out.println("");
          }
          System.out.println("\f");
          pageNumber = pageNumber + 1;
          pageOffset = pageOffset + primesPerColumn * columnsPerPage;
        }
    }
}

					 
