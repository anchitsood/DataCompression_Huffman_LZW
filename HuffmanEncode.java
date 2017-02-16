/* ////////////////////////////////////////////////////////////

File Name: HuffmanEncode.java
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
 *   This implements the Huffman encoding algorithm
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


public class HuffmanEncode
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

	/** it stores chars and their frequencies    */
	private Map<Byte, Integer> freqMap;

	/** it stores bytes and the encoding */
	private Map<Byte, int[]> encoding;

	
	/**
	*  Reads-in the input file, compresses it and writes to the output file
	*/
	public void encode(BitReader reader, BitWriter writer)
	{
		int fileBytes = reader.length();
		if (fileBytes == 0) return;

		countFrequencies(reader);
		HuffmanTree(freqMap);
		try{ writeHeader(writer); } catch(IOException e) {}
		writer.writeInt(fileBytes);

		reader.reset();

		for (int i = 0; i < fileBytes; i++)
			encode((byte) reader.readByte(), writer);

		writer.flush();
	}


	/**
	* This method takes a item and writes the corresponding codeword.
	* The bits <tt>LEFT</tt> and <tt>RIGHT</tt> are written so that
	* if one takes that path in the Huffman tree they will get to the
	* leaf node representing <tt>item</tt>.
	*/
	public void encode(Byte item, BitWriter writer)
	{
		//throw new RuntimeException("You must implement this method");
		
		//look at this byte
		//search tree for this item
		//follow path needed to reach here
		//output path address
		
		//traverse tree recursively
		//populate the map encoding
		
		for (Map.Entry<Byte, int[]> entry : encoding.entrySet())
		{
			if (entry.getKey() == item)
			{
				for (int i = 0; i < entry.getValue().length; ++i)
				{
					writer.writeBit(entry.getValue()[i]);
				}
				break;
			}
			else
			{
				//do nothing
				continue;
			}
		}
	}


	/**
	*  Calculates frequencies of each character from the ASCII table
	*/
	public Map<Byte, Integer> countFrequencies(BitReader reader)
	{
		//throw new RuntimeException("You must implement this method");

//		//read from the bit reader till it runs out
//		byte buf[] = new byte[reader.length()];
//		int numBytes = reader.readBytes(buf, 0, reader.length());
//		//byte array buf is now populated
//		
//		//for each byte read:
//		for (int i = 0; i < buf.length; ++i)
//		{
//			byte temp = buf[i];
//			map.put(temp, map.get(temp) + 1);
//			//if present in map:
//		}
//
//		//return the updated map


		HashMap<Byte, Integer> map = new HashMap<Byte, Integer>();

		int byteCounts[] = new int[256];
		int byteLength = reader.length();
		
		for (int i = 0; i < byteLength; ++i)
		{
			byteCounts[reader.readByte() & 0xff]++;
		}
		
		for (int i = 0; i < 256; ++i)
		{
			if (byteCounts[i] != 0)
			{
				map.put((byte)i, byteCounts[i]);
			}
		}
		this.freqMap = map;
		return map;
	}


	/**
	* Takes a list of (Byte, Frequency) pairs (here represented as a map)
	* and builds a tree for encoding the data items using the Huffman
	* algorithm.
	*/
	public void HuffmanTree (Map<Byte, Integer> map)
	{
		//throw new RuntimeException("You must implement this method");
		
//		//find min value entry in hashmap
//		byte currByte = -1;
//		int currMin = 2147483647;
//		for (Map.Entry<Byte, Integer> entry : map.entrySet())
//		{
//			byte key = entry.getKey();
//			int value = entry.getValue();
//			
//			if (value < currMin)
//			{
//				currMin = value;
//				currByte = key;
//			}
//			else
//			{
//				continue;
//			}
//		}
//		
//		//delete from hashmap and make a huffman node
//		int snip1 = map.remove(currByte);
//		HuffmanNode node1 = new HuffmanNode(currByte, currMin);
//		
//		
//		//find next min value entry in hashmap
//		//at the same time, if hashmap is empty, do not look for a second minimum, instead promote first minimum to root, and exit from recursion
//		
//		currByte = -1;
//		currMin = 2147483647;
//		for (Map.Entry<Byte, Integer> entry : map.entrySet())
//		{
//			byte key = entry.getKey();
//			int value = entry.getValue();
//			
//			if (value < currMin)
//			{
//				currMin = value;
//				currByte = key;
//			}
//			else
//			{
//				continue;
//			}
//		}
//				
//		//delete from hashmap and make another huffman node
//		int snip2 = map.remove(currByte);
//		HuffmanNode node2 = new HuffmanNode(currByte, currMin);
//				
//		
//		
//		//combine huffman nodes into tree
//		if (snip1 <= snip2)
//		{
//			HuffmanNode parent = new HuffmanNode(node1, node2);
//		}
//		else
//		{
//			HuffmanNode parent = new HuffmanNode(node2, node1);
//		}
//		
//		//add parent and its freq back to the hashmap for future recursive calls
//		//how?


		//or just use priority queues!
		
		final Queue<HuffmanNode> nodeQueue = nodeTraversalQueue(map);
		while (nodeQueue.size() > 1) 
        {
            final HuffmanNode node1 = nodeQueue.remove();
            final HuffmanNode node2 = nodeQueue.remove();
            HuffmanNode node = new HuffmanNode(node1, node2);
            nodeQueue.add(node);
        }
        
        this.root = nodeQueue.remove();
        //need to remove the last node, which is effectively the head/root node!
        //can recycle nodeQueue now
        //head node is called node
        //refer to children by using all different methods
        
        
        int[] array = new int[0];
		Map<Byte, int[]> codes = new HashMap<Byte, int[]>();
        addToFinalHashmap(root,codes,array);
        
        this.encoding = codes;
	}
	
	
	/**
	* Writes the Huffman tree into a compressed file.
	*
	* The format for the tree is defined recursively. To write
	* the entire tree, you start with the root. When the node
	* is a leaf node, you write the bit <tt>LEAF</tt>
	* and then call the <tt>writeByte</tt> to write the node value.
	* Otherwise, you write the bit <tt>PARENT</tt>, then
	* go to the left and right nodes.
	*/
	public void writeHeader(BitWriter writer) throws IOException
	{
		//throw new RuntimeException("You must implement this method");
		
		populateBitWriter(root,writer);
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
	 * recursively traverses tree and populates the tree header
	 */
	private static void populateBitWriter(HuffmanNode node, BitWriter writer)
	{
		int out;
		
		if (node.isLeaf())
		{
			out = LEAF;
			writer.writeBit(out);
			writer.writeByte(node.getValue());
			//System.out.println(node.getValue());
		}
		
		else
		{
			out = PARENT;
			writer.writeBit(out);
			populateBitWriter(node.getLeft(), writer);
			populateBitWriter(node.getRight(), writer);
		}
	}
	
	
	/**
	 * Helper function
	 * recursively traverses tree and populates the codewords for all the bytes
	 */
	private static void addToFinalHashmap(HuffmanNode node, Map<Byte, int[]> map, int[] code)
	{

//		if (node == null)
//		{
//			return;
//		}
//		
//		if (!node.isLeaf())
//		{
//			code = appendToIntArray(code, LEFT);
//			addToFinalHashmap(node.getLeft(), map, code);
//		}
//		
//		else
//		{
//			if (map.containsKey(node.getValue()))
//			{
//				if (!node.getParent().isRoot())
//				{
//					code = cutFromIntArray(code);
//					code = cutFromIntArray(code);
//					addToFinalHashmap(node.getParent().getParent(), map, code);
//				}
//				else
//				{
//					//we've reached the root!
//					//we're done!
//					return;
//				}
//			}
//			else
//			{
//				map.put(node.getValue(), Arrays.copyOfRange(code,1,code.length));
//				code = cutFromIntArray(code);
//				code = appendToIntArray(code, RIGHT);
//				addToFinalHashmap(node.getParent().getRight(), map, code);
//			}
//		}

		if (node.isLeaf()) 
		{
            map.put(node.getValue(), code);
            return;
        }
		addToFinalHashmap(node.getLeft(), map, appendToIntArray(code, LEFT));
		addToFinalHashmap(node.getRight(), map, appendToIntArray(code, RIGHT));
	}
	
	
	/**
	 * Helper function
	 * builds a new priority queue by converting each entry of the frequency map to node, and assigning priorities based on frequencies.
	 * lowest frequencies are always dequeued first
	 */	
	private static Queue<HuffmanNode> nodeTraversalQueue(Map<Byte, Integer> map)
	{
		final Queue<HuffmanNode> temp = new PriorityQueue<HuffmanNode>(new HuffmanNodeComparator());
		for (Map.Entry<Byte, Integer> entry : map.entrySet())
		{
			temp.add(new HuffmanNode(entry.getKey(), entry.getValue()));
		}
		return temp;
	}

	
	/**
	 * Helper function
	 * appends an int to an int array
	 */
	private static int[] appendToIntArray(int[] a, int e) 
	{
	    a  = Arrays.copyOf(a, a.length + 1);
	    a[a.length - 1] = e;
	    return a;
	}
	
	
//	/**
//	 * Helper function
//	 * Cuts an int array by one by removing its last int
//	 */
//	private static int[] cutFromIntArray(int[] a) 
//	{
//	    a  = Arrays.copyOf(a, a.length - 1);
//	    return a;
//	}
	
	
//	/**
//	 * Helper function
//	 * outputs the codewords for all bytes based on Huffman tree
//	 */
//	public Map<Byte, Integer> getCodes()
//	{
//		Map<Byte, Integer> codes = new HashMap<Byte, Integer>();
//		
//		for (Map.Entry<Byte, int[]> entry : encoding.entrySet())
//		{
//			int code = 0;
//			for (int i = entry.getValue().length; i > 0; --i)
//			{
//				code = code + (entry.getValue()[i - 1] * (int)(Math.pow(((double)10),((double)(entry.getValue().length - i)))));
//			}
//			codes.put(entry.getKey(), code);
//		}
//		
//		return codes;
//	}
	
	//////////////////////////////////////////////////////////////////////////
	//End of helper functions
	//////////////////////////////////////////////////////////////////////////
}


/**
 * Comparator class
 * overrides the compareTo function in HuffmanNode class
 * acts as comparator to build priorityQueue of nodes from the frequency hashmap
 */
final class HuffmanNodeComparator implements Comparator<HuffmanNode>
{
	@Override
	public int compare(HuffmanNode node1, HuffmanNode node2)
	{
		return node1.getFreq() - node2.getFreq();
	}
}
