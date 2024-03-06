
//import all the GUI classes
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//GUI Frame for FlightList and PassengerList
public class CheckInGUI extends JFrame  implements ActionListener {
	
    
    //The flight and passenger lists to be searched.
    private FlightList flightList;
    private PassengerList passengerList;
    private Passenger foundPassenger;
    private double fee1;
    private double fee2;
    private boolean cIn;
    /**
	 * GUI  components
	 */
    JTextField result1, result2, result3;
    JTextField searchField1, searchField2;
    JTextField bookingRefPanel, lastnamePanel, weightPanel, heightPanel, widthPanel, lengthPanel;
    JButton search1, search2;
    JButton enterVolume, enterWeight;
    JScrollPane scrollList;
    JButton checkIn, close;
    JTextArea resultPassengers, resultFees;
    
    /**
    *Create the frame with its panels
    *@param flist The flight list to be searched
    *@param plist The passenger list to be serarche
    */
    public CheckInGUI(FlightList flist, PassengerList plist) {
        this.flightList = flist;
        this.passengerList = plist;
        
        //set up window title
        setTitle("Airport Boarding Autonomous Service Platform");
        //disable standard close button
		setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
 
		setupSouthPanel();
		setupCenterPanel();
		setupNorthPanel();
		

        //pack and set visible
        pack();
        setVisible(true);
    }
    
    /**
    * Creates the North panel
    */
    private void setupNorthPanel() {
        //search panel contains label, text field and button FOR PASSENGER LIST
    	JPanel northPanel = new JPanel(new GridLayout(3,3));
    	
        JPanel searchPanel1 = new JPanel();
        searchPanel1.setLayout(new GridLayout(2,3));
        searchPanel1.add(new JLabel("Number:"));   
        searchField1 = new JTextField(10);
        searchPanel1.add(searchField1);
        searchPanel1.add(new JLabel(""));
        searchPanel1.add(new JLabel("Last Name:",SwingConstants.LEFT));
        searchField2 = new JTextField(10);
        searchPanel1.add(searchField2);
        search1 = new JButton("Search");  
        searchPanel1.add(search1);    
        //specify action when button is pressed
        search1.addActionListener(this) ;
        
        JPanel inforPanel1 = new JPanel();
        inforPanel1.setLayout(new GridLayout(1,2));
        inforPanel1.add(new JLabel("Number:")); 
        //Set up the area where the results will be displayed.
        result1= new JTextField(30);     
        result1.setEditable(false);
        
        //set up north panel containing 2 previous areas
       
        northPanel.add(searchPanel1);
        northPanel.add(result1);
        
        //add north panel to the content pane
        this.add(northPanel, BorderLayout.NORTH);
    }
    
