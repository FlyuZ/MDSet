package algorithm;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName = "Data.txt";
		Thread mdsThread = new Thread() {
			public void run() {
				System.out.println("�߳�����");
				MDSet mdset = new MDSet(fileName);
				mdset.run();
			}
		};
		mdsThread.start();
		while (!mdsThread.isAlive()) {
			System.out.println("�߳����н���");
		}
	}
}
