package edu.brown.cs.student.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSV_Parser {

  // define four global variables for class
  private int words;
  private int characters;
  private int rows;
  private int columns;

  // assign initial values to variables and call on readCSV
  public CSV_Parser(Reader reader) {
    this.words = 0;
    this.characters = 0;
    this.rows = 0;
    this.columns = 0;
    this.readCSV(reader);
  }

  public void readCSV(Reader reader) {

    try {

      // read file and begin parsing through first line
      BufferedReader bf = new BufferedReader(reader);
      String line = bf.readLine();

      while (line != null) {

        // store line elements into an array (split) and then convert into array (to allow for
        // replaceAll to be called)
        String[] words_array = line.split(",", 0);
        List<String> words_list = new ArrayList<String>(Arrays.asList(words_array));

        // calculate number of columns based on header row (assuming header does not have empty
        // entries)
        if (this.rows == 0) {

          // if the header contains only empty space, measure columns by number of commas +1
          // else, measure columns by number of elements in header
          if (words_array.length == 0) {
            this.columns = line.length() + 1;
          } else {
            this.columns = words_array.length;
          }
        }

        // remove empty entries from file to calculate words
        words_list.removeAll(Arrays.asList("", null));
        this.words += words_list.size();

        // remove commas and empty entries from file to calculate characters
        this.characters += line.replaceAll(",", "").replaceAll(" ", "").length();

        // add to rows per iteration of while loop to determine rows (since loop is by line)
        this.rows += 1;

        // move onto next line of csv, until there are no more lines left (null)
        line = bf.readLine();
      }

      System.out.println("Words: " + this.words);
      System.out.println("Characters: " + this.characters);
      System.out.println("Rows: " + this.rows);
      System.out.println("Columns: " + this.columns);

    } catch (IOException e) {
      System.err.println("ERROR: File Not Found");
    }
  }

  // helper methods to share variables with other classes

  public int return_words() {
    return this.words;
  }

  public int return_characters() {
    return this.characters;
  }

  public int return_rows() {
    return this.rows;
  }

  public int return_columns() {
    return this.columns;
  }
}
