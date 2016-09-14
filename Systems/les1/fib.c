#include <stdio.h>

int fibHelper(int nmbr, int sum, int prev) {
  if (nmbr == 0) {

    printf("helper: %d\n", sum);
    return sum;
  }

  fibHelper(nmbr - 1, sum + prev, sum);
}

int fib(int nmbr) {
  int result;
  if (nmbr == 0) {
    return 0;
  }

  result = fibHelper(nmbr - 1, 1, 0);

  printf("fib: %d\n", result);
  return result;
}

int fib2(int nmbr) {
  if (nmbr <= 1) {
    return nmbr;
  }

  return fib2(nmbr - 1) + fib2(nmbr - 2);
}

int main() {
  printf("%d\n", fib(4));
  return 0;
}
