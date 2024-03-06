

public class Passenger implements Comparable<Passenger> {
	
	//instance variables
	private int bookingReference;			//Booking Reference
	private Name name;						//Name of the passenger
	private String flightCode;				//Flight code
	private boolean checkedIn;				//Passenger is checked in or not
	private double fee;						//Total fees for excess volume or weight
	private double bagWeight;				//Total weight of checked-in baggage per passenger
	private double bagVol;					//Total volume of checked-in baggage per passenger

	
	//Constructor
	public Passenger(int bookref, Name name, String fcode, boolean checkin, double fee, double weight, double vol) 
	{
		bookingReference = bookref;
		this.name = name;
		flightCode = fcode;
		checkedIn = checkin;
		this.fee = fee;
		bagWeight = weight;
		bagVol = vol;
	}
	
	//Used for TreeSet: Compares by booking reference and then by Passenger Name
	public int compareTo(Passenger other) {
		int thisBookRef = bookingReference;
		String thisName = name.getFullName();
	
		if (thisBookRef != other.getBookingReference()) {
			return thisBookRef - other.getBookingReference();
		}
		return thisName.compareTo(other.getPassengerName().getFullName());
	}
	
	//Get Methods
	public int getBookingReference() {
		return bookingReference;
	}
	public Name getPassengerName() {
		return name;
	}
	public String getFlightCode() {
		return flightCode;
	}
	public boolean getCheckedIn() {
		return checkedIn;
	}
	public double getFee() {
		return fee;
	}
	public double getBagWeight() {
		return bagWeight;
	}
	public double getBagVolume() {
		return bagVol;
	}
	
	//Necessary set methods
	public void setFee(double newFee) {
		fee = newFee;
	}
	public void setBagWeight(double newWeight) {
		bagWeight = newWeight;
	}
	public void setBagVolume(double newVol) {
		bagVol = newVol;
	}
	public void checkIn() {
		checkedIn = true;
	}
	public double feeByVol(double vol) {
		double fee1 = 0;
		if (vol > 0.24) {
			if ((vol - 0.24) <= 0.06) {
				fee1 = 20;
			}
			else if((vol -0.24) >= 0.06 ) {
				fee1 = 20 * (vol - 0.24)/0.06;
			}else if (vol<0.24) {
				fee1 = 0;
			}
		}
		return fee1;
	}
	
	public double feeByWeight (double weight) {
		double fee2 = 0;
		if (weight > 30) {
			if ((weight - 30) <= 5) {
				fee2 = 20;
			}
			else {
				fee2 = 4 * (weight - 30);}
			}else if (weight<0.24) {
				fee2 = 0;
			}
		return fee2;
	}
}
