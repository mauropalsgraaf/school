#include <stdio.h>
#include <string.h>

int mystrcmp(char *item1, char *item2) {
  int i;

  if (strlen(item1) != strlen(item2)) {
    return 0;
  }

  for (i = 0; i <= strlen(item1); i++) {
    if (item1[i] != item2[i]) {
      return 0;
    }
  }

  return 1;
}

int main(int argc, char **argv) {
  int isEqual;

  if (argc != 3) {
    printf("Please, make sure you provide 2 string you want to compare for equality\n");
    return 1;
  }

  isEqual = mystrcmp(argv[1], argv[2]);

  if (isEqual == 0) {
    printf("The two strings are different.\n");
  }
  else {
    printf("The two strings are identical.\n");
  }

  return 0;
}
