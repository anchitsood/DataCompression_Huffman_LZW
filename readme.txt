	Description: This is a very interesting project which implements 2 commonplace data compression
	algorithms. Both involve the use of specific data structures, which in turn requires a solid OOP
	code setup.
	
	The Huffman compression algorithm requires a binary tree to store keys, and the tree
	itself is stored in the header of the compressed file. The decompression involves recursively
	rebuilding the key binary tree from the header string, and then using the tree to decompress the
	rest of the compressed file. Compression is implemented in HuffmanEncode.java, and decompression
	in HuffmanDecode.java.
	More about Huffman compression can be found at https://en.wikipedia.org/wiki/Huffman_coding

	The Lempel-Ziv-Welch compression algorithm one-ups the Huffman algorithm, because instead of
	encoding each character, it looks for repeating character patterns in the input file, and uses
	that information to compress it. It doesn't require a header or a key tree, so it is even better
	in space saving when working with sizeably larger files. The algorithm is implemented in LZW.java.
	More about LZW encoding can be found at https://en.wikipedia.org/wiki/Lempel%E2%80%93Ziv%E2%80%93Welch
	
	As a fun implementation, the Declaration of Independence has been compressed and decopressed using
	both the algorithms, and the results are available in zHuffmanEncoded.dat, zHuffmanDecoded.txt,
	zLZWEncoded.dat, zLZWDecoded.txt. The algorithm is ready to go, and works for all kinds of files
	(though not in the quickest possible times).
	
	This project is implemented as a solution to a problem set. Detailed description of the problem
	is available in Compression.pdf.
	
	Code practices to note: Various classes have been used to build the solution. Note use of OOP
	practices for simplicity and reusability. Multiple data srtuctures including hashmap, binary tree,
	and trie have been used.
