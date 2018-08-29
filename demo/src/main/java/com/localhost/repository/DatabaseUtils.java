package com.localhost.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.localhost.model.IdEmailUser;
import com.localhost.model.ListContent;
import com.localhost.model.ListMember;
import com.localhost.model.Position;
import com.localhost.model.UserAttendance;
import com.localhost.model.Users;

public class DatabaseUtils {
	private static List<Users> listUsers = new ArrayList<Users>();
//	private static final Logger logger1 = Logger.getLogger( DataUtils.class);

	public static Connection data() throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
		return con;
	}

//	Read Data Into Database

	public static List<Users> readUsers() {
		try {
			PreparedStatement stmt = data().prepareStatement("select * from users");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Users user = new Users(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6));
				listUsers.add(user);
			}
//			logger1.info("demo"+user.getEmail());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return listUsers;
	}

	public static List<ListContent> readListContent() {
		List<ListContent> listContents = new ArrayList<ListContent>();
		try {
			PreparedStatement stmt = data().prepareStatement("select * from listcontent");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ListContent listContent = new ListContent(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
						rs.getString(9), rs.getString(10));
				listContents.add(listContent);
			}
//			logger1.info("demo"+user.getEmail());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return listContents;
	}

	public static List<ListMember> readListMember() {
		List<ListMember> listMember = new ArrayList<ListMember>();
		try {
			PreparedStatement stmt = data().prepareStatement("select * from memberclass");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ListMember member = new ListMember(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6));
				listMember.add(member);
			}
//			logger1.info("demo"+user.getEmail());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return listMember;
	}

	public static List<ListMember> readListMemberPendingItem() {
		List<ListMember> listMember = new ArrayList<ListMember>();
		try {
			PreparedStatement stmt = data().prepareStatement("select * from pendingitemmemberclass");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ListMember member = new ListMember(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6));
				listMember.add(member);
			}
