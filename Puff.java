/*
 * Puff.java
 *
 * A program that decompresses a file that was compressed using
 * Huffman encoding.
 *
 * Editor: <William Bradley Werner>, <williamwerner@g.harvard.edu>
 * <12/6/16>
 */

import java.util.*;
import java.io.*;

public class Puff {

    /**
     * main method for decompression.  Takes command line arguments.
     * To use, type: java Puff input-file-name output-file-name
     * at the command-line prompt.
     */
    public static void main(String[] args) throws IOException {
        Scanner console = new Scanner(System.in);
        ObjectInputStream in = null;      // reads in the compressed file
        FileWriter out = null;            // writes out the decompressed file

        // Get the file names from the command line (if any) or from the console.
        String infilename, outfilename;
        if (args.length >= 2) {
            infilename = args[0];
            outfilename = args[1];
        } else {
            System.out.print("Enter the name of the compressed file: ");
            infilename = console.nextLine();
            System.out.print("Enter the name to be used for the decompressed file: ");
            outfilename = console.nextLine();
        }

        // Open the input file.
        try {
            in = new ObjectInputStream(new FileInputStream(infilename));
        } catch (FileNotFoundException e) {
            System.out.println("Can't open file " + infilename);
            System.exit(1);
        }

        // Open the output file.
        try {
            out = new FileWriter(outfilename);
        } catch (FileNotFoundException e) {
            System.out.println("Can't open file " + outfilename);
            System.exit(1);
        }

        // Create a BitReader that is able to read the compressed file.
        BitReader reader = new BitReader(in);


        // BEGIN MY CODE

        // Tracks number of nodes being read in
        int numNodes = in.readInt();

        System.out.println("# of Nodes: " + numNodes);
        HuffmanTree puffTree = new HuffmanTree();

        for (int i = 0; i < numNodes; i++)
        {
          char c = (char)in.readInt();
          int f = in.readInt();
          puffTree.addHuffNode(new HuffNode(c, f));
          System.out.print("Read Node #" + (i+1) + ": " + c);
          System.out.print(" with frequency: " + f);
          System.out.println();
        }
        // Builds the HuffmanTree
        puffTree.buildHuffTree();
        puffTree.printHuffChars(puffTree.getHead());
        System.out.println();

        HuffNode trav = puffTree.getHead();
        int bit = -1;

        // Writes out characters corresponding to HuffmanTree
        while (true)
        {
          if (trav.getLeft() == null && trav.getRight() == null)
          {
            if (trav.getFreq() > 0)  // Prevents extra buffer bits being written
            {
              out.write(trav.getChar());
              trav.setFreq(trav.getFreq() - 1);
              trav = puffTree.getHead();
            }
            else
            {
              System.out.println("Node " + trav.getChar() + " exhausted.");
              break;
            }
          }

          bit = reader.getBit();

          if (bit == 0) trav = trav.getLeft();
          else if (bit == 1) trav = trav.getRight();
          else break;
        }

        System.out.println();

        // END MY CODE

        /* Leave these lines at the end of the method. */
        in.close();
        out.close();
    }
}
