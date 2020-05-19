# Missing-Poker-Cards
MapReduce-based approach in your Hadoop system to find all the missing Poker cards.

## A MapReduce based Hadoop implementation in JAVA to find the missing poker card from a deck.

1. It uses a Amazon AWS EC2 instance.
2. Latest version os Apache Hadoop (http://hadoop.apache.org/releases.html) JAVA 8 has been installed on the instance. 
3. Appropriate security group (with appropriate firewall settings) has been assigned to allow network traffic between your two instances
4. Developed and tested a MapReduce-based approach in your Hadoop system (in a fully-distributed mode) to find all the missing Poker cards.


## Program Requirements and Outcomes

### Requirements
    1. AWS EC2 instance with Java8, MapReduce and Hadoop being running properly
    2. Input : Provide input file with deck and number separated by space or tab
    
### Outcome
    1. Missing poker cards 
  
### Code Execution
    Generated a JAR file which can be ran using the terminal of EC2 instance
    Command:  hadoop jar jar_file_name main_class_name input_file_name ouput_directory
    Eg: hadoop jar Missing_Poker_Cards_jar.jar MissingCards ~/data/MissingCardInput.txt