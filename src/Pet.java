public class Pet {
    private String name;
    private PetType type;
    private PetSex sex;
    private Address address;
    private Double age;
    private Double weight;
    private String breed;


    public Pet(String name, PetType type, PetSex sex, Address address, Double age, Double weight, String breed) {
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

    public Double getAge() {
        return age;
    }

    public Double getWeight() {
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
                .append(this.getAge() == null ? Validate.NAO_INFORMADO : String.format("%.2f anos", this.getAge()))
                .append("\n6- ")
                .append(this.getWeight() == null ? Validate.NAO_INFORMADO : String.format("%.2fkg", this.getWeight()))
                .append("\n7- ")
                .append(this.getBreed());
        return sb.toString();
    }
}
