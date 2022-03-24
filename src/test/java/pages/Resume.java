package pages;

/**
 * A class for describing candidate attributes from the hh resume page
 */
public class Resume {
    /**
     * Attributes for the candidate
     */
    private final String gender;
    private final String city;
    private final int age;
    private final boolean isNumberConfirmed;
    private final boolean isReadyToRelocate;


    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public boolean isNumberConfirmed() {
        return isNumberConfirmed;
    }

    public boolean isReadyToRelocate() {
        return isReadyToRelocate;
    }

    public String getCity() {
        return city;
    }

    /**
     * Class constructor
     * @param gender gender of the candidate
     * @param city candidate city
     * @param age age of candidate
     * @param isNumberConfirmed is the number confirmed
     * @param isReadyToRelocate is the candidate ready to move
     */
    public Resume(String gender, String city, int age, boolean isNumberConfirmed, boolean isReadyToRelocate) {
        this.gender = gender;
        this.city = city;
        this.age = age;
        this.isNumberConfirmed = isNumberConfirmed;
        this.isReadyToRelocate = isReadyToRelocate;
    }
}