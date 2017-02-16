/* ////////////////////////////////////////////////////////////

File Name: DictionaryTrie.java
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
 *       This class implements the dictionary trie.
 *       The trie supports all 256 ASCII characters
 *
 *
 * @author anchits
 * @date 2/25/2016
 *****************************************************************************/
 /*****************************************************************************

               DESIGN and IMPLEMENT THIS CLASS

 *****************************************************************************/


import java.util.*;
import java.util.Map.Entry;
import java.io.*;

//Create a dictionary with 256 elements with each having a hashmap
public class DictionaryTrie
{
	char c;
	Child children[];
	
//create a child
public class Child
{
	char c; 
	HashMap<Integer, String> hmap = new HashMap<Integer, String>();
//Initialize the child	
	Child()
	{
		this.c =0;
		this.hmap.put(0,null);
	}
}
//Initialize dictionary

	public DictionaryTrie() 
	{
		this.children = new Child[256];
		this.c = 0;
		for (int i =0;i<256;i++)
		{
			this.children[i] = new Child();
		}
	}
	
	
	public void DictionaryTrie1() 
	{
		this.c = 0;
		for (int i =0;i<256;i++)
		{
			this.children[i] = new Child();
		}
		for( int i =0;i<256;i++)
		{
				this.children[i].c = (char)i;
		}		
	}
public void add(String s, int code) 
{
	
		int i = (int)s.charAt(0);
		this.children[i].hmap.put(code,s.substring(1)); ;
}
	
public boolean isWord(String s) {
	if(s.length() ==1)
	{
		return true;
	}
	int i = (int)s.charAt(0);
	if(children[i].hmap.containsValue(s.substring(1)))
	{
		return true;
	}
	return false;
}


public int value_string(String s)
{
	int i = (int)s.charAt(0);
	if(s.length() ==1)
	{
		return i;
	}
	for (Entry<Integer, String> entry : children[i].hmap.entrySet()) {
        if (Objects.equals(s.substring(1), entry.getValue())) {
        	//System.out.println(entry.getKey());
            return entry.getKey();
        }
	}
    return -1;
   
}

public boolean find (int n)
{
	if( n >=0 && n<=255)
		return true;
	for ( int i =0;i<256;i++)
	{
		if(children[i].hmap.containsKey(n))
			return true;
	}
	return false;
	
}

public String find_string(int n)
{
	if( n >=0 && n<=255)
		return Character.toString(children[n].c);
	for ( int i =0;i<256;i++)
	{
		if(children[i].hmap.containsKey(n))
		return (children[i].c + children[i].hmap.get(n));
	}
	return "";
}



//	public static void main(String[] args)  {
//		DictionaryTrie ob1 = new DictionaryTrie();
//		ob1.DictionaryTrie1();
//		ob1.add("apple",300);
//		ob1.isWord("apple");
//		ob1.value_string("apple");
//		//String[] board = {"t", "h" , "e", "a", "a", "n", "d", "i", "s","t", "h" , "e", "h", "a", "v", "e" };
//	//	ob.boardBFS(board);
//
//	}

}


