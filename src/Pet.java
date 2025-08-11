public class Pet {
    private String name;
    private PetType type;
    private PetSex sex;
    private Address address;
    private double age;
    private double weight;
    private String breed;


    public Pet(String name, PetType type, PetSex sex, Address address, double age, double weight, String breed) {
        this.name = name;
        this.type = type;
        this.sex = sex;
        this.address = address;
        this.age = age;
        this.weight = weight;
        this.breed = breed;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", sex=" + sex +
                ", address=" + address +
                ", age=" + age +
                ", weight=" + weight +
                ", breed='" + breed + '\'' +
                '}';
    }
}
