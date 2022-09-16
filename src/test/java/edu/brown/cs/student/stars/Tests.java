package edu.brown.cs.student.stars;

import edu.brown.cs.student.csv.CSV_Parser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.junit.Assert;
import org.junit.Test;

public class Tests {

  // test csv counter functionality for ten-star data (assert values validated manually)
  @Test
  public void test_ten_star() throws FileNotFoundException {

    File file = new File("data/stars/ten-star.csv");
    FileReader reader = new FileReader(file);

    StarFactory star = new StarFactory();
    CSV_Parser parser = new CSV_Parser(reader);

    Assert.assertEquals(51, parser.return_words());
    Assert.assertEquals(333, parser.return_characters());
    Assert.assertEquals(11, parser.return_rows());
    Assert.assertEquals(5, parser.return_columns());
  }

  // test csv counter functionality for star-data (assert values validated with online tool
  // (https://charactercounttool.com/)
  @Test
  public void test_stardata() throws FileNotFoundException {

    File file = new File("data/stars/stardata.csv");
    FileReader reader = new FileReader(file);

    CSV_Parser parser = new CSV_Parser(reader);

    Assert.assertEquals(598090, parser.return_words());
    Assert.assertEquals(4807946, parser.return_characters());
    Assert.assertEquals(119618, parser.return_rows());
    Assert.assertEquals(5, parser.return_columns());
  }

  // test csv counter functionality for non-empty csv file with entirely empty rows (only commas)
  @Test
  public void test_hollow() throws FileNotFoundException {

    File file = new File("src/test/java/edu/brown/cs/student/stars/hollow_test.csv");
    FileReader reader = new FileReader(file);

    CSV_Parser parser = new CSV_Parser(reader);

    Assert.assertEquals(0, parser.return_words());
    Assert.assertEquals(0, parser.return_characters());
    Assert.assertEquals(6, parser.return_rows());
    Assert.assertEquals(5, parser.return_columns());
  }

  // test csv counter functionality for csv with a single line
  @Test
  public void test_single_line() throws FileNotFoundException {

    File file = new File("src/test/java/edu/brown/cs/student/stars/single_line_test.csv");
    FileReader reader = new FileReader(file);

    CSV_Parser parser = new CSV_Parser(reader);

    Assert.assertEquals(5, parser.return_words());
    Assert.assertEquals(15, parser.return_characters());
    Assert.assertEquals(1, parser.return_rows());
    Assert.assertEquals(5, parser.return_columns());
  }

  // test csv counter functionality for empty csv (no elements, no commas)
  @Test
  public void test_empty() throws FileNotFoundException {

    File file = new File("src/test/java/edu/brown/cs/student/stars/empty_test.csv");
    FileReader reader = new FileReader(file);

    CSV_Parser parser = new CSV_Parser(reader);

    Assert.assertEquals(0, parser.return_words());
    Assert.assertEquals(0, parser.return_characters());
    Assert.assertEquals(0, parser.return_rows());
    Assert.assertEquals(0, parser.return_columns());
  }

  // test csv counter functionality for no csv passed in
  @Test
  public void test_no_file_1() throws FileNotFoundException {

    Assert.assertThrows(
        FileNotFoundException.class,
        () -> {
          File file = new File("");
          FileReader reader = new FileReader(file);
          CSV_Parser parser = new CSV_Parser(reader);
          parser.return_words();
        });
  }

  // test csv counter functionality for nonexistent csv passed in
  @Test
  public void test_no_file_2() throws FileNotFoundException {

    Assert.assertThrows(
        FileNotFoundException.class,
        () -> {
          File file = new File("src/test/java/edu/brown/cs/student/stars/non-existent.csv");
          FileReader reader = new FileReader(file);
          CSV_Parser parser = new CSV_Parser(reader);
          parser.return_words();
        });
  }
}
