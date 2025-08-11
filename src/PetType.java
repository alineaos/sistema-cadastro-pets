public enum PetType {
    GATO("Gato"),
    CACHORRO("Cachorro");

    private String type;

    PetType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public static PetType selectType(String type){
        for (PetType petType : PetType.values()){
            if(petType.type.equalsIgnoreCase(type)){
                return petType;
            }
        }
        return null;
    }
}
