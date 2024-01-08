// ArrayNumberSequence.java

/****************************************************************

ArrayNumberSequence represents a sequence of real numbers.
Such a sequence is defined by the interface NumberSequence.
The class uses an array to store the numbers in the sequence.

Author
Fadil Galjic

****************************************************************/

public class ArrayNumberSequence implements NumberSequence
{
	// numbers in the sequence
    private double[] numbers;

    // create the sequence
    public ArrayNumberSequence (double[] numbers)
    {
		if (numbers.length < 2)
		    throw new IllegalArgumentException("not a sequence");

		this.numbers = new double[numbers.length];
		for (int i = 0; i < numbers.length; i++)
		    this.numbers[i] = numbers[i];
	}

    // toString returns the character string representing this
    // sequence
	public String toString ()
	{
		String s = "";
		for (int i = 0; i < numbers.length - 1; i++)
		    s = s + numbers[i] + ", ";
		s = s + numbers[numbers.length - 1];

		return s;
	}

    // add code here
	public int length()
	{
		return numbers.length;
	}

	public double upperBound()
	{
		double bLetters = numbers[0];

		for(int i = 1 ; i < numbers.length ; i++)
			if(numbers[i] > bLetters)
				bLetters = numbers[i];
						
		return bLetters;
	}

	public double lowerBound()
	{
		double sLetters = numbers[0];

		for(int i = 1 ; i < numbers.length ; i++)
			if(numbers[i] < sLetters)
				sLetters = numbers[i];
						
		return sLetters;
	}
    public double numberAt(int position) throws IndexOutOfBoundsException
	{
		try {
			System.out.println(numbers[position]);
		} catch (IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException("OBS!!! Position " + position + " finns inte i listan!" + "\n");
		}
		
		return numbers[position];
	}
	
	public int positionOf(double number)
	{
		for(int i = 1 ; i < numbers.length ; i++)
			if(numbers[i] == number)
				return i;
		return -1;
	}

    public boolean isIncreasing()
	{
		for(int i = 0 ; i < numbers.length - 1 ; i++)
			if(numbers[i] > numbers[i+1])
				return false;
		return true;
	}
	
    public boolean isDecreasing()
	{
		for(int i = 0 ; i < numbers.length - 1 ; i++)
			if(numbers[i] < numbers[i+1])
				return false;
		return true;
	}

	public boolean contains(double number)
	{
		for(int i = 0 ; i < numbers.length - 1 ; i++)
			if(numbers[i] == number)
				return true;
		return false;
	}

	public void add (double number)
	{
		double[] biggerNumbers = new double[numbers.length + 1];
		for(int i = 0 ; i < numbers.length ; i++)
			biggerNumbers[i] = numbers[i];
		biggerNumbers[numbers.length] = number; // arrays börjar på 0 
		numbers = biggerNumbers;
	}
	
	public void insert (int position, double number)
	{
		double[] inNumbers = new double[numbers.length + 1];

		for(int i = 0 ; i < position ; i++) // går till den innan pos
			inNumbers[i] = numbers[i];
		
		inNumbers[position-1] = number;
		
		for(int i = position ; i < numbers.length + 1 ; i ++)
			inNumbers[i] = numbers[i-1]; 	
		numbers = inNumbers;
	}

    public void removeAt (int position) throws IndexOutOfBoundsException
	{
		int lengthInt = length();
		if(position > lengthInt || position < 1)
			throw new IndexOutOfBoundsException("OBS!!! Position " + position + " finns inte i listan!");
		
		if(lengthInt == 2)
			throw new IllegalStateException ("Listan är bara " + lengthInt + " nummer lång och för liten");

		double[] lessNumbers = new double[numbers.length - 1];
		
		for (int i = 0; i < position - 1; i++) 
			lessNumbers[i] = numbers[i];	
		
		for (int i = position - 1; i < lessNumbers.length; i++) 
			lessNumbers[i] = numbers[i+1]; 
		
			numbers = lessNumbers;
	}

	public double[] asArray()
	{
		/*
		int digitCounter = 0;
		char[] timpala = new char[numbers.length];
		System.out.println(timpala); //! debug
		System.out.println(numbers.length); //! debug
		
		for (int i = 0; i < numbers.length; i++) 
		{
			System.out.println(numbers[i]); //! debug
			timpala[i] = (char) numbers[i];
			System.out,println(timpala[i]); //! debug fel
			if(Character.isDigit(timpala[i]))
				digitCounter += 1;
		}

		System.out.println(digitCounter); //! debug fel
		double[] timpala2 = new double[digitCounter];
		int counter = 0;

		for (int i = 0; i < timpala.length; i++) 
		{
			System.out.println(timpala2[1]); //! debug exception
			if(Character.isDigit(timpala[i]))
				timpala2[counter] = timpala[i];
			counter += 1;
		}
		return timpala2;
 		*/
		/*
		String[] mora = new String[numbers.length];
		double[] mora2 = new double[numbers.length];
		for (int i = 0; i < mora.length; i++) {
			System.out.println(numbers[i]);
			mora[i] = Double.toString(numbers[i]);
			System.out.println(mora[i]);
			mora2[i] = Double.parseDouble(mora[i]);
		}
		System.out.println(mora);
		System.out.println(mora2);
		return mora2;	
		*/
		return numbers;
	}
}