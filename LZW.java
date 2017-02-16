/* ////////////////////////////////////////////////////////////

File Name: LZW.java
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
 *   This implements the LZW algorithm
 *
 *
 * @author
 * @date
 *****************************************************************************/
 /*****************************************************************************

                         IMPLEMENT THIS CLASS

 *****************************************************************************/

import java.io.*;
import java.util.*;


public class LZW
{

	/** The number of bits in each LZW code */
   public static final int BIT_WIDTH = 16;

	/** The maximum number of codes inthe dictionary */
   public static final int MAX_SIZE = (1 << BIT_WIDTH) - 1;

   int code = 256;
   int code1 = 256;
   
   /**
   *  Reads-in the input file, compresses it and writes to the output file
   *  @throws EOFException
   */
   public void encode(BitReader in, BitWriter out) throws EOFException
   {
	   DictionaryTrie ob = new DictionaryTrie();
	   ob.DictionaryTrie1();
	   String current_string = Character.toString((char)in.readByte());
	   while(true)
	   {
		  int b = in.readByte();  
		  current_string = current_string + (char)b;
		  if (ob.isWord(current_string) != true) 
		  {
			  if(code < MAX_SIZE)
			  {
				  ob.add(current_string, code++);
				  int value = ob.value_string(current_string.substring(0, current_string.length() - 1));
				  out.writeInt(value);
				  current_string = current_string.substring(current_string.length() - 1);
			  }
		  }
		  
		  if(b == -1)
			  break;
	   }
	   out.flush();
   }
   
   
   /**
   *  Reads-in the input file, decodes it and writes to the output file
   * @throws EOFException 
   */
	public void decode(BitReader in, BitWriter out) throws EOFException
	{
		//throw new RuntimeException ("You need to implement this method");

		// don't forget to flush!

		DictionaryTrie ob1 = new DictionaryTrie();
		ob1.DictionaryTrie1();
		int c = 0;
		int b = in.readInt();
		while(true)
		{
			try
			{
				c = in.readInt();
				if(ob1.find(c))
				{
					String c1 = ob1.find_string(b) + ob1.find_string(c).charAt(0);
					ob1.add(c1, code1++);
					char[] str1 = (ob1.find_string(b)).toCharArray();
					for (int i = 0; i < str1.length; ++i)
					{
						out.writeByte((byte)(str1[i]));
					}
					b = c;
				}
				
				else
				{
					String c1 = ob1.find_string(b) + ob1.find_string(b).charAt(0);
					ob1.add(c1, code1++);
					char[] str1 = (ob1.find_string(b)).toCharArray();
					for (int i = 0; i < str1.length; ++i)
					{
						out.writeByte((byte)(str1[i]));
					}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         b = c;
				}
			}
			catch(IOException e)
			{
				break;
			}
		}
		char[] str1 = (ob1.find_string(c)).toCharArray();
		for (int i = 0; i < str1.length; ++i)
		{
			out.writeByte((byte)(str1[i]));
		}
		out.flush();
	}
	
	
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, EOFException
	{
		BitReader reader = new BitReader("zaInput.txt");
		BitWriter writer = new BitWriter("zLZWEncoded.dat");
		LZW lzw = new LZW();
		lzw.encode(reader, writer);
		
		BitReader reader1 = new BitReader("zLZWEncoded.dat");
		BitWriter writer1 = new BitWriter("zLZWDecoded.txt");
		lzw.decode(reader1,writer1);
	}
}
