public class PopulationData {

    private String country;
    private String region;
    private String subregion;
    private int population2022;
    private int population2023;
    private String change;

    public PopulationData(String country, String region, String subregion,
                          int population2022, int population2023, String change) {
        this.country = country;
        this.region = region;
        this.subregion = subregion;
        this.population2022 = population2022;
        this.population2023 = population2023;
        this.change = change;
    }

    // Getter method for the country
    public String getCountry() {
        return country;
    }

    // Getter method for the region
    public String getRegion() {
        return region;
    }

    // Getter method for the subregion
    public String getSubregion() {
        return subregion;
    }

    // Getter method for the population in 2022
    public int getPopulation2022() {
        return population2022;
    }

    // Getter method for the population in 2023
    public int getPopulation2023() {
        return population2023;
    }

    // Getter method for the population change
    public String getChange() {
        return change;
    }
}
