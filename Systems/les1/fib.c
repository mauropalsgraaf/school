#include <stdio.h>
int numberOfRecursionCalls = 0;

int fibHelper(int nmbr, int sum, int prev) {
  if (nmbr == 0) {
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

  return result;
}

int fib2(int nmbr) {
  numberOfRecursionCalls++;
  if (nmbr <= 1) {
    return nmbr;
  }

  return fib2(nmbr - 1) + fib2(nmbr - 2);
}

int main() {
  printf("%d\n", fib2(4));
  printf("%d\n", numberOfRecursionCalls);
  return 0;
}
