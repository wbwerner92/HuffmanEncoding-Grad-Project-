class HuffTester
{
  public static void main(String [] args)
  {
    HuffmanTree ht = new HuffmanTree();
    String ans = "Testing fraze and whatnot...";

    for (int i = 0; i < ans.length(); i++)
    {
      ht.addHuffNode(ht.createHuffNode(ans.charAt(i)));
      ht.printHuffChars(ht.head);
      System.out.println();
    }

    ht.buildHuffTree();
    ht.printHuffCharsFreq(ht.head);

    System.out.println("Number of Nodes = " + ht.numNodes);

    System.out.println();
    ht.createCode(ht.head, new Code());
    System.out.println("Code = " + ht.codeString);
  }
}
