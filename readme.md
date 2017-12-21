Entities defined:
	Brand: Object of this class represents brand and corresoinding discount. List of all available brands are part of Store.
	Catagory: Represents a catagory, to represent the hierarchy we have for each catagory we sub catagory and parent catagory
	Customer: represents a customer
	Inventory: represents the inventory available and list od inventory represnt the customer choices
	Store : Store caontsins all the information for available bands list, Inventory and catagories
	
Client class:
	DiscountApp: This class work as client class which used to read the csv files for brands, customers and products. 
	
JUnit
	DiscountTestTest
	
Instruction to run the code using maven
--------------------------------------

prerequisite:
-----------

Maven3
jdk 8

1.download the code
2.go to root directory
3.execute mvn test

Note:
-----

We have seggregated the inventory and customer information in two different csv files becasue loading customer is different from setting up the 
store contents line inventory, brands information
