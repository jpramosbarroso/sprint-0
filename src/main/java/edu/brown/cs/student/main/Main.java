package edu.brown.cs.student.main;

import edu.brown.cs.student.csv.CSV_Parser;
import edu.brown.cs.student.csv.Object_Parser;
import edu.brown.cs.student.kdtree.DistanceSorter;
import edu.brown.cs.student.kdtree.KdTree;
import edu.brown.cs.student.stars.GalaxyGenerator;
import edu.brown.cs.student.stars.Star;
import edu.brown.cs.student.stars.StarFactory;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/** The Main class of our project. This is where execution begins. */
public final class Main {
  /**
   * The initial method called when execution begins.
   *
   * @param args An array of command line arguments
   */
  public static void main(String[] args) {
    new Main(args).run();
  }

  private String[] args;
  private String filepath;

  private Main(String[] args) {
    this.args = args;

    //    try {
    //      CSV_Parser parser = new CSV_Parser(new FileReader(args[0]));
    ////      CSV_Parser parser = new CSV_Parser(new FileReader("data/stars/stardata.csv"));
    ////      Object_Parser object_parser = new Object_Parser(new
    // FileReader("data/stars/stardata.csv"), new StarFactory());
    //      Object_Parser object_parser = new Object_Parser(new FileReader(args[0]), new
    // StarFactory());
    //
    //    } catch (FileNotFoundException e) {
    //      System.err.println("ERROR: java.io.FileNotFoundException");
    //    }
  }

  private void scannable() {

    Scanner object_scan = new Scanner(System.in);
    this.filepath = object_scan.nextLine();
  }

  private void run() {
    // prints out command line arguments; can remove this
    System.out.println(Arrays.toString(args));

    // create parser and object_parser to run both created classes with arg passed in by user
    try {
      CSV_Parser parser = new CSV_Parser(new FileReader(args[0]));
      Object_Parser object_parser = new Object_Parser(new FileReader(args[0]), new StarFactory());

    } catch (FileNotFoundException e) {
      System.err.println("ERROR: File Not Found");
    }

    // generates galaxy of stars, computes nearest neighbor for all
    if (args.length == 2 && args[0].equals("generate_galaxy")) {
      int numStars = 0;
      try {
        numStars = Integer.parseInt(args[1]);
      } catch (Exception ignored) {
        System.err.println("ERROR: Could not parse number of stars to generate.");
      }
      List<Star> galaxy = GalaxyGenerator.generate(numStars);
      KdTree<Star> starKdTree = new KdTree<>(galaxy, 0);
      for (Star star : galaxy) {
        PriorityQueue<Star> pq =
            starKdTree.kdTreeSearch(
                "neighbors", 1, star, new DistanceSorter(star), new HashSet<>());
        System.out.println(pq.peek());
      }
    }
  }
}
