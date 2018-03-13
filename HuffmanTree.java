/*
 * HuffmanTree.java
 *
 * A program that arranges HuffNode Ojbects into a Tree for encoding purposes
 *
 * Includes methods such as:
 * buildHuffTree - builds the Tree out of the Nodes in a LinkedList (nodeList)
 * addHuffNode - adds an additional HuffNode Object into the structure. Handles
 *  different cases such as adding a brand new Node, adding a repated Node, or
 *  beggining a new List altogether
 * findLowestHuffNode - searches for the Node with the lowest frequency in order
 *  to combine it with that of the second lowest frequency
 * combineTwoHuffNodes - take two Nodes and creates a parent Node that LinkedList
 *  to them both
 * etc.
 *
 * Includes getter methods to access the fields of the head Node in the Tree
 *  and the nodeList head Node
 *
 * Author: <William Bradley Werner>, <williamwerner@g.harvard.edu>
 * <12/1/16>
 */

import java.util.Scanner;

class HuffmanTree
{
  private HuffNode head;
  private HuffNode nodeList;
  private int numNodes;


  HuffmanTree()
  {
    head = null;
    nodeList = null;
    numNodes = 0;
  }

  /*
  * buildHuffTree - Builds a Huffman Tree of HuffNodes by combining the nodes
  * with the lowest frequencies until only the Head Node remains in the
  * LinkedList.
  */
  public void buildHuffTree()
  {
    // Creates a copy of the LinkedList to hold onto
    nodeList = new HuffNode(head);
    // Records number of nodes
    setNumNodes();

    // Continues to combine nodes until only the head remains
    while (head.getNext() != null)
    {
      // Tracks HuffNode w/ lowest & second lowest frequencies
      HuffNode low = findLowestHuffNode();
      HuffNode low2 = findLowestHuffNode();

      // Adds the combined HuffNode to the LinkedList
      HuffNode combo = combineTwoHuffNodes(low, low2);
      addHuffNode(combo);
    }
  }

  /*
  * addHuffNode - adds a new HuffNode onto the existing LinkedList
  */
  public void addHuffNode(HuffNode h)
  {
    // Starts a new LinkedList if there is none established
    if (head == null)
    {
      head = h;
      return;
    }
    // Increases frequency of head if character is repeated and readds it to
    // the tree
    if (h.getChar() == head.getChar() && (int)h.getChar() != 0)
    {
      head.increaseFreq();
      HuffNode reHead = head;
      head = head.getNext();
      reHead.setNext(null);
      addHuffNode(reHead);
      return;
    }

    HuffNode prev = head;
    HuffNode trav = head.getNext();
    // Increases frequency of repeated characters and readds them to the tree
    while (trav != null)
    {
      if (h.getChar() == trav.getChar() && (int)h.getChar() != 0)
      {
        trav.increaseFreq();
        HuffNode reNode = trav;
        prev.setNext(trav.getNext());
        reNode.setNext(null);
        addHuffNode(reNode);
        return;
      }
      // Continues down the LinkedList
      prev = trav;
      trav = trav.getNext();
    }

    // Places new HuffNode as new head if it has the least frequency
    if (h.getFreq() < head.getFreq())
    {
      h.setNext(head);
      head = h;
      return;
    }

    prev = head;
    trav = head.getNext();
    // Searches through LinkedList to find placement of new HuffNode
    while (prev != null)
    {
      // Places HuffNode at end of LinkedList if the list is exhausted
      if (trav == null)
      {
        prev.setNext(h);
        return;
      }
      // Places HuffNode if it finds a HuffNode with a higher frequency
      if (h.getFreq() < trav.getFreq())
      {
        prev.setNext(h);
        h.setNext(trav);
        return;
      }
      // Continues down the LinkedList
      prev = trav;
      trav = trav.getNext();
    }
  }

