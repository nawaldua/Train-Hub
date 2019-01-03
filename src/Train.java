/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Fall 2017 
// PROJECT:          Train Hub
// FILE:             LinkedList.java
//
// TEAM:    Individual
// Authors: Nawal Dua
//
// ---------------- OTHER ASSISTANCE CREDITS 
// Persons: Patrick Heithoff, Friend, pheithoff@wisc.edu
//			He helped me understand where to get started in this file, and how
// 			this file relates to the other files.
// 
// Online sources: none
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * defines the train object
 *
 * <p>Bugs:
 *
 * @author Nawal Dua
 */

/**
 * This class represents a train.  It has a destination and a linked list 
 * of CargoCar objects.  It implements Iterable<CargoCar> by returning 
 * a direct access iterator to its linked list of cargo cars. 
 * 
 * Several methods, such as getDestination(), removeCargo() and getWeight(), 
 * are provided to manage a train object. 
 * 
 * COMPLETE THIS CLASS AND HAND IN THIS FILE
 * 
 * @see LinkedList
 * @see CargoCar
 */
public class Train implements Iterable<CargoCar> {

	private String destination;
	private LinkedList<CargoCar> train;
	
	/**
	 * Constructs Train with its destination.
	 * 
	 * @param dest train destination
	 */
	public Train(String dest){
		this.destination = dest;
		train = new LinkedList<CargoCar>();
	}
	
	/**
	 * Get the destination of this train.
	 * 
	 * @return train destination
	 */
	public String getDestination(){
		return this.destination;
	}
	
	/**
	 * Set a new destination for this train.
	 * 
	 * @param newDest new train destination
	 */
	public void setDestination(String newDest){
		this.destination = newDest;
	}
	
	/**
	 * Get the total weight of a cargo in this train.
	 * 
	 * @param the name of the cargo to sum
	 * @return total weight of specified cargo in this train
	 */
	public int getWeight(String cargoName){
		
		LinkedListIterator<CargoCar> itr = train.iterator();  // new iterator for the cargo cars in the train
		int weight = 0;
		
		while (itr.hasNext()) {
			CargoCar carcar = itr.next();
			if (carcar.getName().toLowerCase().equals(cargoName.toLowerCase())) {  // if the cargo matched the given
				weight = weight + carcar.getWeight();								// cargo, add the weight
			}
		}
		
		return weight;				// return weight
		
	}
	
	// add cargo car at end of train
	public void add(CargoCar cargoCar) {
		
		train.add(cargoCar);
		
	}

	// add cargo car as specified position 
	public void add(int pos, CargoCar newCargo) {
		
		train.add(pos, newCargo);
		
	}
	
	/**
	 * Remove the first CargoCar from this train which has the same cargo name 
	 * with the argument. If there are multiple CargoCar objects with the same 
	 * name, remove the first one.
	 * 
	 * @param The name of the cargo that you wish to remove.
	 * @return removed CargoCar object if you successfully removed a cargo, 
	 * otherwise null
	 */
	public CargoCar removeCargo(String cargoName){
		
		LinkedListIterator<CargoCar> itr = train.iterator(); // new iterator for the train
		
		String lcasename = cargoName.toLowerCase();  
		
		int pos = 0;
		
		while (itr.hasNext()) {  										// remove all cargo with matching
			CargoCar carcar = itr.next();								// cargo name
			if (carcar.getName().toLowerCase().equals(lcasename)) {
				carcar.equals(train.remove(pos));
				return carcar;
			}
			pos++;
		}
		return null;													// if the the linked list is empty, return null
	}

	public LinkedListIterator<CargoCar> iterator() {
		return train.iterator();

	}

	/**
	 * Returns the number of cargo cars on this train.  
	 * 
	 * CAUTION: the number of actual cars on a train can be changed external
	 * to the Train type.  Make sure this returns a current count of the 
	 * cargo cars on this train.  Tip: call a LinkedList method from here
	 * and make sure that the LinkedList method iterates to count cars.
	 * 
	 * @return the number of cargo cars on this train.
	 */
	public int numCargoCars() {
		
		return train.size();
	}

	/**
	 * Returns a reference to the header node from the linked list of
	 * CargoCar nodes.
	 * 
	 * CAUTION: Returning this node allows other
	 * code to change the contents of this train without
	 * calling train methods.  
	 * 
	 * It is being returned in this program to facilitate our testing
	 * and for moving sub-chains of nodes from one train to another.  
	 * THIS METHOD MAY ONLY BE CALLED BY moveMultipleCargoCars()
	 * of the TrainHub class.
	 * 
	 * @return the header node of the chain of nodes from the linked list.
	 */
	public Listnode<CargoCar> getHeaderNode() {
		
		return train.getHeaderNode();
		// TODO implement this method
	}

	/**
	 * Returns Train with a String format as following.
	 * <p>
	 * {ENGINE_START}{destination}{ENGINE_END}{CARGO_LINK}{cargo}:{weight}{CARGO_LINK}{cargo}:{weight}...
	 * <p>
	 * By default, {ENGINE_START} = ( , {ENGINE_END} = ) and {CARGO_LINK} = -> (defined in {@link Config}).
	 * So if you did not modify Config class, it will generate a String with following format.
	 * <p>
	 * ({destination})->{cargo}:{weight}->{cargo}:{weight}...
	 * 
	 * DO NOT EDIT THIS METHOD
	 * 
	 * @return train as a string format
	 */
	@Override
	public String toString(){
		String builtStr = "";
		
		builtStr += Config.ENGINE_START+this.destination+ Config.ENGINE_END;

		LinkedListIterator<CargoCar> itr = train.iterator();
		while(itr.hasNext()){
			CargoCar item = itr.next();
			builtStr += Config.CARGO_LINK + item.getName() +":" + item.getWeight();
		}
		
		return builtStr;
	}

}
