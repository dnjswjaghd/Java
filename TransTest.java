class Bus
{	
	int busNumber;
	int passengerCount;
	int money;
	
	Bus(int busNumber) {
		this.busNumber = busNumber;
	}
	
	void take(int money) {  
		this.money += money;
		passengerCount++;
	}
	
	void showBusInfo() {
		System.out.println(busNumber + "번 버스의 승객은 " + passengerCount + "명 이고, 수입은 " + money + "원 입니다");
	}



}

class Subway {

	int lineNumber;
	int passengerCount;
	int money;
	
	Subway(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	void take(int money) {
		this.money += money;
		passengerCount++;
	}
	
	void showSubwayInfo() {
		System.out.println(lineNumber + "호선 지하철의 승객은 " + passengerCount + "명 이고, 수입은 " + money + "원 입니다.");
	}
}

class Taxi
{
	String grade;
	int passengerCount;
	int money;
	int distance;

	Taxi(String grade){
		this.grade = grade;
	}
	void take(int money){
		this.money += money;
		passengerCount++;
	}

	void showTaxiInfo() {
		System.out.println(grade + "택시의 승객은 " + passengerCount + "명 이고, 수입은 " + money + "원 입니다.");
	}
}

class Student
{
	String studentName;
	int money;
	
	Student(String studentName, int money) {
		this.studentName = studentName;
		this.money = money;
	}
	
	void takeBus(Bus bus) {
		bus.take(1000);
		this.money -= 1000;
	}
	
	void takeSubway(Subway subway) {
		subway.take(1200);
		this.money -= 1200;
	}

	void takeNormalTaxi(Taxi taxi){
		taxi.take(4000);
		this.money -= 4000;
	}
	void takeHJighTaxi(Taxi taxi){
		taxi.take(6000);
		this.money -= 6000;
	}

	void showInfo() {
		System.out.println(studentName +"님의 남은 돈은 " + money + "원 입니다");
	}

}

class TransTest
{
	public static void main(String[] args) {
		Student studentY = new Student("영희", 35000);
		Student studentC = new Student("철수", 20000);
		Student studentG = new Student("길동", 20000);
		Student studentS = new Student("영수", 30000);
		Bus bus100 = new Bus(100);
		Bus bus200 = new Bus(200);
		Subway subwayBlue = new Subway(1);
		Subway subwayGreen = new Subway(2);
		Taxi taxi1 = new Taxi("일반");
		Taxi taxi2 = new Taxi("모범");
		
		studentY.takeBus(bus100);
		studentC.takeSubway(subwayBlue);
		studentG.takeSubway(subwayGreen);
		studentS.takeSubway(subwayBlue);
		studentY.takeNormalTaxi(taxi1);
		studentC.takeNormalTaxi(taxi1);
		studentG.takeHighTaxi(taxi2);
		

		
		
		
		studentY.showInfo();
		studentC.showInfo();
		studentG.showInfo();
		
		bus100.showBusInfo();
		subwayBlue.showSubwayInfo();
		subwayGreen.showSubwayInfo();

		taxi1.showTaxiInfo();
		taxi2.showTaxiInfo();
	}

}