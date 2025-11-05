import java.io.*;
import java.util.*;

interface Displayable3{
	String displayInfo();
}

class LotFullException extends Exception{
	LotFullException(String m){
		super(m);
	}
}

class InvalidSpotException extends Exception{
	InvalidSpotException(String m){
		super(m);
	}
}

abstract class Vehicle implements Displayable3{
	private String plate;
	private String owner;
	
	public Vehicle(String plate, String owner) {
		this.plate = plate;
		this.owner = owner;
	}
	
	public String getPlate() {
		return plate;
	}
	
	public String getOwner() {
		return owner;
	}
	
	abstract public double getRatePerHour();
	
	public String displayInfo() {
		return "The vehicle plate is " + plate + "and owner is " + owner;
	}
}

class Car extends Vehicle implements Displayable3{
	public Car(String plate, String owner){
		super(plate, owner);
	}
	
	public double getRatePerHour() {
		return 5.0;
	}
	
	@Override
	public String displayInfo() {
		return "The rate for this car is $" + this.getRatePerHour();
	}
}

class Motorcycle extends Vehicle implements Displayable3{
	public Motorcycle(String plate, String owner) {
		super(plate, owner);
	}
	
	public double getRatePerHour() {
		return 3.0;
	}
	
	@Override
	public String displayInfo() {
		return "The rate for this motorcycle is $" + this.getRatePerHour();
	}
}

class EVCar extends Vehicle implements Displayable3{
	public EVCar(String plate, String owner) {
		super(plate, owner);
	}
	
	public double getRatePerHour() {
		return 1.5;
	}
	
	@Override
	public String displayInfo() {
		return "The rate for this EV is $" + this.getRatePerHour();
	}
}

class Ticket implements Serializable{
	private final Vehicle v;
	private final int row;
	private final int col;
	private final long startEpochMin;
	private final double ratePerHour;
	
	public Ticket(Vehicle v, int row, int col, long startEpochMin, double ratePerHour) {
		this.v = v;
		this.row = row;
		this.col = col;
		this.startEpochMin = startEpochMin;
		this.ratePerHour = ratePerHour;
	}
	
	public double fee(long nowEpochMin) {
		long time = nowEpochMin - startEpochMin;
		double hrs = Math.ceil(time / 60.0);
		return hrs * ratePerHour;
	}
	
	public Vehicle getVehicle() {
		return v;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public long getTime() {
		return startEpochMin;
	}
	
	public double getRate() {
		return ratePerHour;
	}
}

class ParkingLot{
	private String name;
	private char[][] spots;
	private ArrayList<Ticket> ticket = new ArrayList<Ticket>();
	
	public ParkingLot(String name, int rows, int cols) {
		this.name = name;
		this.spots = new char[rows][cols];
		for(int i=0; i<rows; i++) {
			Arrays.fill(spots[i], 'E');
		}
	}
	
	public String spotMap() {
		StringBuilder st = new StringBuilder();
		st.append("Welcome to parking lot ").append(name).append("\n");
		for(int i = 0; i<spots.length; i++) {
			for(int j = 0; j<spots[i].length; j++) {
				st.append(spots[i][j]).append(" ");
			}
			st.append("\n");
		}
		return st.toString();
	}
	
	public boolean isFree(int row, int col) throws InvalidSpotException{
		if(spots[row][col] == 'E') {
			return true;
		} else if(spots[row][col] == 'O') {
			return false;
		} else {
			throw new InvalidSpotException("The spot is invalid");
		}
	}
	
	public Ticket park(Vehicle v) throws LotFullException{
		for (int i = 0; i<spots.length; i++) {
			for(int j = 0; j<spots[i].length; j++) {
				if(spots[i][j] == 'E') {
					spots[i][j] = 'O';
					long startEpochMin = System.currentTimeMillis() / 60000;
					Ticket t = new Ticket(v, i, j, startEpochMin, v.getRatePerHour());
					ticket.add(t);
					return t;
				}
			}
		} throw new LotFullException("The parking lot is full");
	}
	
	public Ticket park(String type, String plate, String owner) throws LotFullException{
		Vehicle v = null;
		switch(type.toLowerCase()){
		case "car":
			v = new Car(plate, owner);
			break;
		case "motorcycle":
			v = new Motorcycle(plate, owner);
			break;
		case "EVCar":
			v = new EVCar(plate, owner);
			break;
		}
		return park(v);
	}
	
	public double leave(String plate) {
		for(int i = 0; i<ticket.size(); i++) {
			Ticket t = ticket.get(i);
			if(t.getVehicle().getPlate().equalsIgnoreCase(plate)) {
				spots[t.getRow()][t.getCol()] = 'E';
				long nowEpochMin = System.currentTimeMillis()/60000;
				double fee = t.fee(nowEpochMin);
				ticket.remove(i);
				return fee;
			} else {
				System.out.println("Vehicle was not found in this parking lot");
			}
		}
		return 0.0;
	}
	
