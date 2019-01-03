/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Fall 2017 
// PROJECT:          Train Hub
// FILE:             LinkedList.java
//
// TEAM:    Individual
// Authors: Nawal Dua
//
// ---------------- OTHER ASSISTANCE CREDITS 
// Persons: none
// 
// Online sources: none
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * This class generates the train from a given file
 *
 * <p>Bugs: (a list of bugs and other problems)
 *
 * @author Nawal Dua
 */

import java.io.*;
import java.util.*;

/**
 * This class provide methods for generating a Train.
 * 
 * COMPLETE THIS CLASS and HAND IN THIS FILE
 * 
 * @see Config
 */
public class TrainGenerator {

	/**
	 * Get a new train generated randomly. The constant variables for determining
	 * how many cargo, what cargo and how heavy are in {@link Config}.
	 * 
	 * @return a train generated randomly
	 */
	public static Train getIncomingTrain() {
		Train incomingTrain = new Train("TrainHub");

		Random rand = new Random(System.currentTimeMillis());

		// How many freight cars
		int cartNum = rand.nextInt(Config.MAX_CART_NUM - Config.MIN_CART_NUM + 1) + Config.MIN_CART_NUM;

		for (int i = 0; i < cartNum; i++) {
			// What kind of cargo
			int loadIndex = rand.nextInt(Config.CARGO_ARRAY.length);
			String load = Config.CARGO_ARRAY[loadIndex];

			// How heavy
			int weight = rand.nextInt(Config.MAX_WEIGHT - Config.MIN_WEIGHT + 1) + Config.MIN_WEIGHT;

			// Where to
			int destIndex = rand.nextInt(Config.DEST_ARRAY.length);
			String dest = Config.DEST_ARRAY[destIndex];

			incomingTrain.add(new CargoCar(load, weight, dest));
		}

		return incomingTrain;
	}

	/**
	 * Get a new train generated from a file. Files for generating a train must have
	 * the format as follow
	 * <p>
	 * {destination},{cargo},{weight}<br>
	 * {destination},{cargo},{weight}<br>
	 * ...
	 * <p>
	 * where {destination} is a string among Config.DEST_ARRAY, {cargo} is a string
	 * among Config.CARGO_ARRAY, and {weight} is a string for any positive integer.
	 * <p>
	 * Ignore the line if it is not matched in this format. See the sample
	 * in/outputs provided in the assignment description to get more details.
	 * 
	 * @param filename
	 *            train input file name
	 * @return a train generated from a file
	 */
	public static Train getIncomingTrainFromFile(String filename) {
		File file = new File(filename); // construct the file

		Train inc = new Train("TrainHub"); // construct the incoming train
		boolean validD;  // boolean for valid destination
		boolean validC;  // boolean for valid cargo

		try {
			Scanner scnr = new Scanner(file);  // try to make a new scanner object
			while (scnr.hasNextLine()) {		
				validD = false;
				validC = false;
				String info = scnr.nextLine();
				String[] carcar = info.split(",");    // split the line by comma
				if (carcar.length == 3) {				// if there are 3 arguments
					String D = carcar[0].trim();   		// the first part will be the destination
					for (int i = 0; i < Config.DEST_ARRAY.length; i++) {
						if (D.toLowerCase().equals(Config.DEST_ARRAY[i].toLowerCase())) {  // if the destination matched
							validD = true;													// one of the given ones,
						}																	// there is the valid destination
					}
					String C = carcar[1].trim();		// the second part will be cargo
					for (int i = 0; i < Config.CARGO_ARRAY.length; i++) {
						if (C.toLowerCase().equals(Config.CARGO_ARRAY[i].toLowerCase())) {	// if the cargo matched one of the
							validC = true;													// given ones, there is valid
						}																	// cargo
					}
					int weight = Integer.parseInt(carcar[2].trim()); 	// the third part is the weight
					if (validD && validC) {								// only check if both C and D are valid
						CargoCar newCarCar = new CargoCar(C, weight, D);  // make a new car with the given info
						inc.add(newCarCar);									// add the cargo to the train
					}
				}
			}
		} catch (FileNotFoundException E) {  // catch exceptions
			return new Train(null);
		} catch (NumberFormatException E) {

		}
		return inc;  // return the incoming train
	}
}
