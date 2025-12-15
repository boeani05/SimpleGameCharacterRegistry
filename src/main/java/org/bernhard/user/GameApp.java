package org.bernhard.user;

import org.bernhard.dao.GameCharacterDAO;
import org.bernhard.game.GameCharacter;
import org.bernhard.utils.HibernateUtil;

import java.util.*;

public class GameApp {
    public static void main(String[] args) {
        GameCharacterDAO characterDAO = new GameCharacterDAO();
        Scanner scanner = new Scanner(System.in);
        boolean doesMenuShow = true;
        int menuInput;
        List<GameCharacter> currentCharactersInDb;

        while (doesMenuShow) {
            System.out.println("""
                    === Game-Character Registry ===
                    1. Make a new Character and Save
                    2. List all Characters
                    3. List a specific Character
                    4. Update a specific Character
                    5. Delete a specific Character
                    6. Exit
                    """);

            while (true) {
                try {
                    System.out.print("Enter your choice: ");
                    menuInput = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.err.println("Invalid input. Please enter a number.");
                } finally {
                    scanner.nextLine();
                }
            }

            switch (menuInput) {
                case 1:
                    GameCharacter newGameCharacter;
                    String gameCharacterName;
                    String gameCharacterClass;
                    int gameCharacterHealth;
                    int gameCharacterAtk;
                    int gameCharacterDef;

                    System.out.println("\n=== What's your character's name ===");
                    gameCharacterName = scanner.nextLine();

                    System.out.println("\n=== What's your character's class ===");
                    gameCharacterClass = scanner.nextLine();

                    System.out.println("=== How much health does your character have ===");
                    while (true) {
                        try {
                            gameCharacterHealth = scanner.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.err.println("Not a valid input! Please enter a number.");
                        } finally {
                            scanner.nextLine();
                        }
                    }

                    System.out.println("\n=== How much ATK does your character have ===");
                    while (true) {
                        try {
                            gameCharacterAtk = scanner.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.err.println("Not a valid input! Please enter a number.");
                        } finally {
                            scanner.nextLine();
                        }
                    }

                    System.out.println("\n=== How much DEF does your character have ===");
                    while (true) {
                        try {
                            gameCharacterDef = scanner.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.err.println("Not a valid input! Please enter a number.");
                        } finally {
                            scanner.nextLine();
                        }
                    }

                    newGameCharacter = new GameCharacter(gameCharacterName, gameCharacterClass, gameCharacterHealth, gameCharacterAtk, gameCharacterDef);
                    characterDAO.save(newGameCharacter);
                    System.out.println("\n=== Character '" + newGameCharacter.getName() + "' created successfully with ID: " + newGameCharacter.getId() + " ===");
                    System.out.println("\n--- Current Characters in DB ---");
                    characterDAO.findAll().forEach(System.out::println);
                    break;

                case 2:
                    currentCharactersInDb = characterDAO.findAll();
                    if (!currentCharactersInDb.isEmpty()) {
                        System.out.println("\n--- All Characters in DB ---");
                        currentCharactersInDb.forEach(System.out::println);
                    } else {
                        System.err.println("\nThere are no characters yet!");
                    }
                    break;

                case 3:
                    currentCharactersInDb = characterDAO.findAll();
                    if (!currentCharactersInDb.isEmpty()) {
                        long idOfCharacterToSearchFor;
                        System.out.println("\n--- Enter an ID of the Character to find ---");
                        System.out.print("Enter ID: ");
                        while (true) {
                            try {
                                idOfCharacterToSearchFor = scanner.nextLong();
                                break;
                            } catch (InputMismatchException e) {
                                System.err.println("Not a valid ID! Please enter a number.");
                            } finally {
                                scanner.nextLine();
                            }
                        }
                        GameCharacter foundCharacter = characterDAO.findById(idOfCharacterToSearchFor);
                        if (foundCharacter != null) {
                            System.out.println("\n--- Found Character ---");
                            System.out.println(foundCharacter);
                        } else {
                            System.err.println("\nCharacter with ID " + idOfCharacterToSearchFor + " not found!");
                        }
                    } else {
                        System.err.println("\nThere are no characters yet!");
                    }
                    break;

                case 4:
                    currentCharactersInDb = characterDAO.findAll();
                    if (!currentCharactersInDb.isEmpty()) {
                        long characterIdToUpdate;
                        int whatToUpdate;
                        GameCharacter characterToUpdate;

                        System.out.println("\n=== Which character would you like to update? ===");
                        currentCharactersInDb.forEach(c -> System.out.printf("ID: %d Name: %s%n", c.getId(), c.getName()));
                        System.out.print("Enter ID: ");

                        while (true) {
                            try {
                                characterIdToUpdate = scanner.nextLong();
                                break;
                            } catch (InputMismatchException e) {
                                System.err.println("\nEnter a valid ID! Please enter a number.");
                            } finally {
                                scanner.nextLine();
                            }
                        }

                        characterToUpdate = characterDAO.findById(characterIdToUpdate);
                        if (characterToUpdate == null) {
                            System.err.println("\nCharacter with ID " + characterIdToUpdate + " not found!");
                            break;
                        }

                        System.out.println("""
                                === What would you like to update ===
                                1. Name
                                2. Class
                                3. Health
                                4. ATK
                                5. DEF
                                """);
                        System.out.print("Enter your choice: ");

                        while (true) {
                            try {
                                whatToUpdate = scanner.nextInt();
                                break;
                            } catch (InputMismatchException e) {
                                System.err.println("Not a valid input! Please enter a number.");
                            } finally {
                                scanner.nextLine();
                            }
                        }

                        switch (whatToUpdate) {
                            case 1:
                                String newName;
                                System.out.println("\n=== Enter the new name ===");
                                newName = scanner.nextLine();
                                characterToUpdate.setName(newName);
                                break;
                            case 2:
                                String newClass;
                                System.out.println("\n=== Enter the new class ===");
                                newClass = scanner.nextLine();
                                characterToUpdate.setCharacterClass(newClass);
                                break;
                            case 3:
                                int newHealth;
                                System.out.println("\n=== Enter the new health ===");
                                while (true) {
                                    try {
                                        newHealth = scanner.nextInt();
                                        break;
                                    } catch (InputMismatchException e) {
                                        System.err.println("Enter a valid health value! Please enter a number.");
                                    } finally {
                                        scanner.nextLine();
                                    }
                                }
                                characterToUpdate.setHealth(newHealth);
                                break;
                            case 4:
                                int newAtk;
                                System.out.println("\n=== Enter the new ATK ===");
                                while (true) {
                                    try {
                                        newAtk = scanner.nextInt();
                                        break;
                                    } catch (InputMismatchException e) {
                                        System.err.println("Enter a valid ATK value! Please enter a number.");
                                    } finally {
                                        scanner.nextLine();
                                    }
                                }
                                characterToUpdate.setAttack(newAtk);
                                break;
                            case 5:
                                int newDef;
                                System.out.println("\n=== Enter the new DEF ===");
                                while (true) {
                                    try {
                                        newDef = scanner.nextInt();
                                        break;
                                    } catch (InputMismatchException e) {
                                        System.err.println("Enter a valid DEF value! Please enter a number.");
                                    } finally {
                                        scanner.nextLine();
                                    }
                                }
                                characterToUpdate.setDefense(newDef);
                                break;
                            default:
                                System.err.println("Invalid update option!");
                                break;
                        }
                        characterDAO.update(characterToUpdate);
                        System.out.println("\nCharacter " + characterToUpdate.getName() + " (ID: " + characterToUpdate.getId() + ") updated successfully!");
                    } else {
                        System.err.println("There are no characters yet to update!");
                    }
                    break;

                case 5:
                    currentCharactersInDb = characterDAO.findAll();
                    if (!currentCharactersInDb.isEmpty()) {
                        long idOfCToDelete;
                        String choice;

                        System.out.println("\n=== Enter the ID of the character you would like to delete ===");
                        currentCharactersInDb.forEach(c -> System.out.printf("ID: %d Name: %s%n", c.getId(), c.getName()));
                        System.out.print("Enter ID: ");

                        while (true) {
                            try {
                                idOfCToDelete = scanner.nextLong();
                                break;
                            } catch (InputMismatchException e) {
                                System.err.println("Not a valid ID! Please enter a number.");
                            } finally {
                                scanner.nextLine();
                            }
                        }

                        GameCharacter characterToDelete = characterDAO.findById(idOfCToDelete);
                        if (characterToDelete == null) {
                            System.err.println("\nCharacter with ID " + idOfCToDelete + " not found!");
                            break;
                        }

                        System.out.println("--- Found: " + characterToDelete.getName() + " (ID: " + characterToDelete.getId() + ") ---");
                        System.out.print("Do you really want to delete this character? (Y/N): ");
                        choice = scanner.nextLine();

                        if (choice.trim().toUpperCase(Locale.ROOT).equals("Y")) {
                            characterDAO.delete(idOfCToDelete);
                            System.out.println("\nCharacter '" + characterToDelete.getName() + "' (ID: " + idOfCToDelete + ") deleted successfully!");
                            System.out.println("\n--- Current Characters in DB ---");
                            characterDAO.findAll().forEach(System.out::println);
                        } else {
                            System.out.println("\nDeletion cancelled.");
                        }
                    } else {
                        System.err.println("\nThere are no characters yet to delete!");
                    }
                    break;

                case 6:
                    doesMenuShow = false;
                    System.out.println("\n--- Exiting Game-Character Registry ---");
                    break;

                default:
                    System.err.println("Invalid input! Please choose an option from 1 to 6.");
            }
            System.out.println("\n");
        }
        HibernateUtil.shutdown();
        scanner.close();
    }
}