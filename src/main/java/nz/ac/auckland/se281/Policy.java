package nz.ac.auckland.se281;

public abstract class Policy {
  protected String sum;

  public Policy(String sum) {
    this.sum = sum;
  }

  public abstract int calculateBasePremium(Profile profile);

  public int calculateDiscounts(int numberOfPolicies, Policy policy, Profile profile) {
    /*
        Calculates how much discount the user gets based on how many policies they have.
    */
    float discount = 0;

    if (numberOfPolicies < 2) {
      return policy.calculateBasePremium(profile);
    } else if (numberOfPolicies == 2) {
      discount = (float) ((float) (policy.calculateBasePremium(profile)) * 0.1);
      return (int) ((float) policy.calculateBasePremium(profile) - discount);
    } else if (numberOfPolicies >= 3) {
      discount = (float) ((float) (policy.calculateBasePremium(profile)) * 0.2);
      return (int) ((float) policy.calculateBasePremium(profile) - discount);
    }
    return 0;
  }

  public String getSum() {
    return sum;
  }
}
