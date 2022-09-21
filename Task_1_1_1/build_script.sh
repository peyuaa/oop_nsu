#!/bin/sh
javac app/src/main/java/ru/nsu/peyuaa/HeapSort.java
javadoc app/src/main/java/ru/nsu/peyuaa/HeapSort.java
jar cf heapsort.jar app/src/main/java/ru/nsu/peyuaa/HeapSort.class
java -jar heapsort.jar
