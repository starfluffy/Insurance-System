package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.HashMap;
import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {

  private ArrayList<Profile> profiles = new ArrayList<>();
  private Boolean isLoading = false;
  private Profile loadedProfile;
  private HashMap<String, ArrayList<Policy>> policies = new HashMap<>();

  public InsuranceSystem() {
    // Only this constructor can be used (if you need to initialise fields).
  }

  public void printDatabase() {
    /*
        Prints the database which contains all the profiles and their policies.
        and also states how many profiles there are.
    */

    int numberOfProfiles = profiles.size();
    int profileNumber = 1;
    int numberOfPolicies;
    int totalPrice;

    if (numberOfProfiles == 0) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("0", "s", ".");
    } else if (numberOfProfiles == 1) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("1", "", ":");

      Profile profile = profiles.get(0);
      String currentName = profile.getUsername();
      String currentAge = profile.getAge();
      totalPrice = 0;
      ArrayList<Integer> policyPrices = new ArrayList<>();

      if (!policies.containsKey(currentName)) {
        numberOfPolicies = 0;
      } else {
        numberOfPolicies = policies.get(currentName).size();

        for (Policy policy : policies.get(currentName)) {
          int discountedPrice = policy.calculateDiscounts(numberOfPolicies, policy, profile);
          totalPrice += discountedPrice;
          policyPrices.add(discountedPrice);
        }
      }

      if (isLoading && loadedProfile.getUsername().equals(currentName)) {
        if (numberOfPolicies == 1) {
          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
              "*** ",
              "1",
              currentName,
              currentAge,
              Integer.toString(numberOfPolicies),
              "y",
              Integer.toString(totalPrice));
        } else {
          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
              "*** ",
              "1",
              currentName,
              currentAge,
              Integer.toString(numberOfPolicies),
              "ies",
              Integer.toString(totalPrice));
        }
      } else {
        if (numberOfPolicies == 1) {
          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
              "",
              "1",
              currentName,
              currentAge,
              Integer.toString(numberOfPolicies),
              "y",
              Integer.toString(totalPrice));
        } else {
          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
              "",
              "1",
              currentName,
              currentAge,
              Integer.toString(numberOfPolicies),
              "ies",
              Integer.toString(totalPrice));
        }

        if (numberOfPolicies > 0) {
          int k = 0;
          for (Policy policy : policies.get(currentName)) {
            if (policy instanceof HomePolicy) {
              MessageCli.PRINT_DB_HOME_POLICY.printMessage(
                  ((HomePolicy) policy).getAddress(),
                  policy.getSum(),
                  Integer.toString(policy.calculateBasePremium(profile)),
                  Integer.toString(policyPrices.get(k)));
            } else if (policy instanceof CarPolicy) {
              MessageCli.PRINT_DB_CAR_POLICY.printMessage(
                  ((CarPolicy) policy).getMakeAndModel(),
                  policy.getSum(),
                  Integer.toString(policy.calculateBasePremium(profile)),
                  Integer.toString(policyPrices.get(k)));
            } else if (policy instanceof LifePolicy) {
              MessageCli.PRINT_DB_LIFE_POLICY.printMessage(
                  policy.getSum(),
                  Integer.toString(policy.calculateBasePremium(profile)),
                  Integer.toString(policyPrices.get(k)));
            }
            k++;
          }
        }
      }
    } else if (numberOfProfiles > 1) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(Integer.toString(numberOfProfiles), "s", ":");

      for (int i = 0; i < numberOfProfiles; i++) {
        Profile currentProfile = profiles.get(i);
        String currentName = currentProfile.getUsername();
        String currentAge = currentProfile.getAge();
        totalPrice = 0;
        ArrayList<Integer> policyPrices = new ArrayList<>();

        if (!policies.containsKey(currentName)) {
          numberOfPolicies = 0;
        } else {
          numberOfPolicies = policies.get(currentName).size();

          for (Policy policy : policies.get(currentName)) {
            int discountedPrice =
                policy.calculateDiscounts(numberOfPolicies, policy, currentProfile);
            totalPrice += discountedPrice;
            policyPrices.add(discountedPrice);
          }
        }

        if (isLoading && loadedProfile.getUsername().equals(currentName)) {
          if (numberOfPolicies == 1) {
            MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
                "*** ",
                Integer.toString(profileNumber),
                currentName,
                currentAge,
                Integer.toString(numberOfPolicies),
                "y",
                Integer.toString(totalPrice));
          } else {
            MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
                "*** ",
                Integer.toString(profileNumber),
                currentName,
                currentAge,
                Integer.toString(numberOfPolicies),
                "ies",
                Integer.toString(totalPrice));
          }
        } else {
          if (numberOfPolicies == 1) {
            MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
                "",
                Integer.toString(profileNumber),
                currentName,
                currentAge,
                Integer.toString(numberOfPolicies),
                "y",
                Integer.toString(totalPrice));
          } else {
            MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
                "",
                Integer.toString(profileNumber),
                currentName,
                currentAge,
                Integer.toString(numberOfPolicies),
                "ies",
                Integer.toString(totalPrice));
          }
        }
        if (numberOfPolicies > 0) {
          int k = 0;
          for (Policy policy : policies.get(currentName)) {
            if (policy instanceof HomePolicy) {
              MessageCli.PRINT_DB_HOME_POLICY.printMessage(
                  ((HomePolicy) policy).getAddress(),
                  policy.getSum(),
                  Integer.toString(policy.calculateBasePremium(currentProfile)),
                  Integer.toString(policyPrices.get(k)));
            } else if (policy instanceof CarPolicy) {
              MessageCli.PRINT_DB_CAR_POLICY.printMessage(
                  ((CarPolicy) policy).getMakeAndModel(),
                  policy.getSum(),
                  Integer.toString(policy.calculateBasePremium(currentProfile)),
                  Integer.toString(policyPrices.get(k)));
            } else if (policy instanceof LifePolicy) {
              MessageCli.PRINT_DB_LIFE_POLICY.printMessage(
                  policy.getSum(),
                  Integer.toString(policy.calculateBasePremium(currentProfile)),
                  Integer.toString(policyPrices.get(k)));
            }
            k++;
          }
        }
        profileNumber++;
      }

    } else {
      System.out.println("not valid");
    }
  }

  public void createNewProfile(String userName, String age) {
    /*
        Checks how many profiles are in the arraylist and prints to the console
        accordingly.
        Uses a for loops for printing multiple profiles
    */

    if (isLoading) {
      MessageCli.CANNOT_CREATE_WHILE_LOADED.printMessage(loadedProfile.getUsername());
      return;
    }

    String formattedUsername = Profile.formatUsername(userName);
    Profile profile = new Profile(formattedUsername, age);

    boolean isPositiveInt = profile.isPositiveInteger();
    boolean isUnique = profile.isUsernameUnique(profiles);
    boolean isValidLength = profile.isValidLength();

    if (!isValidLength) {
      MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(profile.getUsername());
    }
    if (!isUnique) {
      MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(profile.getUsername());
    }
    if (!isPositiveInt) {
      MessageCli.INVALID_AGE.printMessage(profile.getAge(), profile.getUsername());
    }

    if (isPositiveInt && isUnique && isValidLength) {
      profiles.add(profile);
      MessageCli.PROFILE_CREATED.printMessage(profile.getUsername(), profile.getAge());
    }
  }

  public void loadProfile(String userName) {
    /*
        Loads the profile with a given username.
    */
    String formattedUsername = Profile.formatUsername(userName);
    int index = Profile.getIndex(formattedUsername, profiles);

    if (index == -1) {
      MessageCli.NO_PROFILE_FOUND_TO_LOAD.printMessage(formattedUsername);
      return;
    }

    loadedProfile = profiles.get(index);
    isLoading = true;
    MessageCli.PROFILE_LOADED.printMessage(loadedProfile.getUsername());
  }

  public void unloadProfile() {
    if (!isLoading) {
      MessageCli.NO_PROFILE_LOADED.printMessage();
      return;
    }

    MessageCli.PROFILE_UNLOADED.printMessage(loadedProfile.getUsername());
    isLoading = false;
    loadedProfile = null;
  }

  public void deleteProfile(String userName) {
    /*
        Deletes the profile with a given username.
    */
    String formattedUsername = Profile.formatUsername(userName);

    if (loadedProfile != null && formattedUsername.equals(loadedProfile.getUsername())) {
      MessageCli.CANNOT_DELETE_PROFILE_WHILE_LOADED.printMessage(formattedUsername);
      return;
    }

    int index = Profile.getIndex(formattedUsername, profiles);
    if (index == -1) {
      MessageCli.NO_PROFILE_FOUND_TO_DELETE.printMessage(formattedUsername);
      return;
    }
    MessageCli.PROFILE_DELETED.printMessage(formattedUsername);
    profiles.remove(index);
  }

  public void createPolicy(PolicyType type, String[] options) {
    /*
        Creates a policy based on the type of policy and the options given.
    */
    if (!isLoading) {
      MessageCli.NO_PROFILE_FOUND_TO_CREATE_POLICY.printMessage();
      return;
    }
    if (!policies.containsKey(loadedProfile.getUsername())) {
      policies.put(loadedProfile.getUsername(), new ArrayList<Policy>());
    }

    switch (type) {
      case HOME:
        HomePolicy homePolicy = new HomePolicy(options);
        MessageCli.NEW_POLICY_CREATED.printMessage("home", loadedProfile.getUsername());
        policies.get(loadedProfile.getUsername()).add(homePolicy);
        break;
      case CAR:
        CarPolicy carPolicy = new CarPolicy(options);
        MessageCli.NEW_POLICY_CREATED.printMessage("car", loadedProfile.getUsername());
        policies.get(loadedProfile.getUsername()).add(carPolicy);
        break;
      case LIFE:
        LifePolicy lifePolicy = new LifePolicy(options);

        if (!lifePolicy.checkAge(loadedProfile)) {
          MessageCli.OVER_AGE_LIMIT_LIFE_POLICY.printMessage(loadedProfile.getUsername());
          return;
        }
        for (Policy i : policies.get(loadedProfile.getUsername())) {
          if (i instanceof LifePolicy) {
            MessageCli.ALREADY_HAS_LIFE_POLICY.printMessage(loadedProfile.getUsername());
            return;
          }
        }
        MessageCli.NEW_POLICY_CREATED.printMessage("life", loadedProfile.getUsername());
        policies.get(loadedProfile.getUsername()).add(lifePolicy);
        break;
      default:
        break;
    }
  }
}
