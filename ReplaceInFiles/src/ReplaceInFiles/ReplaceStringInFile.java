package ReplaceInFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ReplaceStringInFile
{
    public static void main(String[] args) throws IOException
    {
        String delimiter = args[0];
        String fileToReadPath = args[1];
        String fileToWritePath = args[2];

        File fileToRead = new File( fileToReadPath );

        try (Scanner scannerToRead = new Scanner( fileToRead );
                FileWriter fileToWrite = new FileWriter( fileToWritePath );)
        {
            String oneString = scannerToRead.useDelimiter( delimiter ).next().replaceAll( "\n", " " );
            String twoString = scannerToRead.useDelimiter( delimiter ).next();

            fileToWrite.append( oneString );
            fileToWrite.append( twoString );
        } catch (FileNotFoundException e)
        {
            System.out.println( e );
        }
    }
}
