/*
 * HuffNode.java
 *
 * HuffNode - HuffNode is an Object that stores characters, their frequencies,
 * a Code Object, and references to other HuffNode objects either in a
 * LinkedList or a Tree data structure. This Object is used as the main building
 * block of the Huffman Tree used in HuffmanTree.java in the same file.
 *
 * HuffNode includes 4 constructors:
 * 1) A blank constructor with null values.
 * 2) A constructor that takes in a character, records it in the c field and
 *  has a frequency of 1. This is used when a brand new character is
 *  encountered.
 * 3) A constructor that takes in a character and a frequency to create a
 *  HuffNode with a desired frequency over 1. This is used to imitate a HuffNode
 *  that was already created with a frequency higher than 1.
 * 4) A constructor that takes in a HuffNode object as a parameter and
 *  duplicates its fields.
 *
 * HuffNode Objects have setter and getter methods to access and alter their
 *  fields.
 *
 * Author: <William Bradley Werner>, <williamwerner@g.harvard.edu>
 * <12/1/16>
 */
public class HuffNode
{
  private char c;
  private int freq;
  private Code code;
  private HuffNode next;
  private HuffNode left;
  private HuffNode right;

  // HuffNode constructor that creates a new HuffNode with all null values for
  // its fields
  HuffNode(){}

  // HuffNode constructor that begins a new HuffNode with a char passed in as
  // a parameter
  HuffNode(char entry)
  {
    c = entry;
    freq = 1;
    next = null;
    left = null;
    right = null;
  }

  // HuffNode constructor that takes in a char and an int as its parameters
  // and creates a new HuffNode with these values
  HuffNode(char entry, int frequency)
  {
      c = entry;
      freq = frequency;
      next = null;
      left = null;
      right = null;
  }

  // HuffNode constructor that copies an existing HuffNode's data into a new
  // duplicate and proceeds to create all of its linked HuffNodes
  HuffNode(HuffNode h)
  {
    if (h == null) return;
    else
    {
      c = h.getChar();
      freq = h.getFreq();
      next = new HuffNode(h.getNext());
    }

  }

  // getChar - returns the char value stored in the HuffNode
  public char getChar() {return c;}

  // getFreq - returns the current frequency stored in the HuffNode
  public int getFreq() {return freq;}
  /*
  * increaseFreq - increases the frequency of HuffNode for cases where
  * a given char is being entered more than once
  */
  public void increaseFreq() {freq++;}
  // setFreq - a method that sets the frequency to an integer passes in
  public void setFreq(int i) {freq = i;}

  // getCode - returns the HuffNode's code once it has been set
  public Code getCode() {return code;}
  // setCode - sets the HuffNode's code to a Code Object passed
  public void setCode(Code co) {code = co;}

  // getNext - returns a reference to the HuffNode in the next field
  public HuffNode getNext() {return next;}
  // setNext - sets a HuffNode's next HuffNode Object to a specific HuffNode
  public void setNext(HuffNode h) {next = h;}
  // getRight - returns a reference to the HuffNode's right HuffNode Object
  public HuffNode getRight() {return right;}
  // setRight - sets a HuffNode's right HuffNode Object to a specific HuffNode
  public void setRight(HuffNode h) {right = h;}
  // getLeft - returns a reference to the HuffNode's left HuffNode Object
  public HuffNode getLeft() {return left;}
  // setLeft - sets a HuffNode's left HuffNode Object to a specific HuffNode
  public void setLeft(HuffNode h) {left = h;}
}
