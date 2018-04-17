package core.model;
public class Personne {
    private String name;
    private String city;

    @Override
    public String toString() {
        return "Personne{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public Personne(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
