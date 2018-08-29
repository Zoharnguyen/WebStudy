package com.localhost.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.localhost.model.ContentOfToday;
import com.localhost.model.IdEmailUser;
import com.localhost.model.ListContent;
import com.localhost.model.ListMember;
import com.localhost.model.Position;
import com.localhost.model.TakeAttendance;
import com.localhost.model.UserAttendance;
import com.localhost.model.UserAttendanceNotFull;
import com.localhost.model.UserDetailForToken;
import com.localhost.model.Users;
import com.localhost.repository.DatabaseUtils;
import com.localhost.service.AutoId;
import com.localhost.service.FuntionToSortDate;
import com.localhost.service.FuntionToSortListContent;
import com.localhost.service.FuntionToSortMemberClass;
import com.localhost.service.GenerateToken;
import com.localhost.service.ParseToken;
import com.localhost.service.ProcessListContent;
import com.localhost.service.ProcessListMember;
import com.localhost.service.ProcessListUser;
import com.localhost.service.ProcessPosition;
import com.localhost.service.ProcessUserAttendance;
import com.localhost.service.RoleOfUser;
import com.localhost.service.Validate;
import com.localhost.service.WorkContentOfUser;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@RestController
	@CrossOrigin(origins = "*")
	public static class TheController {

		public static List<Users> listUser = new ArrayList<Users>();
		public static String token1 = "token";

		// TEST

		@RequestMapping("/test/")
		public static String test() {
			return "Have a good day!";
		}

		// TEST

		// Save user into database
		@ResponseBody
		@RequestMapping("/login/")
		public static void login(@RequestBody Users user) throws IOException {
			String id = String.valueOf(AutoId.autoIdUser());
			Date now = new Date();
			long timeline = now.getTime();
			Users users = new Users(id, user.getEmail(), user.getUserName(), user.getFamilyName(), user.getGivenName(),
					user.getAvatar());
			String role = RoleOfUser.creatRoleForUser(users);
			token1 = GenerateToken
					.generateToken(new UserDetailForToken(users.getEmail(), role, String.valueOf(timeline)));

			URL obj = new URL("http://localhost:8082/token/");
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			if (Validate.checkUserLogin(users)) {
				DatabaseUtils.insertDataUsers(id, users.getEmail(), users.getUserName(), users.getFamilyName(),
						users.getGivenName(), users.getAvatar());
			}
			if (ProcessPosition.checkIntoPosition(users.getEmail()) == false) {
				DatabaseUtils.insertDataPosition(users.getEmail(), role);
			}
		}

		// Return token

		@RequestMapping("/token/")
		public static String getToken() {
			return token1;
		}

		// Return role

		@RequestMapping("/role/{token}/")
		public static String role(@PathVariable String token) {
			return ParseToken.parseToken(token).getRole();
		}

		// Show listContent

		@RequestMapping("/listContent/{token}/")
		public static List<ListContent> listContent(@PathVariable String token) {
			if (Validate.checkToken(token)) {
				return FuntionToSortListContent.sort(DatabaseUtils.readListContent());
			} else
				return null;
		}

		// Get IdEmailUser

		@RequestMapping("/getIdEmailUser/{token}/")
		public static List<IdEmailUser> getIdEmailUser(@PathVariable String token) {
			return DatabaseUtils.readIdEmailUser();
		}

		// Create content

		@ResponseBody
		@RequestMapping("/creatContent/{token}/")
		public static boolean creatContent(@RequestBody ListContent content, @PathVariable String token) {
			if (Validate.checkToken(token)) {
				if (ParseToken.parseToken(token).getRole().equals("captain")) {
					String id = String.valueOf(AutoId.autoIdContent());
					String tags = "Default";
					DatabaseUtils.insertDataContentOfUser(ParseToken.parseToken(token).getEmail(), id);
					DatabaseUtils.insertDataListContent(id, content.getUserName(), content.getAvatar(),
							content.getLevel(), content.getStartDate(), content.getEndDate(), content.getStatus(),
							content.getContentName(), content.getContentDescription(), tags);
					return true;
				} else if (ParseToken.parseToken(token).getRole().equals("member")) {
					String id = String.valueOf(AutoId.autoIdContent());
					String tags = "Default";
					DatabaseUtils.insertDataContentOfUser(ParseToken.parseToken(token).getEmail(), id);
					DatabaseUtils.insertDataListContentPendingItem(id, content.getUserName(), content.getAvatar(),
							content.getLevel(), content.getStartDate(), content.getEndDate(), content.getStatus(),
							content.getContentName(), content.getContentDescription(), tags);
					return true;
				}
			}
			return false;
		}

		// Update Content

		@ResponseBody
		@RequestMapping("/editContent/{token}/")
		public static boolean updateContent(@RequestBody ListContent content, @PathVariable String token) {
			if (Validate.checkToken(token)) {
				if (ParseToken.parseToken(token).getRole().equals("captain") || WorkContentOfUser
						.checkContentOfUser(content.getId(), ParseToken.parseToken(token).getEmail())) {
					content.setTags("default");
					DatabaseUtils.updateDataListContent(content.getId(), content);
					return true;
				}
			}
			return false;
		}

		// Delete Content

		@RequestMapping("/deleteContent/{id}/{token}/")
		public static boolean deleteContent(@PathVariable String id, @PathVariable String token) {
			if (Validate.checkToken(token)) {
				if ("captain".equals(ParseToken.parseToken(token).getRole())
						|| WorkContentOfUser.checkContentOfUser(id, ParseToken.parseToken(token).getEmail())) {
					DatabaseUtils.deleteDataIntoContentOfUser(id);
					DatabaseUtils.deleteContent(id);
					return true;
				}
			}
			return false;
		}

		// Show member class

		@RequestMapping("/memberClass/{token}/")
		public static List<ListMember> memberClass(@PathVariable String token) {
			if (Validate.checkToken(token)) {
				return FuntionToSortMemberClass.sortMember(DatabaseUtils.readListMember());
			} else
				return null;
		}

		// Add member

		@ResponseBody
		@RequestMapping("/addMember/{email}/{token}")
		public static boolean addMember(@PathVariable String email, @PathVariable String token) {
			if (Validate.checkToken(token)) {
				Users user = ProcessListUser.findUserByEmail(email);
				String joinedDate = "10/1/2018";
				UserDetailForToken userDetail = ParseToken.parseToken(token);
				String skype = "Zohar Nguyen";
				ListMember member = new ListMember(user.getUserName(), user.getAvatar(), user.getEmail(), joinedDate,
						userDetail.getRole(), skype);
				if (userDetail.getRole().equals("captain")) {
					member.setRole("member");
					DatabaseUtils.insertDataListMember(member.getUserName(), member.getAvatar(), member.getEmail(),
							member.getJoinedDate(), member.getRole(), skype);
					DatabaseUtils.insertDataPosition(member.getEmail(), member.getRole());
					return true;
				} else if (userDetail.getRole().equals("member")) {
					member.setRole("member");
					DatabaseUtils.insertDataListMemberPendingItem(member.getUserName(), member.getAvatar(),
							member.getEmail(), member.getJoinedDate(), member.getRole(), skype);
					return true;
				}
			}
			return false;
		}

		// Delete member

		@RequestMapping("/deleteMember/{email}/{token}/")
		public static boolean deleteMember(@PathVariable String email, @PathVariable String token) {
			if (Validate.checkToken(token)) {
				if (ParseToken.parseToken(token).getRole().equals("captain")) {
					DatabaseUtils.deleteMember(email);
				}
				return true;
			}
			return false;
		}

		// Get List Email

		@RequestMapping("/getListEmailUser/{token}/")
		public static List<String> getListEmailUser(@PathVariable String token) {
			List<String> list = new ArrayList<>();
			if (Validate.checkToken(token)) {
				List<Users> listUser = DatabaseUtils.readUsers();
				for (Users user : listUser) {
					if (Validate.checkDuplicateEmail(user.getEmail(), list)) {
						list.add(user.getEmail());
					}
				}
			}
			return list;
		}

		// PendingItem

		// PendingItem Api MemberClass

		@RequestMapping("/memberPendingItem/{token}/")
		public static List<ListMember> addUserFromPendingItem(@PathVariable String token) {
			if (Validate.checkToken(token)) {
				return DatabaseUtils.readListMemberPendingItem();
			}
			return null;
		}

		// Delete member pendingItem

		@RequestMapping("/deleteMemberPendingItem/{email}/{token}/")
		public static boolean deleteMemberPendingItem(@PathVariable String email, @PathVariable String token) {
			if (Validate.checkToken(token)) {
				if (ParseToken.parseToken(token).getRole().equals("captain")) {
					DatabaseUtils.deleteMemberPendingItem(email);
					return true;
				}
			}
			return false;
		}

		// Add member into ListMember

		@RequestMapping("/acessMemberFromPendingItem/{email}/{token}/")
		public static boolean acessMemberFromPendingItem(@PathVariable String email, @PathVariable String token) {
			if (Validate.checkToken(token)) {
				if (ParseToken.parseToken(token).getRole().equals("captain")) {
					ListMember member = ProcessListMember.findMemberPendingItemByEmail(email);
					DatabaseUtils.deleteMemberPendingItem(email);
					DatabaseUtils.insertDataListMember(member.getUserName(), member.getAvatar(), member.getEmail(),
							member.getJoinedDate(), member.getRole(), member.getSkype());
					return true;
				}
			}
			return false;
		}

		// PendingItem Api Content

		@ResponseBody
		@RequestMapping("/contentPendingItem/{token}/")
		public static List<ListContent> addContentIntoPendingItem(@PathVariable String token) {
			if (Validate.checkToken(token)) {
				return DatabaseUtils.readListContentPendingItem();
			}
			return null;
		}

		// Delete content PendingItem

		@RequestMapping("/deleteContentPendingItem/{id}/{token}/")
		public static boolean deleteContentPendingItem(@PathVariable String id, @PathVariable String token) {
			if (Validate.checkToken(token)) {
				if (ParseToken.parseToken(token).getRole().equals("captain")) {
					DatabaseUtils.deleteDataIntoContentOfUser(id);
					DatabaseUtils.deleteContentPendingItem(id);
					return true;
				}
			}
			return false;
		}

		// Add content Into PendingItem

		@RequestMapping("/acessContentFromPendingItem/{id}/{token}/")
		public static boolean acessContentFromPendingItem(@PathVariable String id, @PathVariable String token) {
			if (Validate.checkToken(token)) {
				if (ParseToken.parseToken(token).getRole().equals("captain")) {
					ListContent content = ProcessListContent.findContentInPendingItemById(id);
					DatabaseUtils.deleteContentPendingItem(id);
					DatabaseUtils.insertDataListContent(id, content.getUserName(), content.getAvatar(),
							content.getLevel(), content.getStartDate(), content.getEndDate(), content.getStatus(),
							content.getContentName(), content.getContentDescription(), content.getTags());
					return true;
				}
			}
			return false;
		}

		// Set Captain

		@RequestMapping("/setCaptain/{email}/{token}/")
		public static boolean setCaptain(@PathVariable String email, @PathVariable String token) {
			if (Validate.checkToken(token) && ParseToken.parseToken(token).getRole().equals("captain")) {

				Position position11 = new Position(email, "captain");
				ListMember member1 = ProcessListMember.findMemberByEmail(email);
				ListMember member11 = new ListMember(member1.getUserName(), member1.getAvatar(), member1.getEmail(),
						member1.getJoinedDate(), "captain", member1.getSkype());
				DatabaseUtils.updatePosition(position11);
				DatabaseUtils.updateListMember(member11);

//				ListMember member3 = ProcessListMember.findMemberPendingItemByEmail(email);
//				ListMember member31 = new ListMember(member3.getUserName(), member3.getAvatar(), member3.getEmail(),
//						member3.getJoinedDate(), "captain", member3.getSkype());
//				DatabaseUtils.updateListMember(member31);

				Position position22 = new Position(ParseToken.parseToken(token).getEmail(), "member");
				ListMember member2 = ProcessListMember.findMemberByEmail(ParseToken.parseToken(token).getEmail());
				ListMember member22 = new ListMember(member2.getUserName(), member2.getAvatar(), member2.getEmail(),
						member2.getJoinedDate(), "member", member2.getSkype());
				DatabaseUtils.updatePosition(position22);
				DatabaseUtils.updateListMember(member22);
				return true;

			}
			return false;
		}

		// Get New Token

		@RequestMapping("/getNewToken/{token}/")
		public static String getNewToken(@PathVariable String token) {
			if (Validate.checkToken(token)) {
				token1 = GenerateToken.generateToken(new UserDetailForToken(ParseToken.parseToken(token).getEmail(),
						"member", ParseToken.parseToken(token).getTimeline()));
				return token1;
			}
			return null;
		}

		// Attendance

		// Get ListAttendance
		@RequestMapping("/getListAttendance/{idContent}/{token}/")
		public static List<TakeAttendance> getListAttendance(@PathVariable String idContent,
				@PathVariable String token) {
			if (Validate.checkToken(token) && Validate.checkId(idContent)) {
				List<TakeAttendance> list = new ArrayList<TakeAttendance>();
				if (ProcessUserAttendance.checkDuplicateIdContent(idContent)) {
					for (UserAttendance user : DatabaseUtils.readUserAttendance()) {
						if (user.getIdContent().equals(idContent)) {
							TakeAttendance takeA = new TakeAttendance(user.getUserName(), user.getEmail(),
									user.getAvatar(), user.getStatus(), user.getNote());
							list.add(takeA);
						}
					}
				} else {
					String status = "0";
					String note = "";
					for (ListMember member : DatabaseUtils.readListMember()) {
						TakeAttendance takeA = new TakeAttendance(member.getUserName(), member.getEmail(),
								member.getAvatar(), status, note);
						list.add(takeA);
					}
				}
				return list;
			}
			return null;

		}

		// Insert ListAttendance

		@ResponseBody
		@RequestMapping("/insertListAttendance/{token}/")
		public static boolean insertListAttendance(@RequestBody List<UserAttendanceNotFull> list,
				@PathVariable String token) {
			if (Validate.checkToken(token)) {
				for (UserAttendanceNotFull userN : list) {
					ListContent content = ProcessListContent.findContentById(userN.getIdContent());
					if (ProcessUserAttendance.checkDuplicateIdEmailContent(userN.getIdContent(), userN.getEmail()) ) {
						UserAttendance user = new UserAttendance(userN.getIdContent(), content.getStartDate(),
								content.getContentName(), content.getUserName(), content.getAvatar(), userN.getStatus(),
								userN.getNote(), userN.getEmail());
						DatabaseUtils.updateUserAttendance(user);
					} else {
						UserAttendance user = new UserAttendance(userN.getIdContent(), content.getStartDate(),
								content.getContentName(), userN.getUserName(),userN.getAvatar(), userN.getStatus(),
								userN.getNote(), userN.getEmail());
						DatabaseUtils.insertUserAttendance(user.getIdContent(), user.getDate(), user.getTitle(),
								user.getUserName(), user.getAvatar(), user.getStatus(), user.getNote(),
								user.getEmail());
					}
				}
				return true;
			} else
				return false;
		}

		// Return ListDate

		@RequestMapping("/attendance/")
		public static List<String> attendance() {
			List<String> listS = new ArrayList<String>();
			List<ListContent> list = DatabaseUtils.readListContent();
			for (ListContent content : list) {
				if (Validate.checkDuplicateDate(content.getStartDate(), listS) == false) {
					listS.add(content.getStartDate());
				}
			}
			return FuntionToSortDate.sort(listS);
		}

		// Return List title of Date

		@RequestMapping("/statusMemberToday/{date}/{token}/")
		public static List<ContentOfToday> listTitleToday(@PathVariable String date, @PathVariable String token) {
			if (Validate.checkToken(token)) {
				List<ContentOfToday> list = new ArrayList<ContentOfToday>();
				List<UserAttendance> listU = DatabaseUtils.readUserAttendance();
				for (UserAttendance userA : listU) {
					if (userA.getDate().equals(date)) {
						ContentOfToday cot = new ContentOfToday();
						cot.setIdContent(userA.getIdContent());
						cot.setTitle(cot.getTitle());
						list.add(cot);
					}
				}
				return list;
			}
			return null;
		}

		// Return ListMember attendance in today

		@RequestMapping("/statusMemmberToday/{date}/{idContent}/{token}/")
		public static List<UserAttendance> showListMemberAttendance(@PathVariable String date,
				@PathVariable String idContent, @PathVariable String token) {
			if (Validate.checkToken(date)) {
				List<UserAttendance> listUserAttendance = DatabaseUtils.readUserAttendance();
				List<UserAttendance> list = new ArrayList<UserAttendance>();
				for (UserAttendance user : listUserAttendance) {
					if (date.equals(user.getDate()) && idContent.equals(user.getIdContent())) {
						list.add(user);
					}
				}
				return list;
			}
			return null;
		}

	}
}
