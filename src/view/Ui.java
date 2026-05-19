package view;

import controller.UserController;
import model.CreateUserDto;
import model.dto.UpdateRequestDto;
import model.dto.UserResponseDto;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.Table;
import util.APIResponseTemplate;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private final static UserController userController = new UserController();
    private final static Scanner scanner = new Scanner(System.in);

    private static void thumbnail() {
        System.out.println("""
       =============== User Management System ================
       1. Create User
       2. Search User by UUID
       3. Search User by name
       4. Delete User by UUID
       5. Update User by UUID
       6. List all Users
       0. Exit""");
    }

    private static int insertOption() {
//        int option = 0 ;
        do {
            try{
                System.out.print("[+] Insert your option: ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please input number");
            }
        }while (true);

//        return option;
    }

    public static void printTable(UserResponseDto user) {
        if (user == null) {
            System.out.println("No user found.");
            return;
        }
        printTable(Collections.singletonList(user));
    }

    public static void printTable(List<UserResponseDto> users) {
        if (users == null || users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }
        Table table = new Table(4, BorderStyle.UNICODE_BOX_HEAVY_BORDER_WIDE);
        for (String h : new String[]{"UUID", "NAME", "EMAIL", "PROFILE"}) {
            table.addCell(h);
        }
        // Rows
        for (UserResponseDto user : users) {
            table.addCell(user.uuid());
            table.addCell(user.name());
            table.addCell(user.email());
            table.addCell(user.profile());
        }
        System.out.println(table.render());
    }

    public static void getRendered() {
        while (true) {
            thumbnail();
            System.out.println("--");
            switch (insertOption()) {
                case 1 -> {
                    System.out.println("Create User");
                    System.out.print("[+] Insert name: ");
                    String name = scanner.nextLine();
                    System.out.print("[+] Insert email: ");
                    String email = scanner.nextLine();
                    System.out.print("[+] Insert password: ");
                    String password = scanner.nextLine();
                    try {
                        CreateUserDto createUserDto = new CreateUserDto(name, email, password);
                        APIResponseTemplate<UserResponseDto> createdUser = userController.createUser(createUserDto);
                        printTable(createdUser.data());
                    } catch (RuntimeException e) {
                        System.out.println("[!] " + e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.println("Search User By UUID");
                    System.out.print("Enter User UUID: ");
                    String uuid = scanner.nextLine();

                    try {
                        APIResponseTemplate<UserResponseDto> searchByUuid = userController.searchUserByUuid(uuid);
                        printTable(searchByUuid.data());
                    } catch (RuntimeException e) {
                        System.out.println("[!] " + e.getMessage());
                    }

                }
                case 3 -> {
                    System.out.println("Search User By Name");
                    System.out.print("Enter User Name: ");
                    String name = scanner.nextLine();
                    try {
                        APIResponseTemplate<List<UserResponseDto>> searchByName = userController.searchByName(name);
                        printTable(searchByName.data());
                    } catch (RuntimeException e) {
                        System.out.println("[!] " + e.getMessage());
                    }


                }
                case 4 -> {
                    System.out.println("Delete User");
                    System.out.print("Enter User UUID: ");
                    String uuid = scanner.nextLine();
                    try {
                        APIResponseTemplate<Integer> deleteResult = userController.deleteUser(uuid);
                        if (deleteResult.data() != null && deleteResult.data() > 0) {
                            System.out.println("User deleted successfully.");
                        } else {
                            System.out.println("Deletion failed.");
                        }
                    } catch (RuntimeException e) {
                        System.out.println("[!] " + e.getMessage());
                    }
                }
                case 5 -> {
                    System.out.println("Update User");
                    System.out.print("[+] Enter UUID: ");
                    String uuid = scanner.nextLine();
                    System.out.print("[+] Insert new name: ");
                    String name = scanner.nextLine();
                    System.out.print("[+] Insert new email: ");
                    String email = scanner.nextLine();
                    System.out.print("[+] Insert new password: ");
                    String password = scanner.nextLine();
                    System.out.print("[+] Insert new profile: ");
                    String pf = scanner.nextLine();
                    try {
                        UpdateRequestDto updateRequestDto = new UpdateRequestDto(name, email, password, pf);
                        APIResponseTemplate<UserResponseDto> updatedUser = userController.updateUser(uuid, updateRequestDto);
                        printTable(updatedUser.data());
                    } catch (RuntimeException e) {
                        System.out.println("[!] " + e.getMessage());
                    }
                }
                case 6 -> {
                    try {
                        APIResponseTemplate<List<UserResponseDto>> allUsers = userController.getAllUsers();
                        printTable(allUsers.data());
                    } catch (RuntimeException e) {
                        System.out.println("[!] " + e.getMessage());
                    }
                }
                case 0 -> {
                    System.out.println("System closed...");
                    try { Thread.sleep(100); } catch (Exception ignore) {}
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
