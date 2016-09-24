#include <stdlib.h>
#include <printf.h>

struct List {
    struct List* next;
    int value;
};

void add_front(struct List* list, int value) {
    struct List* pointer = (struct List *) malloc(sizeof(struct List));
    pointer->next = list;
    pointer->value = value;

    list = pointer;
}

void add_back(struct List* list, int value) {
    struct List* helper = list;
    struct List* pointer = (struct List *) malloc(sizeof(struct List));
    pointer->next = NULL;
    pointer->value = value;

    while (helper->next != NULL) {
        helper = helper->next;
    }

    helper->next = pointer;
}

void display_list(struct List* list) {
    struct List* helper = list;

    while (helper != NULL) {
        printf("value: %i\n", helper->value);

        helper = helper->next;
    }
}


int main() {
    struct List list1;
    list1.next = NULL;
    list1.value = 2;

    struct List list2;
    list2.next = &list1;
    list2.value = 1;

    display_list(&list2);

    add_back(&list2, 3);

    display_list(&list2);

    add_front(&list2, 0);

    display_list(&list2);

    return 0;
}