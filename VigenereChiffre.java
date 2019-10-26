/**
 * @author eSlider@gmail.com
 */
public class VigenereChiffre 
{
	// constant metrics
	public static int A 			= "A".charAt(0);	//65
	public static int Z 			= "Z".charAt(0);	//90
	public static int a 			= "a".charAt(0);	//97
	public static int z 			= "z".charAt(0);	//122
	public static int casePadding 	= a-Z-1;			//6
	public static int avaibleChars 	= (Z-A+1)+(z-a+1);	//52
	
	public static void main(String[] args) 
	{	
		// Aufgabe #4
		String key = "geheim";

		// Aufgebe #3 
		if(args.length > 1)
		{
			// encrypt
			if( Boolean.valueOf(args[0]) )	
				System.out.println(encrypt(args[1],key));
			// decrypt	
			else 							
				System.out.println(decrypt(args[1],key));
		}
		/* intern test
		else{
			System.out.println("Encrypted:" + encrypt("Lorem ipsum dolor sit amet, consectetuer adipiscing elit.", key));
			System.out.println("Decrypted:" + decrypt("rSYIU OTZYU JSSSZ YMa IYKX, KaTWLGbQZYLV MJMWMaOORN MXOX.", key));
		}//*/	
	}
	
	// Aufgabe #2 
	public static String encrypt(String text, String key){
		return shift(text, key, 1);
	}

	// Aufgabe #3
	public static String decrypt(String text, String key){
		return shift(text, key, -1);
	}
	
	public static String shift(String text, String key, int op)
	{
		String 	r			= "";
		int keyLength 		= key.length(), 
			textLength 		= text.length(),
			currentChar,
			currentKeyChar,
			i;
		
		for (i = 0; i < textLength; i++)
		{	
			// get current char
			currentChar  = (int) text.charAt(i);
			
			// alphabetic? (exclude not alphabetic chars)
			if( (currentChar >= A && currentChar <= Z) 
			|| 	(currentChar >= a && currentChar <= z) )
			{
				currentKeyChar 	= key.charAt(i%keyLength);										// get current key char
				currentKeyChar 	-= currentKeyChar 	>= a ? A+casePadding : A;					// simplify key char (convert)
				currentChar 	-= currentChar 		>= a ? A+casePadding : A;					// simplify current char (convert)
				currentChar 	= (avaibleChars+currentChar+currentKeyChar*op) % avaibleChars;	// displace current char by key and cut off the rest by module 
				currentChar 	+= currentChar>=(avaibleChars/2) ? A+casePadding : A;			// convert to ASCII			
			}
			// save char
			r += (char) currentChar;
		}
		return  r;
	}

}
