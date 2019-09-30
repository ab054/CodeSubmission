import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandReader {


    private HashMap<String, Component> available;

    private HashMap<String, Component> installed;

    public CommandReader() {
        this.installed = new HashMap<String, Component>();
        this.available = new HashMap<String, Component>();
    }



    public void read(String input) {
        String[] splitted = input.split(" ");

        logInfo(input);

        if(splitted[0].contentEquals("DEPEND")){
            depend(splitted);
        }

        if(splitted[0].contentEquals("INSTALL")){
            install(splitted[1]);
        }

        if(splitted[0].contentEquals("LIST")){
            list();
        }

        if(splitted[0].contentEquals("REMOVE")){
            remove(splitted);
        }



    }

    private void remove(String[] splitted) {
        String targetComponentName = splitted[1];

        remove(targetComponentName);



    }

    private void remove(String targetComponentName) {
        if(installed.containsKey(targetComponentName)){
            List<Component> inderectDependents = getIndirectDependants(installed.get(targetComponentName));

            boolean isDirectDependency = isDirectDependency(targetComponentName);

            if(isDirectDependency){
                System.out.println(" " + targetComponentName + " is still needed.");
            } else {
                installed.remove(targetComponentName);
                System.out.println(" Removing " + targetComponentName);
            }





        }
    }

    private boolean isDirectDependency(String targetComponentName) {
        boolean result = false;

        for(String each : installed.keySet()){
            if(!each.contentEquals(targetComponentName)){
                for(Component eachComponent : installed.get(each).getDependants()){
                    String dependantsName = eachComponent.getName();
                    if(dependantsName.contentEquals(targetComponentName)){
                        result = true;
                    }
                }

            }
        }

        return result;
    }

    private List<Component> getIndirectDependants(Component root) {
        List<Component> result = new ArrayList<Component>();

        MyQueue queue = new MyQueue();

        root.visited = true;
        queue.enqueue(root);

        while(queue.first != null){
            Component c = queue.dequeue();

            for(Component each : c.getDependants()){
                if(!each.visited){
                    result.add(each);
                    each.visited = true;
                    queue.enqueue(each);
                }
            }
        }

        return result;
    }

    private void list() {
        for(String eachInstalled : installed.keySet()){
            System.out.println(" " + eachInstalled);
        }
    }

    private void install(String componentName) {
        Component componentToInstall = new Component(componentName);

        boolean isInstalled = checkIfInstalled(componentToInstall);
        if(!isInstalled){
            logInfo(" installing " + componentToInstall.getName());
            if(available.containsKey(componentToInstall.getName())){
                Component temp = available.get(componentName);
                installed.put(temp.getName(), temp);
            } else {
                installed.put(componentToInstall.getName(), componentToInstall);
            }

            installDependencies(componentToInstall);
        } else {
            logInfo(" " + componentToInstall.getName() + " is already installed.");
        }


        for(Component eachDependant : componentToInstall.getDependants()){
            installed.put(eachDependant.getName(), componentToInstall);
        }
    }

    private void installDependencies(Component component) {
        if(available.containsKey(component.getName())){
            for(Component each : available.get(component.getName()).getDependants()){
                install(each.getName());
            }
        }
    }

    private boolean checkIfInstalled(Component component) {
        return installed.containsKey(component.getName());
    }

    private void logInfo(String s) {
        System.out.println(s);
    }

    private void depend(String[] splitted) {
        String targetComponentName = splitted[1];
        Component targetComponent = new Component(targetComponentName);

        for(int i=2; i<splitted.length; i ++){
            targetComponent.addDependant(new Component(splitted[i]));
        }

        available.put(targetComponent.getName(), targetComponent);
    }

    public HashMap<String, Component> getInstalled() {
        return installed;
    }

    public Component getIfInstalled(String targetComponentName) {
        Component targetComponent = null;

        for(String each : installed.keySet()){
            if(each.contentEquals(targetComponentName)){
                targetComponent = installed.get(each);
            }
        }

        if(targetComponent != null){
            return targetComponent;
        } else {
            logInfo(" component is not installed");
        }

        return null;
    }
}
