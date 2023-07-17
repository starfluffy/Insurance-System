package nz.ac.auckland.se281;

public class CarPolicy extends Policy {

  private String makeAndModel;
  private String licensePlate;
  private String mechanicalBreakdown;

  public CarPolicy(String[] options) {
    super(options[0]);
    this.makeAndModel = options[1];
    this.licensePlate = options[2];
    this.mechanicalBreakdown = options[3];
  }

  @Override
  public int calculateBasePremium(Profile profile) {
    /*
        Calculates the base premium of the policy based on their age.
    */
    float sumFloat = Float.parseFloat(sum);
    float ageFloat = Float.parseFloat(profile.getAge());
    float basePremium = 0;

    if (ageFloat < 25) {
      basePremium = (int) (sumFloat * 0.15);
    } else if (ageFloat >= 25) {
      basePremium = (int) (sumFloat * 0.1);
    }

    if (mechanicalBreakdown.equals("yes")) {
      return (int) (basePremium += 80.0);
    } else if (mechanicalBreakdown.equals("no")) {
      return (int) basePremium;
    }

    return -1;
  }

  public String getMakeAndModel() {
    return makeAndModel;
  }
}
