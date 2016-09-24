cmake_minimum_required(VERSION 3.6)
project(Exercises)

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -g -Wall")

set(SOURCE_FILES reverse.c)
add_executable(Exercises ${SOURCE_FILES})