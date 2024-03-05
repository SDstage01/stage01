import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//GuiFrame for displaying desk information.

public class GuiFrame extends JFrame {
	/* Instant Variable from FlightList, PassengerList and Airport
	 * 
	 */
    private FlightList flightList;
    private PassengerList passengerList;
    
    
	//GUI Components
	JScrollPane scrollList;
	JSplitPane splitPane1, splitPane2a, splitPane2b;
	JTextPane desk1Pane, desk2Pane;
	JTextArea displayList, desk1List, desk2List, flight1List, flight2List, flight3List;
	JPanel flight1Pane, flight2Pane, flight3Pane;
	
	
	//Create Frame & panel
	public GuiFrame(FlightList flist, PassengerList plist, AirportMain adesk){
		
		//Set Close by default close button of window
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Set Window Title showing the current desk 
		setTitle("Desk" );//+ String.format("%-4s", adesk.findCurrentDesk()));

		//Set Panels
		setupSouthPanel();
		setupNorthPanel();
		setupCenterPanel();
		
		//pack and set visible
		pack();
		setVisible(true);
		
		//Pack with CheckInGUI
		CheckInGUI cig = new CheckInGUI(flightList, passengerList);
		cig.pack();
		cig.setVisible(true);
		
	}
	
	//Set NorthPanel for the passenger queue
	private void setupNorthPanel() {
		displayList = new JTextArea(15,200);
		displayList.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
		displayList.setEditable(false);
		//displayList.setText(airportDesk.queueList());
		scrollList = new JScrollPane(displayList);
		this.add(scrollList,BorderLayout.NORTH);
		
	}
	//Set CenterPanel for two desk status
	private void setupCenterPanel() {
		//Create a split pane with the two text panel for desk 1 and desk 2 status
		splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, desk1Pane, desk2Pane);
		splitPane1.setDividerLocation(100);
		desk1Pane.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
		desk1Pane.setEditable(false);
		//desk1Pane.setText(airportDesk.useDesk());
		desk2Pane.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
		desk2Pane.setEditable(false);
		//desk2Pane.setText(airportDesk.useDesk());
		this.add(splitPane1, BorderLayout.CENTER);
	}
	//Set SouthPanel for all flights information
	private void setupSouthPanel() {
		
        splitPane2a = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, flight1Pane, flight2Pane);
        splitPane2b = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPane2a, flight3Pane);
        flight1Pane.setBackground(Color.PINK);
        flight2Pane.setBackground(Color.ORANGE);
        flight3Pane.setBackground(Color.BLUE);
		//flight1Pane.setText(flightList.getFlightDetails());
		//flight2Pane.setText(flightList.getFlightDetails());
		//flight3Pane.setText(flightList.getFlightDetails());
        this.add(splitPane2b, BorderLayout.SOUTH);

	}

}
