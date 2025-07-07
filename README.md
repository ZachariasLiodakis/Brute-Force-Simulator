<div align="center">

<table>
  <tr>
    <td>
      <img src="BruteForceSimulator.png" alt="Brute Force Attack Simulator" height="150" />
    </td>
    <td style="vertical-align: middle;">
      <h2 style="font-size: 0em; margin: 0;">Brute Force Attack Simulator</h2>
    </td>
  </tr>
</table>

</div>

# 

A simple Java program that simulates a brute force attack to crack a hashed password using SHA-256.  
It tries all possible combinations of characters up to a specified maximum length and reports the cracked password (if found), the number of attempts, and the time taken.

---

## Features

- Takes a cleartext password input from the user.
- Hashes the password with SHA-256.
- Allows the user to specify the maximum length of the password guesses.
- Displays the total possible combinations before starting.
- Prompts the user for confirmation before starting the brute force process.
- Outputs the cracked password, number of attempts, and elapsed time upon success or failure.

---

## Requirements

- Java 11 or higher
- Maven (for build and dependency management)

---

## How to Use

1. Clone or download the repository.

2. Compile and run from a terminal.

```bash
cd /path/to/repo-folder
mvn clean package
java -jar target/BruteForceSimulator-1.0-SNAPSHOT.jar
