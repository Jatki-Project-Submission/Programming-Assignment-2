# Programming-Assignment-2
CSC 3102 Programming Assignment #2

This Java project, Programming-Assignment-2, utilizes an AVL Tree data structure to manage and organize book orders. The system can insert, delete, and search for orders, ensuring efficient storage and retrieval using the self-balancing properties of the AVL tree.

## Overview

The **Programming-Assignment-2** reads book orders from a CSV file and stores them in an AVL tree for efficient management. Users can also manually add, remove, and search for orders through a command-line interface. The program provides a menu for interacting with the book orders, with options for adding new orders, removing existing ones, displaying orders, and fetching specific book details.

## Features

- **File Loading:** Reads book orders from a CSV file and loads them into an AVL tree.
- **AVL Tree Operations:** Efficient insertion, deletion, and in-order traversal using the AVL tree structure.
- **Command-line Interface (CLI):** Menu-driven CLI for easy interaction with the system.
- **Search Functionality:** Quickly find a book by order ID.
- **Order Management:** Add or remove orders with automatic AVL tree balancing.
- **Display Tree Information:** Shows the number of nodes and maximum height of the AVL tree.

## Setup and Installation

### Prerequisites
- **Java Development Kit (JDK)** - Ensure Java 8 or above is installed.
- **IDE (Optional)** - You can use any Java IDE like Eclipse, IntelliJ, or Visual Studio Code.

## Usage 

After running the program, you will see a menu with the following options:
1. Manually add a book order
2. Manually remove a book order
3. Print an In-Order list of book titles by order ID
4. Find the name of the book for a specific order number
5. Find the oldest book order
6. Find the latest book order
7. Exit the program
Type the number action you wish to execute and follow the prompts.

## Technologies Used
 **Java** - Main programming language
 **AVL Tree** - Self-balancing binary search tree structure to ensure efficient order management
 **CSV File Parsing** - Used to load initial book orders from a file
