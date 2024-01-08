// NumberSequenceTest.java

/****************************************************************

NumberSequenceTest is a test program for the classes
ArrayNumberSequence and LinkedNumberSequence.

Author
Fadil Galjic

****************************************************************/

import static java.lang.System.out;

import java.util.Arrays;

class NumberSequenceTest
{
    public static void main (String[] args)
    {
		double[] realNumbers =
		    {1.0, 2.0, 3.0, 5.0, 8.0, 13.0, 21.0};
        NumberSequence sequence = null;
        // sequence = new ArrayNumberSequence(realNumbers);
        sequence = new LinkedNumberSequence(realNumbers);
        out.println("the sequence:");
        out.println(sequence);
        out.println();

        // add code here
        System.out.println("length");
        System.out.println(sequence.length()+"\n");
        
        System.out.println("upperbound");
        System.out.println(sequence.upperBound()+"\n");
        
        System.out.println("lowerbound");
        System.out.println(sequence.lowerBound()+"\n");

        System.out.println("numberat");
        System.out.println(sequence.numberAt(3)+"\n");

        System.out.println("positionof");
        System.out.println(sequence.positionOf(8)+"\n");
        
        System.out.println("isincreasing");
        System.out.println(sequence.isIncreasing()+"\n");
        
        System.out.println("isdecreasing");
        System.out.println(sequence.isDecreasing()+"\n");

        System.out.println("contains");
        System.out.println(sequence.contains(5)+"\n");

        System.out.println("add");
        sequence.add(40);
        System.out.println(sequence+"\n");

        System.out.println("insert");
        sequence.insert(1, 7);
        System.out.println(sequence+"\n");

        System.out.println("remove");
        sequence.removeAt(9);
        System.out.println(sequence+"\n");

        System.out.println("array (as String then just raw)");
        out.println(Arrays.toString(sequence.asArray()));
        System.out.println(sequence.asArray()+"\n");
    }
}