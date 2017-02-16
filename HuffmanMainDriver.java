import java.io.FileNotFoundException;
import java.io.IOException;

/**
 ******************************************************************************
 *
 *   The main class for the Huffman compression algorithm
 *
 *
 *****************************************************************************/


public class HuffmanMainDriver
{
	public static void main(String[] args) throws IOException
	{
		HuffmanEncode hc = new HuffmanEncode();
		BitReader reader = new BitReader("zaInput.txt");
		BitWriter writer = new BitWriter("zHuffmanEncoded.dat");
		hc.encode(reader, writer);

		HuffmanDecode hd = new HuffmanDecode();
		reader = new BitReader("zHuffmanEncoded.dat");
		writer = new BitWriter("zHuffmanDecoded.txt");
		hd.decode(reader, writer);

//		BitReader reader = new BitReader("test.txt");
//		BitWriter writer = new BitWriter("output.txt");
//		System.out.println(hc.countFrequencies(reader));
//		System.out.println(hc.getCodes());
		
//		if (hc.getCodeTreeRoot() == hd.getCodeTreeRoot())
//		{
//			System.out.println("Yay!");
//		}
//		else
//		{
//			System.out.println("Nope!");
//		}

		
//		HuffmanEncode testEnc1 = new HuffmanEncode();
//		HuffmanEncode testEnc2 = new HuffmanEncode();
//		HuffmanDecode testDec1 = new HuffmanDecode();
//		
//		BitWriter writer1 = new BitWriter("test1.txt");
//		BitWriter writer2 = new BitWriter("test2.txt");
//		
//		testEnc1.encode(reader, writer1);
//		testDec1.decode(reader, writer2);
//		testEnc2.root = testDec1.root;
//		
//		testEnc1.writeHeader(writer1);
//		testEnc2.writeHeader(writer2);		
	}
}