#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>
#include <string.h>

struct TripleInteger {
  int field1;
  int field2;
  int field3;
};

void add_fields(struct TripleInteger *tripleInteger) {
  tripleInteger->field3 = tripleInteger->field1 + tripleInteger->field2;
}

int main(int argc, char **argv) {
  struct TripleInteger trip;
  if (argc != 3) {
    printf("Please, provide 2 parameters");
    
    return 1;
  }

  trip.field1 = atoi(argv[1]);
  trip.field2 = atoi(argv[2]);

  printf("field1: %d", trip.field1);
  printf("field2: %d", trip.field2);

  add_fields(&trip);

  printf("field3: %d", trip.field3);

  return 0;
}