  /*
  * findLowestHuffNode - Searches through the LinkedList of HuffNodes, finds the
  * HuffNode with the lowest frequency, removes it from the LinkedList and
  * returns it
  */
  public HuffNode findLowestHuffNode()
  {
    HuffNode trav = head.getNext();
    HuffNode prev = head;
    HuffNode low = head;
    HuffNode lowPrev = null;

    while (trav != null)
    {
      if (trav.getFreq() < low.getFreq())
      {
        low = trav;
        lowPrev = prev;
      }
      prev = trav;
      trav = trav.getNext();
    }
    // Removes the HuffNode from the LinkedList
    if (low == head && head.getNext() != null) head = head.getNext();
    else if (low == head) head = null;
    else lowPrev.setNext(low.getNext());
    low.setNext(null);

    return low;
  }

  /*
  * combineTwoHuffNodes - creates a new HuffNode by combining two seperate
  * HuffNodes with a combined frequency and with the left being the lower freq
  * and the right being the higher
  *
  * returns the combined HuffNode "combo"
  */
  public HuffNode combineTwoHuffNodes(HuffNode h1, HuffNode h2)
  {
    HuffNode combo = new HuffNode();
    combo.setFreq(h1.getFreq() + h2.getFreq());
    if (h2.getFreq() < h1.getFreq())
    {
      combo.setLeft(h2);
      combo.setRight(h1);
    }
    else
    {
      combo.setLeft(h1);
      combo.setRight(h2);
    }

    return combo;
  }

  // printHuffChars - Prints out all of the chars included in the Huffman Tree
  public void printHuffChars(HuffNode h)
  {
    System.out.print("(");
    if (h.getLeft() == null && h.getRight() == null)
    {
      System.out.print (h.getChar() + " " + h.getFreq() + " ");
    }
    if (h.getLeft() != null) printHuffChars(h.getLeft());
    if (h.getRight() != null) printHuffChars(h.getRight());
    System.out.print(")");
    if (h.getNext() != null) printHuffChars(h.getNext());
  }

  // printHuffCharsFreq - Prints out all of the char's frequencies
  // Efficiency: O(n) where n = the number of HuffNodes in the HuffmanTree
  public void printHuffCharsFreq(HuffNode h)
  {
    if (h.getLeft() == null && h.getRight() == null)
      System.out.println (h.getChar() + " freq = " + h.getFreq());
    if (h.getLeft() != null) printHuffCharsFreq(h.getLeft());
    if (h.getRight() != null) printHuffCharsFreq(h.getRight());
  }

  // createHuffNode - Creates and returns a new HuffNode object from a char
  // object.
  public HuffNode createHuffNode(char c)
  {
    return new HuffNode(c);
  }

  // createCode - Traverses the Huffman Tree adding bit code for each iteration
  // of the call. (Either 0 or 1).
  public void createCode(HuffNode h, Code c)
  {
    Code co = new Code(c);

    if (h.getLeft() == null && h.getRight() == null)
    {
      System.out.println(h.getChar() + ": " + co);
      HuffNode trav = nodeList;
      while (trav != null)
      {
        if (h.getChar() == trav.getChar())
        {
          trav.setCode(new Code(co));
          break;
        }
        trav = trav.getNext();
      }
    }
    if (h.getLeft() != null)
    {
      Code leftCo = new Code(co);
      leftCo.addBit(0);
      createCode(h.getLeft(), leftCo);
    }
    if (h.getRight() != null)
    {
      Code rightCo = new Code(co);
      rightCo.addBit(1);
      createCode(h.getRight(), rightCo);
    }
  }

  // setNumNodes - Runs throught the nodes to determine their total number and
  // returns that number
  public int setNumNodes()
  {
    numNodes = 0;
    HuffNode trav = nodeList;
    while (trav.getNext() != null)
    {
      numNodes ++;
      trav = trav.getNext();
    }

    return numNodes;
  }

  // getHead - returns a reference to the HuffmanTree's head HuffNode
  public HuffNode getHead() {return head;}
  // getNodeList - returns a reference to the HuffmanTree's LinkedList of
  // HuffNodes
  public HuffNode getNodeList() {return nodeList;}

}