//			logger1.info("demo"+user.getEmail());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return listMember;
	}

	public static List<Position> readPosition() {
		List<Position> listPosition = new ArrayList<Position>();
		try {
			PreparedStatement stmt = data().prepareStatement("select * from position");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Position position = new Position(rs.getString(1), rs.getString(2));
				listPosition.add(position);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return listPosition;
	}

	public static List<IdEmailUser> readContentOfUser() {
		List<IdEmailUser> list = new ArrayList<IdEmailUser>();
		try {
			PreparedStatement stmt = data().prepareStatement("select * from contentofuser");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				IdEmailUser cou = new IdEmailUser(rs.getString(1), rs.getString(2));
				list.add(cou);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

	public static List<UserAttendance> readUserAttendance() {
		List<UserAttendance> list = new ArrayList<UserAttendance>();
		try {
			PreparedStatement stmt = data().prepareStatement("select * from userattendance");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				UserAttendance ua = new UserAttendance(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8));
				list.add(ua);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

	public static List<ListContent> readListContentPendingItem() {
		List<ListContent> listContents = new ArrayList<ListContent>();
		try {
			PreparedStatement stmt = data().prepareStatement("select * from pendingitemlistcontent");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ListContent listContent = new ListContent(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
						rs.getString(9), rs.getString(10));
				listContents.add(listContent);
			}
//			logger1.info("demo"+user.getEmail());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return listContents;
	}

	public static List<IdEmailUser> readIdEmailUser() {
		List<IdEmailUser> list = new ArrayList<IdEmailUser>();
		try {
			PreparedStatement stmt = data().prepareStatement("select * from contentofuser");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				IdEmailUser idEmailUser = new IdEmailUser(rs.getString(1), rs.getString(2));
				list.add(idEmailUser);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

//	Insert Data Into Database

	public static void insertDataUsers(String id, String email, String username, String familyname, String givenname,
			String avatar) {
		try {
			id = "'" + id + "'";
			email = "'" + email + "'";
			username = "'" + username + "'";
			familyname = "'" + familyname + "'";
			givenname = "'" + givenname + "'";
			avatar = "'" + avatar + "'";
			Statement stmt = data().createStatement();
			stmt.executeUpdate("insert into users values (" + id + "," + email + "," + username + "," + familyname + ","
					+ givenname + "," + avatar + ");");
			data().commit();
			stmt.close();
			data().close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void insertDataListContent(String id, String userName, String avatar, String level, String startDate,
			String endDate, String status, String contentName, String contentDescription, String tags) {
		try {
			id = "'" + id + "'";
			userName = "'" + userName + "'";
			avatar = "'" + avatar + "'";
			level = "'" + level + "'";
			startDate = "'" + startDate + "'";
			endDate = "'" + endDate + "'";
			status = "'" + status + "'";
			contentName = "'" + contentName + "'";
			contentDescription = "'" + contentDescription + "'";
			tags = "'" + tags + "'";
			Statement stmt = data().createStatement();
			stmt.executeUpdate("insert into listcontent values (" + id + "," + userName + "," + avatar + "," + level
					+ "," + startDate + "," + endDate + "," + status + "," + contentName + "," + contentDescription
					+ "," + tags + ");");
			data().commit();
			stmt.close();
			data().close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void insertDataListMember(String userName, String avatar, String email, String joinedDate,
			String role, String skype) {
		try {
			email = "'" + email + "'";
			userName = "'" + userName + "'";
			joinedDate = "'" + joinedDate + "'";
			avatar = "'" + avatar + "'";
			role = "'" + role + "'";
			skype = "'" + skype + "'";
			Statement stmt = data().createStatement();
			stmt.executeUpdate("insert into memberclass values (" + userName + "," + avatar + "," + email + ","
					+ joinedDate + "," + role + "," + skype + ");");
			data().commit();
			stmt.close();
			data().close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void insertDataListMemberPendingItem(String userName, String avatar, String email, String joinedDate,
			String role, String skype) {
		try {
			email = "'" + email + "'";
			userName = "'" + userName + "'";
			joinedDate = "'" + joinedDate + "'";
			avatar = "'" + avatar + "'";
			role = "'" + role + "'";
			skype = "'" + skype + "'";
			Statement stmt = data().createStatement();
			stmt.executeUpdate("insert into pendingitemmemberclass values (" + userName + "," + avatar + "," + email
					+ "," + joinedDate + "," + role + "," + skype + ");");
			data().commit();
			stmt.close();
			data().close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void insertDataPosition(String email, String role) {
		try {
			email = "'" + email + "'";
			role = "'" + role + "'";
			Statement stmt = data().createStatement();
			stmt.executeUpdate("insert into position values (" + email + "," + role + ");");
			data().commit();
			stmt.close();
			data().close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void insertDataContentOfUser(String email, String id) {
		try {
			email = "'" + email + "'";
			id = "'" + id + "'";
			Statement stmt = data().createStatement();
			stmt.executeUpdate("insert into contentofuser values (" + id + "," + email + ");");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void insertUserAttendance(String idContent, String date, String title, String userName, String avatar,
			String status, String note, String email) {
		try {
			email = "'" + email + "'";
			idContent = "'" + idContent + "'";
			title = "'" + title + "'";
			date = "'" + date + "'";
			userName = "'" + userName + "'";
			avatar = "'" + avatar + "'";
			status = "'" + status + "'";
			note = "'" + note + "'";
			Statement stmt = data().createStatement();
			stmt.executeUpdate("insert into userattendance values (" + idContent + "," + date + "," + title + ","
					+ userName + "," + avatar + "," + status + "," + note + "," + email + ");");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void insertDataListContentPendingItem(String id, String userName, String avatar, String level,
			String startDate, String endDate, String status, String contentName, String contentDescription,
			String tags) {
		try {
			id = "'" + id + "'";
			userName = "'" + userName + "'";
			avatar = "'" + avatar + "'";
			level = "'" + level + "'";
			startDate = "'" + startDate + "'";
			endDate = "'" + endDate + "'";
			status = "'" + status + "'";
			contentName = "'" + contentName + "'";
			contentDescription = "'" + contentDescription + "'";
			tags = "'" + tags + "'";
			Statement stmt = data().createStatement();
			stmt.executeUpdate("insert into pendingitemlistcontent values (" + id + "," + userName + "," + avatar + ","
					+ level + "," + startDate + "," + endDate + "," + status + "," + contentName + ","
					+ contentDescription + "," + tags + ");");
			data().commit();
			stmt.close();
			data().close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void updateDataListContent(String id, ListContent listContent) {
		try {
			Statement stmt = data().createStatement();
			id = "'" + id + "'";
			String userName = "'" + listContent.getUserName() + "'";
			String avatar = "'" + listContent.getAvatar() + "'";
			String level = "'" + listContent.getLevel() + "'";
			String startDate = "'" + listContent.getStartDate() + "'";
			String endDate = "'" + listContent.getEndDate() + "'";
			String status = "'" + listContent.getStatus() + "'";
			String contentName = "'" + listContent.getContentName() + "'";
			String contentDescription = "'" + listContent.getContentDescription() + "'";
			String tag = "'" + listContent.getTags() + "'";
			stmt.executeUpdate("update listcontent set username = " + userName + ", avatar = " + avatar
					+ ", startdate = " + startDate + ", enddate = " + endDate + ", level = " + level + ", status = "
					+ status + ", contentname = " + contentName + ", contentdescription = " + contentDescription
					+ ", tags = " + tag + " where id = " + id);
			stmt.close();
			data().close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void updateDataListContentPendingItem(String id, ListContent listContent) {
		try {
			Statement stmt = data().createStatement();
			id = "'" + id + "'";
			String userName = "'" + listContent.getUserName() + "'";
			String avatar = "'" + listContent.getAvatar() + "'";
			String level = "'" + listContent.getLevel() + "'";
			String startDate = "'" + listContent.getStartDate() + "'";
			String endDate = "'" + listContent.getEndDate() + "'";
			String status = "'" + listContent.getStatus() + "'";
			String contentName = "'" + listContent.getContentName() + "'";
			String contentDescription = "'" + listContent.getContentDescription() + "'";
			String tag = "'" + listContent.getTags() + "'";
			stmt.executeUpdate("update pendingitemlistcontent set username = " + userName + ", avatar = " + avatar
					+ ", startdate = " + startDate + ", enddate = " + endDate + ", level = " + level + ", status = "
					+ status + ", contentname = " + contentName + ", contentdescription = " + contentDescription
					+ ", tags = " + tag + " where id = " + id);
			stmt.close();
			data().close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void updatePosition(Position position) {
		try {
			Statement stmt = data().createStatement();
			String email = "'" + position.getEmail() + "'";
			String role = "'" + position.getRole() + "'";
			stmt.executeUpdate("update position set role = " + role + " where email = " + email);
			stmt.close();
			data().close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void updateListMember(ListMember member) {
		try {
			Statement stmt = data().createStatement();
			String email = "'" + member.getEmail() + "'";
			String role = "'" + member.getRole() + "'";
			String avatar = "'" + member.getAvatar() + "'";
			String userName = "'" + member.getUserName() + "'";
			String joinedDate = "'" + member.getJoinedDate() + "'";
			stmt.executeUpdate("update memberclass set role = " + role + ", avatar = " + avatar + ",username = "
					+ userName + ", joineddate = " + joinedDate + " where email = " + email);
			stmt.close();
			data().close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void updateUserAttendance(UserAttendance userAttendance) {
		try {
			Statement stmt = data().createStatement();
			String email = "'" + userAttendance.getEmail() + "'";
			String idContent = "'" + userAttendance.getIdContent() + "'";
			String status = "'" + userAttendance.getStatus() + "'";
			String note = "'" + userAttendance.getNote() + "'";
			stmt.executeUpdate("update userattendance set status = " + status + ", note = " + note +" where idcontent = " + idContent + " and email = " + email);
			stmt.close();
			data().close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
//	Delete Data Into Database

	public static void deleteContent(String id) {
		try {
			id = "'" + id + "'";
			Statement stmt = data().createStatement();
			stmt.executeUpdate("delete from listcontent where id = " + id);
			stmt.close();
			data().close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void deleteMember(String email) {
		try {
			email = "'" + email + "'";
			Statement stmt = data().createStatement();
			stmt.executeUpdate("delete from memberclass where email = " + email);
			stmt.close();
			data().close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void deleteMemberPendingItem(String email) {
		try {
			email = "'" + email + "'";
			Statement stmt = data().createStatement();
			stmt.executeUpdate("delete from pendingitemmemberclass where email = " + email);
			stmt.close();
			data().close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void deleteDataIntoContentOfUser(String id) {
		try {
			id = "'" + id + "'";
			Statement stmt = data().createStatement();
			stmt.executeUpdate("delete from contentofuser where id = " + id + ";");
			stmt.close();
			data().close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void deleteUserAttendance(String idContent, String date, String userName) {
		try {
			idContent = "'" + idContent + "'";
			date = "'" + date + "'";
			Statement stmt = data().createStatement();
			stmt.executeUpdate("delete from userattendance where date = " + date + " and idContent = " + idContent
					+ " and userName = " + userName + ";");
			stmt.close();
			data().close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void deleteContentPendingItem(String id) {
		try {
			id = "'" + id + "'";
			Statement stmt = data().createStatement();
			stmt.executeUpdate("delete from pendingitemlistcontent where id = " + id);
			stmt.close();
			data().close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
