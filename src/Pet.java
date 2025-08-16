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

    public String getName() {
        return name;
    }

    public PetType getType() {
        return type;
    }

    public PetSex getSex() {
        return sex;
    }

    public Address getAddress() {
        return address;
    }

    public double getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public String getBreed() {
        return breed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("1- ")
                .append(this.getName())
                .append("\n2- ")
                .append(this.getType().getClassification())
                .append("\n3- ")
                .append(this.getSex().getClassification())
                .append("\n4- ")
                .append(this.getAddress())
                .append("\n5- ")
                .append(String.format("%.2f", this.getAge()))
                .append(" anos\n6- ")
                .append(this.getWeight())
                .append("kg\n7- ")
                .append(this.getBreed());
        return sb.toString();
    }
}
