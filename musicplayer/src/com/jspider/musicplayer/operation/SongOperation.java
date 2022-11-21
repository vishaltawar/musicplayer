package com.jspider.musicplayer.operation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.jspider.musicplayer.musicplayer.Player;
import com.jspider.musicplayer.song.Songs;

public class SongOperation {
	// private static Statement statement = Player.statement;
	public static Connection connection;
	public static PreparedStatement preparedStatement;
	private static ResultSet resultSet;
	private static int result;

	ArrayList<Songs> playList = new ArrayList<Songs>();

	// Add Songs
	public void addSongs() {
		try {
			// Step:- 1 Load the Driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				// Step:- 2 Open the connection
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weje2?user=root&password=123456");
				preparedStatement = connection.prepareStatement("insert into song values(?,?,?,?,?,?,?)");

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Songs songs = new Songs();
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Song Id : ");
		int id = scanner.nextInt();
		try {
			preparedStatement.setInt(1, id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		// songs.setInt(preparedStatement.setInt(1, id));

		System.out.print("Enter Song Name : ");
		String name = scanner.next();
		try {
			preparedStatement.setString(2, name);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		System.out.print("Enter Song Movie/Album : ");
		String movie = scanner.next();
		try {
			preparedStatement.setString(3, movie);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		System.out.print("Enter Song Length : ");
		float length = scanner.nextFloat();
		try {
			preparedStatement.setFloat(4, length);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		System.out.print("Enter  Singer Name : ");
		String singer = scanner.next();
		try {
			preparedStatement.setString(5, singer);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		System.out.print("Enter Song Composer Name : ");
		String composer = scanner.next();
		try {
			preparedStatement.setString(6, composer);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		System.out.print("Enter Song lyrics Creater Name : ");
		String lyrics = scanner.next();
		try {
			preparedStatement.setString(7, lyrics);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(result + " Added Successfull.");

	}

	// View All Songs
	public void viewAllSongs() {
		//		if (playList.isEmpty()) {
//			System.out.println("Sorry Song is Not Available in Playlist.");
//		}
		// else {
		System.out.println("List Of All Songs :-");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weje2?user=root&password=123456");
			preparedStatement = connection.prepareStatement("select * from song");

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1) + " | " + resultSet.getString(2) + " | "
						+ resultSet.getString(3) + " | " + resultSet.getFloat(4) + " | " + resultSet.getString(5)
						+ " | " + resultSet.getString(6) + " | " + resultSet.getString(7));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


	
	}

	
	

	// Play All Songs
	public void playAllSongs() {
		int i=1;
//		if (playList.isEmpty()) {
//			System.out.println("Sorry Song is Not Available in Playlist.");
//		} else {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weje2?user=root&password=123456");
			preparedStatement = connection.prepareStatement("select * from song");

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next() && i>0) {
				preparedStatement = connection.prepareStatement("select name from song where id=?");
				preparedStatement.setInt(1, i);
				resultSet = preparedStatement.executeQuery();
				
//	********************************** Working Here **********************************************************
					System.out.println("Now " + resultSet + " Song Playing....");
					try {
						System.out.println("Zingg...");
						Thread.sleep(2000);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
					i++;
				}
				
			

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
//			for (int i = 0; i < playList.size(); i++) {
//
//				System.out.println("Now " + playList.get(i) + " Song Playing....");
//				try {
//					System.out.println("Zingg...");
//					Thread.sleep(2000);
//				} catch (InterruptedException e) {
//
//					e.printStackTrace();
//				}
//			}
		}
	//}

	// Play Random Songs
	public void playRandomSongs() {
		if (playList.isEmpty()) {
			System.out.println("Sorry Song is Not Available in Playlist.");
		} else {

			// System.out.println(random);
			for (int i = 0; i < 10; i++) {
				int random = (int) (Math.random() * 10);
				if (random < playList.size()) {
					System.out.println("Now " + playList.get(random) + " Song Playing....");
					try {
						System.out.println("Zingg...");
						Thread.sleep(2000);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}

				}

			}

		}

	}

	// Select Song
	public void selectSong(int choise) {

		System.out.println("Now " + playList.get(choise - 1) + " Song Playing....");
		try {
			System.out.println("Zingg...");
			Thread.sleep(3000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

//**************************DELETE**********************
	// Delete Song
	public void deleteSongs(int choise) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weje2?user=root&password=123456");
			preparedStatement = connection.prepareStatement("delete from song where id=?");
			preparedStatement.setInt(1, choise);

			result = preparedStatement.executeUpdate();

			System.out.println(result + " row(s) Deleted");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

//		if (playList.isEmpty()) {
//			System.out.println("Sorry Song is Not Available in Playlist.");
//		} else {
//			if (choise <= playList.size()) {
//				System.out.println(playList.get(choise - 1) + " Delete Successfully,");
//				playList.remove(choise - 1);
//			} else {
//				System.out.println(choise + " is Not Present in List.");
//			}
//
//		}

	}

	// Edit Song
	public void editSong() {
		if (playList.isEmpty()) {
			System.out.println("Sorry Song is Not Available in Playlist.");
		} else {

			Scanner scanner = new Scanner(System.in);
			System.out.print("Which song you want to Edit Choose No. :- ");
			int songNo = scanner.nextInt();
			System.out.println("What you want to Edit :-");
			System.out.println(
					"\n 1 : id\n 2 : Song name\n 3 : Movie/Album name \n 4 : Composer\n 5 : Length \n 6 : Lyrics Creater\n 7 : Back");
			int choise = scanner.nextInt();
			switch (choise) {
			case 1:
				System.out.print("Enter Song Id : ");
				playList.get(songNo - 1).setId(scanner.nextInt());
				break;
			case 2:
				System.out.print("Enter Song Name : ");
				playList.get(songNo - 1).setName(scanner.next());
				break;
			case 3:
				System.out.print("Enter Song Movie/Album : ");
				playList.get(songNo - 1).setMovie(scanner.next());
				break;
			case 4:
				System.out.print("Enter Song Composer Name : ");
				playList.get(songNo - 1).setCompose(scanner.next());
				break;
			case 5:
				System.out.print("Enter Song Length : ");
				playList.get(songNo - 1).setLength(scanner.nextFloat());
				break;
			case 6:
				System.out.print("Enter Song lyrics Creater Name : ");
				playList.get(songNo - 1).setLyrics(scanner.next());
				break;
			case 7:
				break;

			default:
				System.out.println("Invalid choise...");
				break;
			}
			System.out.println("Update Successfull.");

		}
	}

}
