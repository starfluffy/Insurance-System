package nz.ac.auckland.se281;

public class LifePolicy extends Policy {

  public LifePolicy(String[] options) {
    super(options[0]);
  }

  @Override
  public int calculateBasePremium(Profile profile) {
    /*
        Calculates the base premium of the policy based on their age.
    */
    float sumFloat = Float.parseFloat(sum);
    float ageFloat = Float.parseFloat(profile.getAge());

    return (int) (sumFloat * (1.0 + (ageFloat / 100.0)) / 100.0);
  }

  public boolean checkAge(Profile profile) {
    /*
        Checks whether the user is over 100 years old.
    */
    int intAge = Integer.parseInt(profile.getAge());

    if (intAge > 100) {
      return false;
    }

    return true;
  }
}
