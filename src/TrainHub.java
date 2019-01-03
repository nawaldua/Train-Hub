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
 * the methods that process the train and cargo cars are defines here
 *
 * <p>Bugs: 
 *
 * @author Nawal Dua
 */

/**
 * This class represents a train hub and provides the common operations needed
 * for managing the incoming and outgoing trains.
 *
 * It has a LinkedList of Train as a member variable and manages it.
 *
 * COMPLETE THIS CLASS AND HAND IN THIS FILE
 *
 * @see LinkedList
 * @see Train
 * @see Config
 */
public class TrainHub {

	/** The internal data structure of a hub is a linked list of Trains */
	private LinkedList<Train> trains;

	/**
	 * Constructs and initializes TrainHub object
	 */
	public TrainHub() {
		this.trains = new LinkedList<Train>();
	}

	/**
	 * This method processes the incoming train. It iterates through each of the
	 * cargo car of the incoming train. If there is an outgoing train in the train
	 * list going to the destination city of the cargo car, then it removes the
	 * cargo car from the incoming train and adds the cargo car at the correct
	 * location in the outgoing train. The correct location is to become the first
	 * of the matching cargo cars, with the cargo cars in alphabetical order by
	 * their cargo name.
	 *
	 * If there is no train going to the destination city, it creates a new train
	 * and adds the cargo to this train.
	 *
	 * @param train
	 *            Incoming train (list or cargo cars)
	 */
	public void processIncomingTrain(Train train) {

		if (train.getDestination() == null) {
			return;
		}
		// iterates through each of the cargo car of the incoming train.
		LinkedListIterator<CargoCar> itr = train.iterator();
		while (itr.hasNext()) {

			CargoCar curCarCar = itr.next();
			String D = curCarCar.getDestination();

			if (trains.isEmpty()) {
				Train firsttrain = new Train(D);
				firsttrain.add(curCarCar);
				trains.add(firsttrain);

				// If there is an outgoing train in the train
				// list going to the destination city of the cargo car
			} else {

				Train sameDTrain = findTrain(D);

				if (sameDTrain == null) {
					Train newTrain = new Train(D);
					newTrain.add(curCarCar);
					trains.add(newTrain);
				} else {

					// removes the
					// cargo car from the incoming train and adds the cargo car at the correct
					// location in the outgoing train.
					int pos = 0;
					LinkedListIterator<CargoCar> sameDTrainItr = sameDTrain.iterator();
					while (sameDTrainItr.hasNext()) {
						CargoCar tCar = sameDTrainItr.next();
						if (curCarCar.getName().compareTo(tCar.getName()) <= 0) {
							train.removeCargo(D);
							sameDTrain.add(pos, curCarCar);
							break;
						}
						pos++;
						if (pos == sameDTrain.numCargoCars()) {
							train.removeCargo(D);
							sameDTrain.add(pos, curCarCar);
							break;
						}

					}

				}
			}
		}
	}

	/**
	 * This method tries to find the train in the list of trains, departing to the
	 * given destination city.
	 *
	 * @param dest
	 *            Destination city for which train has to be found.
	 * @return Pointer to the train if the train going to the given destination
	 *         exists. Otherwise returns null.
	 */
	public Train findTrain(String dest) {
		LinkedListIterator<Train> itr = trains.iterator(); // new iterator for the trains at the hub
		while (itr.hasNext()) {
			Train curT = itr.next();
			if (curT.getDestination().toLowerCase().equals(dest.toLowerCase())) { // if the train has the same
				// destination
				return curT; // return the the train
			}
		}
		return null;
	}

	/**
	 * This method removes the first cargo car going to the given destination city
	 * and carrying the given cargo.
	 *
	 * @param dest
	 *            Destination city
	 * @param name
	 *            Cargo name
	 * @return If there is a train going to the the given destination and is
	 *         carrying the given cargo, it returns the cargo car. Else it returns
	 *         null.
	 */
	public CargoCar removeCargo(String dest, String name) {
		Train train = findTrain(dest); // find a train with the same destination
		if (train == null) {
			return null;
		}
		return train.removeCargo(name); // remove cargo that matches the name, from the found train
	}

	/**
	 * This method iterates through all the trains in the list and finds the sum of
	 * weights of given cargo in all trains.
	 *
	 * @param name
	 *            Name of the cargo
	 * @return Total weight of given cargo in all departing trains.
	 */
	public int getWeight(String name) {
		LinkedListIterator<Train> itr = trains.iterator(); // iterator for the trains at the hub
		int weight = 0;
		while (itr.hasNext()) { // for each train
			Train train = itr.next();
			weight = weight + train.getWeight(name); // sum up all the weights of the cargo from each train
		}
		return weight; // return the total weight
	}

	/**
	 * This method is used to depart the train to the given destination. To depart
	 * the train, one needs to delete the train from the list.
	 *
	 * @param dest
	 *            Destination city for which corresponding train has to be
	 *            departed/deleted.
	 * @return True if train to the given destination city exists. If not, then
	 *         return false.
	 */
	public boolean departTrain(String dest) {

		Train train = findTrain(dest); // find the train going to that destination

		if (train == null) { // if that train does not exist, return false
			return false;
		} else {
			int pos = 0;
			LinkedListIterator<Train> itr = trains.iterator(); // iterator for the train at the hub
			while (!itr.next().equals(train)) { // get the position of the train
				pos++;
			}
			trains.remove(pos); // remove the train from the hub
			return true;
		}
	}

