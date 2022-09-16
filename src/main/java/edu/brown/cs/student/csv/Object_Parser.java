package edu.brown.cs.student.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// instantiate reader and CreatorFromRow object
public class Object_Parser<T> {

  private BufferedReader bf;
  private CreatorFromRow<T> star;

  // set global variables equal to constructor parameters
  public Object_Parser(Reader reader, CreatorFromRow<T> creator) {
    this.bf = new BufferedReader(reader);
    this.star = creator;
  }

  public List<T> object_parser(Reader reader, CreatorFromRow<T> creator) {

    // create empty list of stars (List<T> can take any type of object)
    List<T> list_of_stars = new ArrayList<T>();

    try {

      // read file and begin parsing through first line
      String line = bf.readLine();
      List<String> words;

      while (line != null) {

        // store line elements into an array (split) and then convert into array
        String[] words_array = line.split(",", 0);
        words = new ArrayList<String>(Arrays.asList(words_array));

        try {

          // create star and add to list of stars
          T new_star = this.star.create(words);
          list_of_stars.add(new_star);

        } catch (FactoryFailureException e) {
          throw new RuntimeException(e);
        }

        // move onto next line, until there are no more lines left (null)
        line = bf.readLine();
      }
    } catch (IOException e) {
      System.err.println("ERROR: java.io.FileNotFoundException");
    }

    // return list of stars with added objects
    return list_of_stars;
  }
}