     /**
    * Creates the Center panel
    */
    private void setupCenterPanel() {
    	//Set Weight Panel
    	JPanel wPanel = new JPanel();
    	wPanel.setLayout(new GridLayout(3,3));
    	wPanel.add(new JLabel("Enter Baggage Weight (kg):"));
    	wPanel.add(new JLabel(""));
    	wPanel.add(new JLabel(""));
    	wPanel.add(new JLabel(""));
    	weightPanel = new JTextField(5);
    	wPanel.add(weightPanel);
    	enterWeight = new JButton("Enter Weight");
    	enterWeight.addActionListener(this);
    	wPanel.add(enterWeight);
    	wPanel.add(new JLabel(""));

    	
    	//Set Volume Panel
    	JPanel volumePanel = new JPanel();
    	volumePanel.setLayout(new GridLayout(4,3));
    	volumePanel.add(new JLabel("Enter Baggage Dimensions(cm):", SwingConstants.CENTER));
    	volumePanel.add(new JLabel(""));
    	volumePanel.add(new JLabel(""));
    	volumePanel.add(new JLabel("Height:",SwingConstants.RIGHT));
    	heightPanel = new JTextField(5);
    	volumePanel.add(heightPanel);
    	volumePanel.add(new JLabel(""));
    	volumePanel.add(new JLabel("Width:",SwingConstants.RIGHT));
    	widthPanel = new JTextField(5);
    	volumePanel.add(widthPanel);
    	volumePanel.add(new JLabel(""));
    	volumePanel.add(new JLabel("Length:",SwingConstants.RIGHT));
    	lengthPanel = new JTextField(5);
    	volumePanel.add(lengthPanel);
    	enterVolume = new JButton("Enter Volume");
    	enterVolume.addActionListener(this);
    	volumePanel.add(enterVolume);
    	//volumePanel.add(new JLabel(""));


    	
    	
    	//Setup main Center Panel
    	JPanel centerPanel = new JPanel();
    	centerPanel.setLayout(new GridLayout(4,1));
    	centerPanel.add(wPanel);
        result2= new JTextField(25);     
        result2.setEditable(false);
        result3= new JTextField(25);     
        result3.setEditable(false);
    	centerPanel.add(result3);
        centerPanel.add(volumePanel);
        
        centerPanel.add(result2);
        
        //Add Panel to Layout        
        this.add(centerPanel, BorderLayout.CENTER);
    
    
    }
      /**
    * Creates the South panel
    */   
     private void setupSouthPanel() { 
    	JPanel southPanel = new JPanel(new GridLayout(1,2));
    	
    	//Check-In  Button
        checkIn = new JButton("Check in");
        checkIn.addActionListener(this);
        
        //Close Button
        close = new JButton("Close");
        close.addActionListener(this);
        
        //Add buttons to South Pane
        southPanel.add(checkIn);
        southPanel.add(close);
        
        //add south panel to the content pane
        this.add(southPanel, BorderLayout.SOUTH);
    }
     
     /**
     *Sets up the method for calculating baggage volume
     *If the volume is over the limit it shows the applied fee
     *Catches trying to convert a String to a double
     */
     private void bagVolIn() {
    	 String heightString = heightPanel.getText().trim();
    	 String widthString = widthPanel.getText().trim();
    	 String lengthString = lengthPanel.getText().trim();
    	 
    	 if (foundPassenger != null) {
    		 if (heightString.length() > 0 && widthString.length() > 0 && lengthString.length() > 0) {
    			 try {
    				 double height = Double.parseDouble(heightString);
    				 double width = Double.parseDouble(widthString);
    				 double length = Double.parseDouble(lengthString);
    			 
    				 double volume = height *0.01* width * 0.01* length* 0.01;
    				 foundPassenger.setBagVolume(volume);
    				 
    				 if (volume <= 0.24) {
    					 fee1 = foundPassenger.feeByVol(volume);
    					 result2.setText("Baggage volume within limits. ");
    				 }
    				 else {
    					 fee1 = foundPassenger.feeByVol(volume);
    					 String text1 = "Baggage volume exceeded, An additional payment of  " + fee1 + " pounds is required.";
    					 result2.setText(text1);   				
    				 }
    			 
    			 }
    			 catch (NumberFormatException nfe) {
    				 result2.setText("Please give a valid number for all measurements.");
    			 }
    		 }
    		 else {
    			 result2.setText("Missing baggage measurements.");
    		 }
    	 }
    	 else {
    		 result2.setText("Please enter passenger details.");
    	 }
    	 
    	 
     }
     
	/**
	*Sets up the method for entering the baggage weight
	*If the weight is over the limit it shows the applied fee
	*Catches trying to convert a String to double
	*/
     private void bagWeightIn() {
    	 String weightString = weightPanel.getText().trim();

    	 if (foundPassenger != null) {
    		 if (weightString.length() > 0) {
    			 try {
    				 double weight = Double.parseDouble(weightString);

    				 foundPassenger.setBagWeight(weight);
    				 
				 if (weight <= 30) {
    					 fee2 = foundPassenger.feeByWeight(weight);
    					 result3.setText("Baggage weight within limits. No overweight fee added.");
    				 }
    				 else {
    					 fee2 = foundPassenger.feeByWeight(weight);
    					 String text2 = "Baggage weight exceeded, An additional payment of " + fee2 + " pounds is required.";
    					 result3.setText(text2);  				
    				 }
    			 
    			 }
    			 catch (NumberFormatException nfe) {
    				 result3.setText("Please give a valid number for the weight.");
    			 }
    		 }
    		 else {
    			 result3.setText("Weight not entered.");
    		 }
    	 }
    	 else {
    		 result3.setText("Please enter passenger details.");
    	 }
     }
  
