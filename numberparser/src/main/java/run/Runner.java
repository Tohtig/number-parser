package run;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import numberparser.ParserSelector;
import numberparser.factory.Parser;


// Понимаю что фронтэнд пришит "на живую нитку". На статиках и параметре.
// И области видимости конструкторов внутри пакетов, возможно, нужно привести 
// в порядок.
public class Runner {
	private static final String IN_FILE_NAME = "input.txt";
	private static final String OUT_FILE_NAME = "output.txt";
	private static Scanner sc;
	private static PrintWriter out;
	
	public static void main(String[] args) {

		Parser parser;
		try {
			sc = new Scanner(new File(IN_FILE_NAME));
			out = new PrintWriter(new File(OUT_FILE_NAME));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
//		System.out.println(System.getProperty("user.dir"));
//		System.out.println(mode);
//		System.out.println(sc.nextInt());
		byte mode = Byte.valueOf(args[0]);
		switch(mode){
			case ParserSelector.DIGITS_TO_PHRASE:
				parser = ParserSelector.getWanted(mode);
				while(sc.hasNext()){
					out.println(parser.invert(sc.next()));
				}
//				out.flush();
				out.close();
				break;
			case ParserSelector.PHRASE_TO_DIGITS:
				parser = ParserSelector.getWanted(mode);
				while(sc.hasNextLine())
					out.println(parser.invert(sc.nextLine()));
//				out.flush();
				out.close();
				break;
			default:
				System.out.println("Sorry.");
				System.exit(0);
		}
	}
}