	public Vehicle[] activeVehicles() {
		Vehicle[] vs = new Vehicle[ticket.size()];
		for(int i = 0; i<ticket.size(); i++) {
			Ticket t = ticket.get(i);
			vs[i] = t.getVehicle();
		}
		return vs;
	}
	
	public void save() throws IOException{
		try{
			FileOutputStream file = new FileOutputStream("Parking Status.dat");
			DataOutputStream out = new DataOutputStream(new BufferedOutputStream(file));
			out.writeUTF(name);
			out.writeInt(spots.length);
			out.writeInt(spots[0].length);
			for(int i = 0; i<spots.length; i++) {
				for(int j = 0; j<spots[i].length; j++) {
					out.writeChar(spots[i][j] + ' ');
				}
			}
			out.writeInt(ticket.size());
			for(Ticket t:ticket) {
				Vehicle v = t.getVehicle();
				String type = (v instanceof Car) ? "car" : " ";
				type = (v instanceof Motorcycle) ? "motorcycle" : " ";
				type = (v instanceof EVCar) ? "ev" : " ";
				out.writeUTF(type);
				out.writeUTF(v.getPlate());
				out.writeUTF(v.getOwner());
				out.writeLong(t.getTime());
				out.writeInt(t.getRow());
				out.writeInt(t.getCol());
				out.writeDouble(t.getRate());
				}
			out.flush();
			} catch (IOException m) {
				System.out.println("Data save failed");
		}
	}
	
	public void load() throws IOException {
		try {
			FileInputStream fileIn = new FileInputStream("Parking Status.dat");
			DataInputStream in = new DataInputStream(new BufferedInputStream(fileIn));
			String name = in.readUTF();
			int rows = in.readInt();
			int cols = in.readInt();
			if(rows!=spots.length || cols!=spots[0].length) {
				throw new IOException("Data mismatch");
			}
			for(int i = 0; i<rows; i++) {
				for(int j = 0; j<cols; j++) {
					spots[i][j] = in.readChar();
				}
			}
			int ticketSize = in.readInt();
			for(int a = 0; a<ticketSize; a++) {
				String type = in.readUTF();
				String plate = in.readUTF();
				String owner = in.readUTF();
				Vehicle v = null;
				switch(type.toLowerCase()) {
				case "car":
					v = new Car(plate, owner);
					break;
				case "motorcycle":
					v = new Motorcycle(plate, owner);
					break;
				case "ev":
					v = new EVCar(plate, owner);
					break;
				}
				long startEpochMin = in.readLong();
				int row = in.readInt();
				int col = in.readInt();
				double rate = in.readDouble();
				Ticket t = new Ticket(v, row, col, startEpochMin, rate);
				ticket.add(t);
			}
		}catch (FileNotFoundException m) {
			
		}catch (IOException m) {
			System.out.println("Data load failed");
		}
	}
}

public class ParkingSystem {

	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		ParkingLot pk = new ParkingLot("MCCLot1", 10, 10);
		pk.load();
		
		while(true) {
		System.out.println("Welcome to MCC parking lot 1");
		System.out.println("Please choose menu 1 to 5");
		System.out.println("1. Map");
		System.out.println("2. Rate");
		System.out.println("3. Park");
		System.out.println("4. Leave");
		System.out.println("5. Exit");
		int option = input.nextInt();
		input.nextLine();
		if(1<=option && option<=5) {
			switch(option) {
			case 1:
				System.out.println(pk.spotMap());
				break;
			case 2:
				System.out.println("Please enter your vehicle type(car, motorcycle, or evcar), plate and name");
				String type = input.next();
				String plate = input.next();
				String owner = input.next();
				Vehicle v = null;
				switch(type.toLowerCase()){
				case "car":
					v = new Car(plate, owner);
					break;
				case "motorcycle":
					v = new Motorcycle(plate, owner);
					break;
				case "evcar":
					v = new EVCar(plate, owner);
					break;
				}
					System.out.println(v.displayInfo());
					break;
			case 3:
				System.out.println("Please enter your vehicle type, plate and name");
				String type1 = input.next();
				String plate1 = input.next();
				String owner1 = input.next();
				Ticket t = pk.park(type1, plate1, owner1);
				System.out.println("Ticket: " + t.getRow() + "\n" + t.getCol() + "\n" + t.getTime() + "\n" + t.getRate());
				break;
			case 4:
				System.out.println("Enter your plate");
				String plate2 = input.next();
				System.out.println("The parking fee is $" + pk.leave(plate2));
				break;
			case 5:
				pk.save();
				System.out.println("Thanks for choosing our parking lot! GoodBye!");
				System.exit(option);
			}
		} else if(option<1 || option>5) {
			System.out.println("Wrong option, please enter again(1 to 5)");
			continue;
		} else {
			System.out.println("Invalid option, system exist");
			pk.save();
			}
		}
	}
}
