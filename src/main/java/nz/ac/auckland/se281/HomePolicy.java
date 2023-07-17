package nz.ac.auckland.se281;

public class HomePolicy extends Policy {

  private String address;
  private String rental;

  public HomePolicy(String[] options) {
    super(options[0]);
    this.address = options[1];
    this.rental = options[2];
  }

  @Override
  public int calculateBasePremium(Profile profile) {
    /*
        Calculates the base premium of the policy based on the
        sum insured and whether the house is rented out or not.
    */
    float sumFloat = Float.parseFloat(sum);

    if (rental.equals("yes")) {
      return (int) (sumFloat * 0.02);
    } else if (rental.equals("no")) {
      return (int) (sumFloat * 0.01);
    }

    return -1;
  }

  public String getAddress() {
    return address;
  }
}
