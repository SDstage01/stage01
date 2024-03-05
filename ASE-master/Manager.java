public class Manager {
	
	private PassengerList allPassengers;
	private FlightList allFlights;
	public Manager() {
		allPassengers = new PassengerList();
		allFlights = new FlightList(allPassengers);
	}
    
	public void run() throws PassengerException {
		
		allFlights.readFile("Flight_mock.csv");
		allPassengers.readFile("Passenger_mock.csv");
		

		CheckInGUI gui = new CheckInGUI(allFlights, allPassengers);
        	gui.setVisible(true);
		

	}
}
