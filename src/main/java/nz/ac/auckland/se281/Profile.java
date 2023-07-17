package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Profile {
  // Creates the profile of each user, including the checks for invalid names or ages

  private String username;
  private String age;

  public Profile(String username, String age) {
    /*
    Contructor for the class
     *
     * Parameters:
     *      username (String): a string, for example "John"
     *      age (string): a string, for example "23"
     *
     */
    this.username = username;
    this.age = age;
  }

  public Profile() {
    this.username = "";
    this.age = "0";
  }

  public String getUsername() {
    return username;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  boolean isPositiveInteger() {
    /*
        Returns true or false based on whether the input, age, is a positive integer or not
    */
    if (!this.isInteger(age)) {
      return false;
    }

    int ageInt = Integer.parseInt(age);
    if (ageInt < 0) {
      return false;
    }
    return true;
  }

  boolean isInteger(String age) {
    /*
        Returns true or false based on whether the input, age, is an integer or not
    */
    for (int i = 0; i < this.age.length(); i++) {
      if (!Character.isDigit(age.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  public boolean isUsernameUnique(ArrayList<Profile> profiles) {
    /*
        Returns true or false based on whether the input, username, is unique or not
    */
    for (int i = 0; i < profiles.size(); i++) {
      Profile currentProfile = profiles.get(i);
      if (this.username.equals(currentProfile.getUsername())) {
        return false;
      }
    }
    return true;
  }

  public static String formatUsername(String userName) {
    /*
        Formats the username to be capitalized
    */
    String firstLetter = userName.substring(0, 1).toUpperCase();
    String restOfUsername = userName.substring(1, userName.length()).toLowerCase();
    String formattedUsername = firstLetter + restOfUsername;

    return formattedUsername;
  }

  public boolean isValidLength() {
    /*
        Returns true or false based on whether the input, username, is a valid length
    */
    int usernameLength = this.username.length();
    if (usernameLength < 3) {
      return false;
    }
    return true;
  }

  public static int getIndex(String username, ArrayList<Profile> profiles) {
    /*
        Returns the index of the profile with the desired username
    */
    for (int i = 0; i < profiles.size(); i++) {
      Profile currentProfile = profiles.get(i);
      if (username.equals(currentProfile.getUsername())) {
        return i;
      }
    }
    return -1;
  }
}
