// SynonymHandler

/****************************************************************

SynonymHandler handles information about synonyms for various
words.

The synonym data can be read from a file and handled in various
ways. These data consists of several lines, where each line begins
with a word, and this word is followed with a number of synonyms.

The synonym line for a given word can be obtained. It is possible
to add a synonym line, and to remove the synonym line for a given
word. Also a synonym for a particular word can be added, or
removed. The synonym data can be sorted. Lastly, the data can be
written to a given file.

Author: Fadil Galjic

****************************************************************/

import java.io.*;    // FileReader, BufferedReader, PrintWriter,
                     // IOException
import java.util.*;  // LinkedList

class SynonymHandler
{
	// readSynonymData reads the synonym data from a given file
	// and returns the data as an array
    public static String[] readSynonymData (String synonymFile)
                                         throws IOException
    {
        BufferedReader reader = new BufferedReader(
	        new FileReader(synonymFile));
        LinkedList<String> synonymLines = new LinkedList<>();
        String synonymLine = reader.readLine();
        while (synonymLine != null)
        {
			synonymLines.add(synonymLine);
			synonymLine = reader.readLine();
		}
		reader.close();

		String[] synonymData = new String[synonymLines.size()];
		synonymLines.toArray(synonymData);

		return synonymData;
    }

    // writeSynonymData writes a given synonym data to a given
    // file
    public static void writeSynonymData (String[] synonymData,
        String synonymFile) throws IOException
    {
        PrintWriter writer = new PrintWriter(synonymFile);
        for (String synonymLine : synonymData)
            writer.println(synonymLine);
        writer.close();

    }

    // synonymLineIndex accepts synonym data, and returns the
    // index of the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
	private static int synonymLineIndex (String[] synonymData,
        String word)
    {
		int delimiterIndex = 0;
		String w = "";
		int i = 0;
		boolean wordFound = false;
		while (!wordFound  &&  i < synonymData.length)
		{
		    delimiterIndex = synonymData[i].indexOf('|');
		    w = synonymData[i].substring(0, delimiterIndex).trim();
		    if (w.equalsIgnoreCase(word))
				wordFound = true;
			else
				i++;
	    }

	    if (!wordFound)
	        throw new IllegalArgumentException(
		        word + " not present");

	    return i;
	}

    // getSynonymLine accepts synonym data, and returns
    // the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
    public static String getSynonymLine (String[] synonymData,
        String word)
    {
		int index = synonymLineIndex(synonymData, word);

	    return synonymData[index];
	}

    // addSynonymLine accepts synonym data, and adds a given
    // synonym line to the data
	public static String[] addSynonymLine (String[] synonymData,
	    String synonymLine)
	{
		String[] synData = new String[synonymData.length + 1];
		for (int i = 0; i < synonymData.length; i++)
		    synData[i] = synonymData[i];
		synData[synData.length - 1] = synonymLine;

	    return synData;
	}

    // removeSynonymLine accepts synonym data, and removes
    // the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
	public static String[] removeSynonymLine (String[] synonymData,
	    String word)
	{
        // add code here
        int markedVector = synonymLineIndex(synonymData, word);
        String newSynonymData[] = new String[synonymData.length - 1];
            synonymData[markedVector] = "";

            for(int i = 0 ; i < markedVector ; i++)
                {
                    newSynonymData[i] = synonymData[i];
                }

            for(int i = markedVector + 1 ; i < synonymData.length ; i++)
                {
                    newSynonymData[i-1] = synonymData[i];
                }

            return newSynonymData;
	}

    // addSynonym accepts synonym data, and adds a given
    // synonym for a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
	public static void addSynonym (String[] synonymData,
	    String word, String synonym)
	{
        // add code here
        int markedVector = -1;
        markedVector = synonymLineIndex(synonymData, word);
        if(markedVector != -1)
        {
            synonymData[markedVector] = synonymData[markedVector] + ", " + synonym;
        }
        else
            throw new IllegalArgumentException();
	}

    // removeSynonym accepts synonym data, and removes a given
    // synonym for a given word.
    // If the given word or the given synonym is not present, an
    // exception of the type IllegalArgumentException is thrown.
	public static void removeSynonym (String[] synonymData,
	    String word, String synonym)
	{
        // add code here

        int markedVector = -1;
        markedVector = synonymLineIndex(synonymData, word);
        if(markedVector == -1)
            throw new IllegalArgumentException("cringe2");
        
        // ta bort synonym
        boolean isItLast = true;
        try
        {
            synonymData[markedVector] = synonymData[markedVector].replace(synonym + ", ", "");
        } 
        catch(Exception e)
        {
            isItLast = false;
        }
        //* replace dont worky om den inte hittar vad den ska replacea, 
        //* därför kör jag en "try" först som sedan bestämmer vad för kod ska köras genom "if"
        if(isItLast == true)
        synonymData[markedVector] = synonymData[markedVector].replace(synonym + ", ", "");
        else
        synonymData[markedVector] = synonymData[markedVector].replace(synonym, ""); 
	}

    // sortIgnoreCase sorts an array of strings, using
    // the selection sort algorithm
    private static void sortIgnoreCase (String[] strings)
    {
        // add code here
        for(int i = 0 ; i < strings.length - 1 ; i++)
        {
            for(int i2 = i + 1 ; i2 < strings.length ; i2++)
                if(strings[i2].compareToIgnoreCase(strings[i]) < 0)
                    {String tempMin = strings[i2];

            strings[i2] = strings[i];
            strings[i] = tempMin;}
        }    
	}

    // sortSynonymLine accepts a synonym line, and sorts
    // the synonyms in this line
    private static String sortSynonymLine (String synonymLine)
    {
	    // add code here
        int thatLongOne = synonymLine.indexOf("|");

        String tempSynonymLineInSplit = synonymLine.substring(thatLongOne + 2, synonymLine.length());
        // efter "|"
        String tempSynonymLineBeforeSplit = synonymLine.substring(0, thatLongOne + 2);
        // före och "|"
        // tar bort huvudordet innan split

        String tempSynonymLineArray[] = tempSynonymLineInSplit.split(", ");

        sortIgnoreCase(tempSynonymLineArray);   
        
        String stitch = tempSynonymLineBeforeSplit;
        for(int i = 0 ; i < tempSynonymLineArray.length - 1; i++)
            stitch = stitch + tempSynonymLineArray[i] + ", "; 
            // sätter ihop alla splittade synonymer

        stitch = stitch + tempSynonymLineArray[tempSynonymLineArray.length - 1]; 
        // är "- 1" för att "*.length" inte börjar på 0
        // lägger  till sista utan ", "

        return stitch;
	}

    // sortSynonymData accepts synonym data, and sorts its
    // synonym lines and the synonyms in these lines
	public static void sortSynonymData (String[] synonymData)
	{
        // add code here
        sortIgnoreCase(synonymData);
        //synonymData[0] = sortSynonymLine(synonymData[0]);
        for(int i = 0 ; i < synonymData.length ; i++)
            synonymData[i] = sortSynonymLine(synonymData[i]); // kallar sortSynonymLine för varje ord och sorterar dens synonymer
	}
}