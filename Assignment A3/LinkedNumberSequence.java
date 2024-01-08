import javax.sound.midi.Sequence;

import org.w3c.dom.css.Counter;

// LinkedNumberSequence.java

/****************************************************************

LinkedNumberSequence represents a sequence of real numbers.
Such a sequence is defined by the interface NumberSequence.
The class uses linked nodes to store the numbers in the sequence.

Author
Fadil Galjic

****************************************************************/

public class LinkedNumberSequence implements NumberSequence
{
	private class Node
	{
		public double number; 
		public Node next; 

		public Node (double number)
		{
			this.number = number;
			next = null;
		}
	}

	// the first node in the node-sequence
    private Node first;

    // create the sequence
    public LinkedNumberSequence (double[] numbers)
    {
		if (numbers.length < 2)
		    throw new IllegalArgumentException("not a sequence");

        first = new Node(numbers[0]);
        Node n = first;
		for (int i = 1; i < numbers.length; i++)
		{
			n.next = new Node(numbers[i]);
			n = n.next;
		}
	}

    // toString returns the character string representing this
    // sequence
	public String toString ()
	{
		String s = "";
		Node n = first;
		while (n.next != null)
		{
		    s = s + n.number + ", ";
		    n = n.next;
		}
		s = s + n.number;

		return s;
	}

    // add code here

	public int length()
	{
		int counter = 1;
		Node n = first;
		while (n.next != null)
		{
			counter++;
			n = n.next;
		}
		
		return counter;
	}

	public double upperBound()
	{
		Node n = first;
		double stor = n.number; 

		while (n.next != null)
		{
			if (stor < n.next.number) {
				stor = n.next.number;
			}
			n = n.next;
		}
		return stor;
	}

	public double lowerBound()
	{
		Node n = first;
		double lite = n.number; 

		while (n.next != null)
		{
			if (lite > n.next.number) {
				lite = n.next.number;
			}
			n = n.next;
		}
		return lite;
	}

    public double numberAt(int position) throws IndexOutOfBoundsException
	{
		Node n = first;
		double target = -1;
		int counter = 0;

		while(n.next != null)
		{
			if(position == counter)
				{
					target = n.number;
					return target;
				}
			counter++;
			n = n.next;
		}

		throw new IndexOutOfBoundsException();
	}
	
	public int positionOf(double number)
	{
		Node n = first;
		int counter = 1;
		while(n.next != null)
		{
			if(number == n.number)
				{
					return counter;
				}

			counter++;
			n = n.next;
		}
		return -1;
	}

    public boolean isIncreasing()
	{
		Node n = first;
		while(n.next != null)
		{			
			if(n.number > n.next.number)
				return false;
			n = n.next;
		}
		return true;
	}
	
    public boolean isDecreasing()
	{
		Node n = first;
		while(n.next != null)
		{			
			if(n.number < n.next.number)
				return false;
			n = n.next;
		}
		return true;
	}

	public boolean contains(double number)
	{
		{
			Node n = first;
			while(n.next != null)
			{			
				if(n.number == number)
					return true;
				n = n.next;
			}
			return false;
		}
	}

	public void add (double number)
	{
		Node n = first;
		while (n.next != null) 
		{
			n = n.next;			
		}
		n.next = new Node (number);
	}
	
	public void insert (int position, double number)
	{
		Node n = first;
		Node numberNode = new Node(number);
		int counter = 1;

		if(position > length() || position < 1)
			throw new IndexOutOfBoundsException("OBS!!! Position " + position + " finns inte i listan");

		while (n.next != null && counter < position) 
		{
			n = n.next;
			counter++;
		}
		
		if(position == 1)
		{
			numberNode.next = first;
			first = numberNode;		
		}
		else
		{
			Node tempNode;
			tempNode = n.next;
			n.next = numberNode;
			n = n.next;
			n.next = tempNode;
		}
	}

    public void removeAt (int position) throws IndexOutOfBoundsException
	{
		Node n = first;

		int lengthInt = length();
		if(position > lengthInt || position < 1)
			throw new IndexOutOfBoundsException("OBS!!! Position " + position + " finns inte i listan");
		
		if(lengthInt == 2)
			throw new IllegalStateException ("Listan är bara " + lengthInt + " nummer lång och för liten");

		if (position == 1) 
		{
			first = n.next;	
		} 
		else 
		{
			int counter = 1;
			
			while (n.next.next != null && counter < position) 
			{
				n = n.next;
				counter++;
			}
			n.next = n.next.next;
		}
	}

	public double[] asArray()
	{
		double[] numbersArray = new double[length()];
		Node n = first;
		numbersArray[0] = first.number;
		int counter = 1;

		while(n.next != null && counter < length())
		{	
			n = n.next;
			numbersArray[counter] = n.number;
			counter++;
		}

		return numbersArray;
	}

}