    private void search() {
    	//get search text and search staff list
    	//setting result text 
        String searchString1 = searchField1.getText().trim();
        String searchString2 = searchField2.getText().trim();
        
        if(searchString1.length() > 0 && searchString2.length() > 0) {
            Passenger person = passengerList.findPassenger(searchString1, searchString2);
            if (person != null ) {
            	if (person.getCheckedIn() == false) {
            		String fCode = person.getFlightCode(); 	
            		String destination = flightList.findByFlightCode(fCode).getDestination();
            		result1.setText("Passenger: " + person.getPassengerName().getFullName()+  ". Flight " + fCode + ". Destination: " + destination + ".");
            		foundPassenger = person;
            	} else {
            		result1.setText("Passenger already checked in");
            		searchField1.setText("");
            		searchField2.setText("");
            		foundPassenger = null;
            	}
            	//gobf.disableButton(person.getName().getFirstName());
            } else
            	result1.setText("Passenger not found.");
        } else if (searchString1.length() == 0 && searchString2.length() > 0) {
        	result1.setText("No Booking Reference given.");
        } else if (searchString1.length() > 0 && searchString2.length() == 0) {
        	result1.setText("No Last Name given.");
        } else
        	result1.setText("Please enter Booking Reference and Last Name.");
    }
    
	/**
	*Sets the method for checking in
	*If baggage volume or weigth is exceeded, it shows the total payable fee
	*/
    private void checkIn() {
    	foundPassenger.setFee(fee1+fee2);
    	foundPassenger.checkIn();
    	if (fee1+fee2 == 0) {
    		result3.setText("Passenger correctly checked in with no additional fees. Have an excellent flight!");
    	}
    	else {
    		result3.setText("Passenger correctly checked in with a total fee of: " + (fee1+fee2) + ". Have an excellent flight!");
    	}
    	result1.setText("");
    	result2.setText("");
    	searchField1.setText("");
    	searchField2.setText("");
    	heightPanel.setText("");
    	widthPanel.setText("");
    	lengthPanel.setText("");
    	weightPanel.setText("");
    }
    

    

    
    /**
     * Set up the action events
     * After clicking any action button by the user the
     * GUI gives an output 
     */    
   
    public void actionPerformed(ActionEvent e) { 
    	if (e.getSource() == search1) {
    		//displayList.setText("Search");
    		search();
    		result2.setText("");
    		result3.setText("");
    	}
		else if (e.getSource() == enterVolume) {
	    	//displayList.setText("Search");
	    	bagVolIn();
    	} 
    	else if (e.getSource() == enterWeight) {
	    	//displayList.setText("Search");
	    	bagWeightIn();
    	}
    	else if (e.getSource() == checkIn) {
        	String heightString = heightPanel.getText().trim();
       	 	String widthString = widthPanel.getText().trim();
       	 	String lengthString = lengthPanel.getText().trim();
       	 	String weightString = weightPanel.getText().trim();
       	 
        	if (foundPassenger != null && heightString.length() > 0 && widthString.length() > 0 && lengthString.length() > 0 && weightString.length() > 0) {
        		checkIn();
        	}
        	else {
        		result3.setText("Check In failed");
        	}
    	}
    	else if (e.getSource() == close) {
    		flightList.writeFlightReport("FlightReport.txt");
    		JOptionPane.showMessageDialog(this, 
    				 "FlightReport.txt was successfully created.");
    		System.exit(0);
    	}
    	
       	
    	}
	   
      
}
