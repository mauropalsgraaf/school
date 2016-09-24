#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

int AMOUNT_OF_BYTES = 1024;

struct Node {
    struct Node *next;
    char *buffer;
    int bufferLength;
};

void free_buffer_linked_list(struct Node *linkedList) {
    struct Node* nodeHelper;

    while (linkedList->next != NULL) {
        nodeHelper = linkedList->next;

        free(linkedList->buffer);
        free(linkedList);

        linkedList = nodeHelper;
    }

    free(linkedList);
}

void print_linked_list(struct Node node) {

    // As long as there is a next element, we print the entire buffer starting at the end of the buffer.
    while (node.next != NULL) {

        char *buffer = node.buffer;

        for (int i = node.bufferLength - 1; i >= 0; i--) {
            printf("%c", buffer[i]);
        }

        node = *node.next;
    }

}

struct Node* create_node_on_heap() {
    //This is just a simple helper function to encapsulate the errors that could occur with malloc

    struct Node* node = (struct Node *) malloc(sizeof(struct Node));

    if (node == NULL) {
        printf("Malloc could not allocate any memory, program will crash");
        exit(1);
    }

    return node;
}

int read_from_file(int fd, char *buffer, ssize_t bytes_to_read, ssize_t offset) {
    ssize_t bytesRead = pread(fd, buffer, (size_t) bytes_to_read, offset);

    if (bytesRead == -1) {
        printf("Something went wrong while reading from the file");
        exit(1);
    }

    return (int) bytesRead;
}

void print_file_reversed_from_file_descriptor(int fd) {
    ssize_t totalBytesRead = 0;
    ssize_t bytesRead = 0; //set higher than 0 so that we can loop over it and make the iteration have a cleaner interface

    // This will be the last element of the linked list. I've chosen to add an entire empty object at the end, so
    // the iteration to iterate over this object will be cleaner. There is no edge case anymore where the next one is NULL
    // but the buffer is still present.
    struct Node *linkedList = create_node_on_heap();
    linkedList->next = NULL;
    linkedList->buffer = NULL;
    linkedList->bufferLength = 0;

    // As long as the total bytes mod the amount of bytes to read is 0, all 1024 bytes have been read.
    while ((int) totalBytesRead % AMOUNT_OF_BYTES == 0) {

        struct Node *nextNode = create_node_on_heap();
        nextNode->next = NULL;
        nextNode->buffer = (char *) malloc(AMOUNT_OF_BYTES * sizeof(char));
        nextNode->bufferLength = 0;

        if (nextNode->buffer == NULL) {
            printf("Malloc could not allocate any memory, program will crash");
            exit(1);
        }

        bytesRead = read_from_file(fd, nextNode->buffer, AMOUNT_OF_BYTES, totalBytesRead);

        // If no bytes have been read, we stop the loop, no more characters are present.
        if (bytesRead == 0) {
            break;
        }

        totalBytesRead += bytesRead;

        nextNode->bufferLength = (int) bytesRead;

        //Put the newly read bytes at the front of the linked list.
        nextNode->next = linkedList;
        linkedList = nextNode;
    }


    print_linked_list(*linkedList);
    free_buffer_linked_list(linkedList);
}

int main(int args, char **argc) {
    if (args != 2) {
        printf("Bad function call: provide 1 parameter which represents the filename\n");

        exit(1);
    }

    int fd = open(argc[1], O_RDWR);

    if (fd < 0) {
        printf("Could not open file, does it exist?");

        exit(1);
    }

    print_file_reversed_from_file_descriptor(fd);

    return 0;
}
