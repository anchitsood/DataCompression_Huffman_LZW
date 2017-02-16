/* ////////////////////////////////////////////////////////////

File Name: HuffmanDecode.java
Copyright (c) 2016 Anchit Sood (sood.anchit@gmail.com).  All rights reserved.


Redistribution and use in source and binary forms, with or without modification, 
are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, 
   this list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice, 
   this list of conditions and the following disclaimer in the documentation 
   and/or other materials provided with the distribution.


This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.

//////////////////////////////////////////////////////////// */



/**
 ******************************************************************************
 *
 *   This implements the Huffman decoding algorithm
 *
 *
 * @author anchits
 * @date 2/25/2016
 *****************************************************************************/
 /*****************************************************************************

                         IMPLEMENT THIS CLASS

 *****************************************************************************/

import java.io.*;
import java.util.*;


public class HuffmanDecode
{
	/** Code bit for a leaf node in file-based tree representation */
   public static final int LEAF = 0;

	/** Code bit for a parent node in file-based tree representation */
   public static final int PARENT = 1;

	/** Code bit for the left child in the file-based tree representation */
   public static final int LEFT = 0;

	/** Code bit for the right child in the file-based tree representation */
   public static final int RIGHT = 1;

	/** the root of the Huffman tree    */
	private HuffmanNode root;
	//public HuffmanNode root;

	/**
	*  Reads-in the input file, decodes it and writes to the output file
	*/
	public void decode(BitReader reader, BitWriter writer)
	{
		if (reader.length() == 0) return;

		HuffmanTree(reader);
		int fileBytes = 0;
		try{ fileBytes = reader.readInt(); } catch(EOFException e) {}

		for (int i = 0; i < fileBytes; i++)
			writer.writeByte((byte) decode(reader));

		writer.flush();
	}


	/**
	* Reads the header from a compressed file and builds the Huffman tree for decoding.
	*
	*/
	public void HuffmanTree(BitReader br)
	{
		//throw new RuntimeException("You must implement this method");
		
//		byte garbageByte = -1;
//		int garbageFreq = 0;
//		HuffmanNode rootNode = new HuffmanNode(garbageByte,garbageFreq);
//		HuffmanNode thisNode = rootNode;
//		int headerSize = br.length();
//		
//		for (int i = 0; i < headerSize; ++i)
//		{
//			if (br.readBit() == 1)
//			{
//				//createnodelevel
//				HuffmanNode newNode1 = new HuffmanNode(garbageByte,garbageFreq);
//				HuffmanNode newNode2 = new HuffmanNode(garbageByte,garbageFreq);
//				HuffmanNode newDepth = new HuffmanNode(newNode1, newNode2);
//				
//				thisNode = newDepth;
//				newDepth = null;
//				thisNode = thisNode.getLeft();
//			}
//			else if (br.readBit() == 0)
//			{
//				//createnode, fill next byte into the node
//
//				thisNode = thisNode.getParent();
//				
//				while ((thisNode.getLeft().getValue() != garbageByte) && (thisNode.getRight().getValue() != garbageByte))
//				{
//					thisNode = thisNode.getParent();
//				}
//				
//				if (thisNode.getLeft().getValue() == garbageByte)
//				{
//					HuffmanNode newNodeMeaningful1 = new HuffmanNode((byte)br.readByte(),garbageFreq);
//					HuffmanNode newLevel = new HuffmanNode(newNodeMeaningful1, thisNode.getRight());
//					thisNode = newLevel;
//					newLevel = null;
//					thisNode = thisNode.getLeft();
//				}
//				else if (thisNode.getRight().getValue() == garbageByte)
//				{
//					HuffmanNode newNodeMeaningful2 = new HuffmanNode((byte)br.readByte(),garbageFreq);
//					HuffmanNode newLevel = new HuffmanNode(thisNode.getLeft(), newNodeMeaningful2);
//					thisNode = newLevel;
//					newLevel = null;
//					thisNode = thisNode.getRight();
//				}
//			}
//		}
//		root = rootNode;
			
		root = TreeBuilder(br);
	}

	
	/**
	 * This method reads bits from the reader and traverse the Huffman tree
	 * to get the value stored in a leaf.
	 */
	public Byte decode (BitReader r)
	{
		//throw new RuntimeException("You must implement this method");
		
//		int codedLength = r.length();
//		for (int i = 0; i < r.length(); ++i)
//		{
//			
//		}
		
		HuffmanNode current = root;
		while (true)
		{
			if (current.isLeaf())
			{
				break;
			}
			
			else if (r.readBit() == LEFT)
			{
				current = current.getLeft();
			}
			
			else
			{
				current = current.getRight();
			}
		}
		
		return current.getValue();
	}

	
	/**
	 * For testing purposes
	 * DO NOT CHANGE!
	 */
	public HuffmanNode getCodeTreeRoot()
	{
		return root;
	}


	//////////////////////////////////////////////////////////////////////////
	//Helper functions
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Helper function
	 * Recursively builds Huffman tree
	 */
	private static HuffmanNode TreeBuilder(BitReader br)
	{

//		HuffmanNode node = new HuffmanNode(((byte)(-1)), 0);
//		//System.out.println(br);
//        //reading header and determining if created node is the leaf or non-leaf.
//        if (br.readBit() == 0)
//        {
//            // feed the next position to the next stack frame by doing computation before preOrder is called.
//            int byteVal = br.readByte();
//        	HuffmanNode tempNode = new HuffmanNode((byte)byteVal,0);
//            node = tempNode;
//            //System.out.println(byteVal);
//            return node;
//        } 
//
//        br.readBit();  // feed the next position to the next stack frame by doing computation before preOrder is called.
//        HuffmanNode left = TreeBuilder(br);
//        //node = left;
//        
//        br.readBit(); // feed the next position to the next stack frame by doing computation before preOrder is called.
//        HuffmanNode right = TreeBuilder(br);
//
//        HuffmanNode usable = new HuffmanNode(left, right);
//        node = usable;
//        
//        //node.left = left;
//        //node.right = right;
//
//        return node;

		HuffmanNode left, right;
		if (br.readBit() == LEAF)
		{
			return (new HuffmanNode((byte)(br.readByte()),0));
		}
		
		else
		{
			left = TreeBuilder(br);
			right = TreeBuilder(br);
			return (new HuffmanNode(left, right));
		}		
	}
	
	//////////////////////////////////////////////////////////////////////////
	//End of helper functions
	//////////////////////////////////////////////////////////////////////////
}
