public enum PetSex {
    FEMEA("F"),
    MACHO("M");

    private String abbreaviation;

    PetSex(String abbreaviation) {
        this.abbreaviation = abbreaviation;
    }

    public String getAbbreaviation() {
        return abbreaviation;
    }

    public static PetSex selectSex(String abbreaviation){
        for (PetSex petSex : PetSex.values()){
            if(petSex.abbreaviation.equalsIgnoreCase(abbreaviation)){
                return petSex;
            }
        }
        return null;
    }
}
