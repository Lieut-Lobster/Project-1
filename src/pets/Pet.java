package pets;

public class Pet {
    /* Sets the name and age */
    private String name;
    private int age;

    /*
      Instantiates a new pet
     */
    public Pet(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    /*
      toString Method
     */
    @Override
    public String toString() {
        String Information;
        Information = String.format("%3s%5s%5s%3d%3s\n", "|", name, "|", age, "|");
        return Information;
    }

        /*
          Gets the name
         */

    public String getName() {
        return name;
    }

    /*
      Sets the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
      Gets the age
     */
    public int getAge() {
        return age;
    }

    /*
      Sets the age
     */
    public void setAge(int age) {
        this.age = age;
    }


}

