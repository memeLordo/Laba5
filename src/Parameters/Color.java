package Parameters;

public enum Color {
    GREEN("Зелёный"),
    YELLOW("Жёлтый"),
    ORANGE("Оранжевый"),
    WHITE("Белый");
    private final String name;

    public String getName() {
        return name;
    }

    Color(String text) {
        this.name=text;
    }
    public static Color find(String text){
        Color[] x =Color.values();
        Color colorX=null;
        for(Color color:x){
            colorX=color.getName().equals(text)?color:colorX;
        }
        return colorX;
    }

}
