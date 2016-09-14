#include <stdio.h>
int mystrlen(char *input_string) {
  int length = 0;
  while (input_string[length] != '\0') {
    length += 1;
  }

  return length;
}

int main(int argc, char **argv) {
  int length;
  if (argc!=2) {
    printf("Usage: strlen <input_string_with_no_space_inside_it>\n\n");
    return 1;
  }
  length = mystrlen(argv[1]);
  printf("The length is: %d characters.\n",length);
  return 0;
}