	/**
	 * This method deletes all the trains.
	 *
	 * @return True if there was at least one train to delete. False if there was no
	 *         train to delete.
	 */
	public boolean departAllTrains() {
		if (trains.isEmpty()) {
			return false;
		}
		while (!trains.isEmpty()) // remove each train till the hub is empty
		{
			trains.remove(0);
		}
		return true;
	}

	/**
	 * Display the specific train for a destination.
	 *
	 * @param dest
	 *            Destination city for the train the to be displayed.
	 * @return True if train to the given destination city exists. If not, then
	 *         return false.
	 */
	public boolean displayTrain(String dest) {

		Train train = findTrain(dest); // find the a train going to the destination
		if (train == null) {
			return false;
		} else {
			System.out.println(train.toString()); // print that train
			return true;
		}
	}

	/**
	 * This method is used to display all the departing trains in the train hub.
	 * Train should be displayed in the specified format.
	 *
	 * @return True if there is at least one train to print. False if there is no
	 *         train to print.
	 */
	public boolean displayAllTrains() {
		if (trains.isEmpty()) {
			return false;
		}

		LinkedListIterator<Train> itr = trains.iterator(); // iterator for the trains at the hub

		while (itr.hasNext()) {

			Train train = itr.next();
			System.out.println(train.toString()); // print all the trains
		}

		return true;
	}

	/**
	 * Move all cargo cars that match the cargo name from a source (src) train to a
	 * destination (dst) train.
	 *
	 * The matching cargo cars are added before the first cargo car with a name
	 * match in the destination train.
	 *
	 * All matching cargo is to be moved as one chain of nodes and inserted into the
	 * destination train's chain of nodes before the first cargo matched node.
	 *
	 * PRECONDITION: there is a source train and a destination train, and the source
	 * train of nodes has at least one node with matching cargo. We will not test
	 * other conditions.
	 *
	 * NOTE: This method requires direct access to the chain of nodes of a Train
	 * object. Therefore, the Train class has a method in addition to ListADT
	 * methods so that you can get direct access to header node of the train's chain
	 * of nodes.
	 *
	 * @param src
	 *            a reference to a Train that contains at least one node with cargo.
	 *
	 * @param dst
	 *            a reference to an existing Train. The destination is the train
	 *            that will have the cargo added to it. If the destination chain
	 *            does not have any matching cargo, add the chain at its correct
	 *            location alphabetically. Otherwise, add the chain of cargo nodes
	 *            before the first matching cargo node.
	 *
	 * @param cargoName
	 *            The name of cargo to be moved from one chain to another.
	 */
	public static void moveMultipleCargoCars(Train srcTrain, Train dstTrain, String cargoName) {

		// get references to train header nodes
		// get references to train header nodes
		Listnode<CargoCar> srcHeader, dstHeader, prev, curr;
		srcHeader = srcTrain.getHeaderNode();
		dstHeader = dstTrain.getHeaderNode();

		Listnode<CargoCar> first_prev = null, first = null, last = null;
		boolean hasFound = false;

		// 1. Find references to the node BEFORE the first matching cargo node
		// and a reference to the last node with matching cargo.
		curr = srcHeader;
		while (curr.getNext() != null) {
			if (!hasFound && curr.getNext().getData().getName().equals(cargoName)) { // if the cargo has not been found,
				first_prev = curr; // and the next cargo is the correct one
				first = curr.getNext(); // set the references appropriately
				hasFound = true;
			}
			if (hasFound && !curr.getNext().getData().getName().equals(cargoName)) { // if transition goes from the
				// correct
				last = curr; // cargo to an incorrect cargo, set the
				break; // reference appropriately
			}
			curr = curr.getNext();
		}

		// NOTE : We know we can find this cargo,
		// so we are not going to deal with other exceptions here.

		// 2. Remove from matching chain of nodes from src Train
		// by linking node before match to node after matching chain
		first_prev.setNext(last.getNext());

		// 3-1. Find reference to first matching cargo in dst Train
		curr = dstHeader;
		Listnode<CargoCar> dst_prev = null, dst = null;
		while (curr.getNext() != null) {
			if (curr.getNext().getData().getName().equals(cargoName)) {
				dst_prev = curr;
				dst = curr.getNext();
				break;
			}
			curr = curr.getNext();
		}
		// 3-2. If found, insert them before cargo found in dst
		if (dst_prev != null) {
			dst_prev.setNext(first);
			last.setNext(dst);
		}

		// 3-3. If no matching cargo, add at correct location in dst
		else {
			int pos = 0;
			LinkedListIterator<CargoCar> dstTrainItr = dstTrain.iterator();
			while (dstTrainItr.hasNext()) {
				CargoCar car = dstTrainItr.next();
				if (cargoName.compareTo(car.getName()) <= 0) {
					dst_prev.setNext(first);
					last.setNext(dst);
					break;
				}
				pos++;
				if (pos == dstTrain.numCargoCars()) {
					curr = dstHeader;
					for (int i = 0; i < dstTrain.numCargoCars(); i++) {
						curr = curr.getNext();
					}
					curr.setNext(first);
					break;
				}

			}
		}
	}
}
