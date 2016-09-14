#include <stdio.h>

void strmycpy(char *dest, char *src, int length) {
  int i;
  for (i = 0; i < length; i++) {

    //If the character is the null byte
    if (src[i] == '\0') {
      //Loop as long as i is smaller than the length and assign null byte + raise the value of i by 1
      while (i < length) {
        dest[i] = '\0';
        i++;
      }
      //Make sure to return to not get the last src possibly get out of bound
      return;
    }
    dest[i] = src[i];
  }

  dest[length] = '\0';
}

int main() {
  char src[] = "sourceeee\0";
  char dest[7];

  strmycpy(dest, src, 6);
  printf("arr: %s\n", dest);
  return 0;
}
