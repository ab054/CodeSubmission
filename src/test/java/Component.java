import java.util.ArrayList;

public class Component {


    private final String name;

    public ArrayList<Component> dependants;
    public Component next;
    public boolean visited;


    public Component(String targetComponentName) {
        this.name = targetComponentName;

        dependants = new ArrayList<Component>();
    }

    public void addDependant(Component dependant){
        dependants.add(dependant);
    }

    public ArrayList<Component> getDependants() {
        return dependants;
    }

    public String getName() {
        return name;
    }
}
