//package algorithm;
//
//import java.io.File;
//
//public class Main {
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		String fileName = "Data.txt";
//		File file = new File(fileName);
//		Thread mdsThread = new Thread() {
//			public void run() {
//				System.out.println("线程启动");
//				MDSet mdset = new MDSet(file);
//				mdset.run();
//			}
//		};
//		mdsThread.start();
//		while (!mdsThread.isAlive()) {
//			System.out.println("线程运行结束");
//		}
//	}
//}
