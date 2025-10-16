/**
 * Build a console program to manage flights and seat bookings.
 * Use a 2D array for seats,  
 * OOP design (classes, inheritance),  
 * interfaces, custom exceptions, and binary I/O to save/load data.  
 */
import java.util.*;
import java.io.*;

interface Displayable1{
	String displayInfo();
}

class SeatUnavailableException extends Exception{
	SeatUnavailableException(String m){
		super(m);
	}
}

class InvalidSeatException extends Exception{
	InvalidSeatException(String m){
		super(m);	
	}
}

abstract class Person1{
	private String name;
	private String id;
	public Person1(String name, String id) {
		this.name = name;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public String getId() {
		return id;
	}
}

class Passenger extends Person1 implements Displayable1{
	public Passenger(String name, String id) {
		super(name, id);
	}
	
	@Override
	public String displayInfo() {
		return "Passenger " + getName() + " id is " + getId();
	}
}

class Flight implements Displayable1, Serializable {
	private String code;
	private int rows;
	private int cols;
	private String[][] seatMap;
	private String[][] booking;
	
	public Flight(String code, int rows, int cols) {
		this.code = code;
		this.rows = rows;
		this.cols = cols;
		this.seatMap = new String[rows][cols];
		this.booking = new String[rows][cols];
		for(int i = 0; i<rows; i++) {
			Arrays.fill(seatMap[i], "E");
		}
	}
	
	public String getCode() {
		return code;
	}
	
	public boolean isSeatAvailable(int r, int c) throws InvalidSeatException{
		if(r<rows && c<cols && seatMap[r][c].equals("E")) {
			return true;
		} else if(r<rows && c<cols && seatMap[r][c].equals("R")){
			return false;
		} else {
			throw new InvalidSeatException("Seat out of bound");
		}
	}
	
	public void bookSeat(int r, int c, Passenger p) throws SeatUnavailableException, InvalidSeatException {
		if(isSeatAvailable(r, c) == true) {
			System.out.println(p.displayInfo() + " booked seat sucessfully");
			seatMap[r][c] = "R";
			booking[r][c] = p.getId() + "|" + p.getName();
		} else if(isSeatAvailable(r, c) == false) {
			System.out.println("Seat is unavailable");
		} else {
			throw new SeatUnavailableException("Seat out of bound");
		}
	}
	
	public void cancelSeat(int r, int c, Passenger p) throws SeatUnavailableException, InvalidSeatException {
		String passengerInfo = p.getId() + "|" + p.getName();
 		if(isSeatAvailable(r, c) == false && passengerInfo.trim().equals(booking[r][c])) {
			System.out.println("Seat is canceled sucessfully");
			seatMap[r][c] = "E";
			booking[r][c] = null;
		} else if(isSeatAvailable(r, c) == true || !passengerInfo.trim().equals(booking[r][c])) {
			System.out.println("Seat is not booked or this is not your ticket");
		} else {
			throw new SeatUnavailableException("Seat out of bound");
		}
	}
	
	@Override
	public String displayInfo() {
		String fl = "Flight " + code + " seat map ";
		StringBuilder sm = new StringBuilder();
		for(int r = 0; r<rows; r++) {
			sm.append("\n");
			for(int c = 0; c<cols; c++) {
				sm.append(seatMap[r][c] + " ");
			}
		}
		return fl + sm;
	}
	
	public static void writeData(ArrayList<Flight> flights){
		try {
			FileOutputStream file = new FileOutputStream("FlightInfo.dat");
			ObjectOutputStream oos = new ObjectOutputStream(file);
			oos.writeObject(flights);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			System.out.println("Data saved failed");
		}
	}
	
	public static ArrayList<Flight> load() throws ClassNotFoundException {
		ArrayList<Flight> flights = new ArrayList<Flight>();
		try {
			File file = new File("FlightInfo.dat");
			if(!file.exists()) {
				System.out.println("File does not exist");
				return new ArrayList<>();
			} else {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream oos = new ObjectInputStream(fis);
				flights = (ArrayList<Flight>) oos.readObject();
				return flights;
			}
		} catch (IOException e) {
			System.out.println("Data load failed");
		} return flights;
	}
}

public class AirPlaneSeatBooking {

	public static void main(String[] args) throws ClassNotFoundException, SeatUnavailableException, InvalidSeatException {
		ArrayList<Flight> flights;
		boolean found = true;

		System.out.println("Welcome to flight booking system;");
		System.out.println("Please enter your name and id");
		Scanner input = new Scanner(System.in);
		String name = input.next();
		String id = input.next();
		Person1 p = new Passenger(name, id);
		
		flights = Flight.load();
		if (flights.isEmpty()) {
			flights.add(new Flight("M101", 15, 5));
			flights.add(new Flight("M102", 20, 5));
		}
		
		while(true) {
			System.out.println("Please choose option from below");
			System.out.println("1. Show flights");
			System.out.println("2. Show seat map");
			System.out.println("3. Book seat");
			System.out.println("4. Cancle seat");
			System.out.println("5. Exit System");
			
			int option = input.nextInt();
			input.nextLine();
			
			if(option>=1 && option<=5) {
				switch(option) {
				case 1:
					for(Flight f : flights) {
						System.out.println(f.getCode());
					}
					break;
				case 2:
					for(Flight f : flights) {
						System.out.println(f.displayInfo());
					}
					break;
				case 3:
					System.out.println("Please input the code to choose the flight");
					String book = input.next();
					System.out.println("Please enter the row and column resepctively");
					int r = input.nextInt();
					int c = input.nextInt();
					for (Flight f : flights) {
						if (f.getCode().equalsIgnoreCase(book)) {
							found = true;
							if(!f.isSeatAvailable(r, c)) {
								System.out.println("Seat is booked");
								} else {
								f.bookSeat(r, c, (Passenger) p);
								Flight.writeData(flights);
							}
					} 
				}
					if (!found){
					System.out.println("Flight code is wrong");
			}
				break;
				case 4:
					System.out.println("Please input the code to choose the flight");
					String cancel = input.next();
					System.out.println("Please enter the row and column resepctively");
					int r1 = input.nextInt();
					int c1 = input.nextInt();
					for (Flight f : flights) {
						if (f.getCode().equalsIgnoreCase(cancel)) {
							found = true;
							if(f.isSeatAvailable(r1, c1)) {
								System.out.println("Seat is not booked");
							} else {
								f.cancelSeat(r1, c1,  (Passenger) p);
								Flight.writeData(flights);
							}
					} 
				}
					if (!found){
					System.out.println("Flight code is wrong");
			}
				break;
				case 5:
					Flight.writeData(flights);
					System.out.println("Thank you!");
					System.exit(option);
				}
			} else if (option<1 || option>5) {
				System.out.println("Invalid number, please enter again");
				continue;
			} else {
				System.out.println("Wrong input, system ends");
			}
		}
	}

}
