package de.myralia.jsonConfigReader;

public class runtest {

	public int a;
	
	public static void main(String[] args) {
		runtest m = (runtest) ConfigParser.parseString("{\"a\":12,\"b\":13}", runtest.class);
		System.out.print(m.a);
	}

}
