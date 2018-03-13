William B. Werner
CSCI E-22
Grad Project



class: HuffNode - An ADT that stores a char value,and int value to record the frequency, and references to a HuffNode’s next node in a LinkedList, a left child node, a right child node, and the HuffNode’s Code.

class: HuffmanTree - An ADT designed to take in char values, arrange them into a LinkedList, and then add them into a Huffman Tree data structure.

*The time efficiencies will reference n as the number of Nodes not the number of characters*

	The main methods of HuffmanTree are:

	1. addHuffNode(HuffNode h) 
	- adds a HuffNode to a LinkedList
	- Efficiency: Performs in O(n) time in worst case and O(log n) in the 		       best
		- Takes a HuffNode Object as a parameter
		- If there is no head Node, the passed Node becomes the new 			  head
		- If h’s char value is equal to the head Node’s char value,
		  the head’s frequency is increased and the HuffNode is not
		  added to the LinkedList
		- Checks the entire LinkedList for repeated char values and
		  and increases their frequency if found 
		- If the Node is newly encountered, it checks where to add it
		- If the Node has a lower frequency than than the head, it is
		  added as the new head
		- Searches through the Linked list for a place where it has a 
		  lower frequency than the Node after it or is placed at the 
		  end

	2. findLowestHuffNode()
	- Efficiency: Performs in O(n) time in worst case and O(log n) in the 		       best
		- Searches through the LinkedList to find the Node with the 			  lowest frequency
		- Removes the Node from the LinkedList
		- returns that Node

	3. combineTwoHuffNodes(HuffNode h1, HuffNode h2)
	- Efficiency: Performs in O(1) time, performs a few actions
		- Creates a new HuffNode with a blank char value and a 
		  frequency of h1.freq + h2.freq
		- Sets the left child to h1 with the lower frequency
		- Sets the right child to h2 with the higher frequency
		- Returns the new combo HuffNode

	4. buildHuffTree()
	- Builds a Tree out of the completed LinkedList
	- Efficiency: Performs in O(n^2) time performing findLowestNode n - 1 	  times 
		- While there is more than 1 HuffNode in the LinkedList 
		  remaining, the Nodes will be removed and combined to form
		  a Huffman Tree

	5. createCode(HuffNode h, Code c)
	- Creates a Code by recursively traversing the entire Tree
	- Efficiency: Performs in O(n) where n = the number of HuffNodes 	  	  in the Tree
		- Creates a new Code Object as a copy of the passed in 			  parameter
		- The Base Case: If the Node is a leaf node, the code is 			  complete and returns
		- If there is a left child, a 0 bit is added to the code and
		  the method is called for the left child
		- If there is a right child, a 1 bit is added to the code and
		  the method is called for the right child







