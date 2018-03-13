/*
 * Huff.java
 *
 * A program that compresses a file using Huffman encoding.
 *
 * Editor: <William Bradley Werner>, <williamwerner@g.harvard.edu>
 * <12/5/16>
 */

import java.util.*;
import java.io.*;

public class Huff {

    /**
     * main method for compression.  Takes command line arguments.
     * To use, type: java Huff input-file-name output-file-name
     * at the command-line prompt.
     */
    public static void main(String[] args) throws IOException {
        Scanner console = new Scanner(System.in);
        FileReader in = null;               // reads in the original file
        ObjectOutputStream out = null;      // writes out the compressed file

        // Get the file names from the command line (if any) or from the console.
        String infilename, outfilename;
        if (args.length >= 2) {
            infilename = args[0];
            outfilename = args[1];
        } else {
            System.out.print("Enter the name of the original file: ");
            infilename = console.nextLine();
            System.out.print("Enter the name to be used for the compressed file: ");
            outfilename = console.nextLine();
        }

        // Open the input file.
        try {
            in = new FileReader(infilename);
        } catch (FileNotFoundException e) {
            System.out.println("Can't open file " + infilename);
            System.exit(1);
        }


        // Open the output file.
        try {
            out = new ObjectOutputStream(new FileOutputStream(outfilename));
        } catch (FileNotFoundException e) {
            System.out.println("Can't open file " + outfilename);
            System.exit(1);
        }

        // Create a BitWriter that is able to write to the compressed file.
        BitWriter writer = new BitWriter(out);

         // BEGIN MY CODE

         // Creates a new HuffmanTree Object
         HuffmanTree huffTree = new HuffmanTree();
         // Writes the characters of the input txt file into the HuffmanTree
         while (true)
         {
           int check = in.read();
           if (check > -1)
           {
             huffTree.addHuffNode(huffTree.createHuffNode((char) check));
           }
           else break;
         }

         // Builds the Huffman Tree
         huffTree.buildHuffTree();

         huffTree.printHuffChars(huffTree.getNodeList());

         // Writes out the code

        // First writes out number of HuffNodes that are in the Tree
         out.writeInt(huffTree.setNumNodes());

         HuffNode trav = huffTree.getNodeList();
         while (trav.getNext() != null)
         {
           out.writeInt((trav.getChar()));
           out.writeInt((trav.getFreq()));
           System.out.println("Writing (" + trav.getChar() + " "
                              + trav.getFreq() + ")");
           trav = trav.getNext();
         }

         huffTree.createCode(huffTree.getHead(), new Code());

         in = new FileReader(infilename);

         while(true)
         {
           int check = in.read();
           if (check > -1)
           {
             char c = (char) check;
             trav = huffTree.getNodeList();
             while (trav != null)
             {
               if (c == trav.getChar())
               {
                 writer.writeCode(trav.getCode());
                 break;
               }
               trav = trav.getNext();
             }
           }
           else break;
         }
         System.out.println("Writer Buffer = " + writer.getNumBytesWritten());
         writer.flushBits();

         /* Leave these lines at the end of the method. */
         in.close();
         out.close();

         System.out.println("Wrote out: " + huffTree.setNumNodes());
         // END MY CODE
    }
}
