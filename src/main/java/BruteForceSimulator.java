import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

private static long attempts = 0;

public static String hashPassword(String password) {
    try {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
    }
}

public static void bruteForce(String hashToCrack, char[] charset, int maxLength) {
    long totalCombinations = 0;
    for (int i = 1; i <= maxLength; i++) {
        totalCombinations += (long) Math.pow(charset.length, i);
    }
    System.out.println("Possible combinations to try: " + totalCombinations);

    Scanner scanner = new Scanner(System.in);
    System.out.print("Do you want to continue? (y/n): ");
    String answer = scanner.nextLine();
    if (!answer.equalsIgnoreCase("y")) {
        System.out.println("Program is closing.");
        scanner.close();
        return;
    }

    long startTime = System.currentTimeMillis();
    attempts = 0;

    for (int length = 1; length <= maxLength; length++) {
        char[] current = new char[length];
        if (bruteForceHelper(hashToCrack, charset, current, 0)) {
            long endTime = System.currentTimeMillis();
            System.out.println("Password cracked!");
            System.out.println("Password: " + new String(current));
            System.out.println("Attempts: " + attempts);
            System.out.println("Time taken: " + (endTime - startTime) / 1000.0 + " seconds.");
            scanner.close();
            return;
        }
    }
    System.out.println("Password not cracked.");
    System.out.println("Attempts: " + attempts);
    scanner.close();
}

private static boolean bruteForceHelper(String hashToCrack, char[] charset, char[] current, int index) {
    if (index == current.length) {
        attempts++;
        String attempt = new String(current);
        return hashPassword(attempt).equals(hashToCrack);
    }
    for (char c : charset) {
        current[index] = c;
        if (bruteForceHelper(hashToCrack, charset, current, index + 1)) {
            return true;
        }
    }
    return false;
}

public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Give a password to brute force: ");
    String password = scanner.nextLine();
    String hashed = hashPassword(password);

    System.out.print("Please give max length: ");
    int maxLen = scanner.nextInt();
    scanner.nextLine(); // καταναλώνει το newline

    char[] charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-_=+[]{}|;:',.<>?/`~".toCharArray();

    bruteForce(hashed, charset, maxLen);

    scanner.close();
}
