package com.jspider.musicplayer.musicplayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.jspider.musicplayer.operation.SongOperation;
import com.jspider.musicplayer.song.Songs;

public class Player {
	public static Connection connection;
	 public static Statement statement;
	// static ResultSet resultSet;
	//private static int result;
	static boolean loop = true;
	Songs songs = new Songs();
	SongOperation operation = new SongOperation();

	public static void main(String[] args) {
				Player player = new Player();

		while (loop) {
			try {
				player.player();
				System.out.println();

			} catch (Exception e) {
				System.out.println("Wrong Input...");
				player.player();
			}
		}
	}

	// player method
	public void player() {
// *******************************JDBC Conection Start*********************************************		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weje2?user=root&password=123456");
			statement = connection.createStatement();

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
// ********************************JDBC Conection Start********************************************		
		Scanner scanner = new Scanner(System.in);

		System.out.println(
				"Please chose following operation :-\n1 :- Add/Remove Songs\n2 :- Play a Songs\n3 :- Edit a Songs\n4 :- View Songs\n5 :- Exist ");
		int choise = scanner.nextInt();
		switch (choise) {
		case 1:// for Add/Remove songs
			System.out.println("Choose  :-\n 1 :- Add Songs\n 2 :- Remove a Songs\n 3 :- Back ");
			choise = scanner.nextInt();
			switch (choise) {
			case 1:// add Songs
				System.out.print("How many Songs you want you to add :- ");
				choise = scanner.nextInt();
				while (choise > 0) {
					try {
						operation.addSongs();
					} catch (InputMismatchException e) {
						System.out.println("Wrong Input...\nPlease Check Data Type\n");
						break;
					}
					// call add method

					System.out.println();
					choise--;
				}
				operation.viewAllSongs();

				loop = true;
				break;
			case 2:// for Remove Songs
				operation.viewAllSongs();
				// System.out.println(operation.viewAllSongs());
				System.out.print("Which song you want to Delete Please Choose a No. :- ");
				choise = scanner.nextInt();
				operation.deleteSongs(choise);// delete method call by passing choise number
				break;
			case 3:// for Back Home
				player();
				break;
			default:
				System.out.println("Invalid Input ");
				break;
			}

			break;
		case 2:// for Play a Songs

			System.out.println(
					"\nchoose following Operation :- \n1 :- Play All Song\n2 :- Play Random Songs\n3 :- Select Song to Play\n4 :- Back");
			choise = scanner.nextInt();
			switch (choise) {
			case 1:// PlayAll songs

				operation.playAllSongs();

				break;
			case 2:// Play Random Songs
				operation.playRandomSongs();

				break;
			case 3:// select Song to Play
				operation.viewAllSongs();
				System.out.println("Choose Song :- ");
				choise = scanner.nextInt();
				operation.selectSong(choise);
				break;
			case 4:
				player();
				break;

			default:
				System.out.println("Invalid choise...");
				loop = true;
				break;
			}

			break;
		case 3:// Edit a Songs
			operation.viewAllSongs();
			try {
				operation.editSong();

			} catch (InputMismatchException e) {
				System.out.println("Wrong Input..");
			}
			break;
		case 4:
			operation.viewAllSongs();
			break;
		case 5:
			loop = false;
			System.out.println("Thank You.");
			break;
		default:
			System.out.println("Invalid choise...");
			break;
		}

	}

}
