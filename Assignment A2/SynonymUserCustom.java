// SynonymUser.java

/****************************************************************

SynonymUser reads the synonym data from a given file. This data
is used and modified in various ways. Finally, the modified data
is written to a new file.

See:
thesaurus.com

Author: Fadil Galjic

****************************************************************/

import java.io.*;  // IOException
import static java.lang.System.out;

class SynonymUserCustom
{
    public static void main (String[] args) throws IOException
    {
        out.println("readSynonymData");
        String[] synonymData = SynonymHandler.readSynonymData(
			"SynonymData1.txt");
        println(synonymData);
        out.println("getSynonymLine");
        String synonymLine = SynonymHandler.getSynonymLine(
			synonymData, "beautiful");
        out.println(synonymLine + "\n");
        synonymLine =
            "glowing | luminous, vibrant, flaming, gleaming";
        out.println("addSynonymLine");
        synonymData = SynonymHandler.addSynonymLine(
			synonymData, synonymLine);
        println(synonymData);
        out.println("removeSynonymLine");
        synonymData = SynonymHandler.removeSynonymLine(
			synonymData, "clever");
        println(synonymData);
        out.println("addSynonym");
        SynonymHandler.addSynonym(synonymData, "powerful",
            "briliant");
        println(synonymData);
        out.println("removeSynonym");
        SynonymHandler.removeSynonym(synonymData, "beautiful",
            "appealing");
        println(synonymData);
        out.println("sortSynonymData");
        SynonymHandler.sortSynonymData(synonymData);
        println(synonymData);

        SynonymHandler.writeSynonymData(synonymData,
            "SynonymData2.txt");
    }

    public static void println (String[] synonymData)
    {
        for (String synonymLine : synonymData)
            out.println(synonymLine);
        out.println("");
	}
}