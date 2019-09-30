import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Tests {


    @Test
    public void test001() throws Exception {
        String input = "DEPEND TELNET TCPIP NETCARD";

        CommandReader reader = new CommandReader();

        reader.read(input);
    }

    @Test
    public void testInstall() throws Exception {
        String input = "INSTALL NETCARD";
        CommandReader reader = new CommandReader();
        reader.read(input);

        input = "INSTALL NETCARD";
        reader.read(input);
    }

    @Test
    public void testList() throws Exception {
        CommandReader reader = new CommandReader();
        String input = "INSTALL NETCARD";
        reader.read(input);

        input = "LIST";
        reader.read(input);
    }

    @Test
    public void testRemove() throws Exception {
        CommandReader reader = new CommandReader();
        String input = "REMOVE TELNET";
        reader.read(input);
    }

    @Test
    public void test002() throws Exception {
        CommandReader reader = new CommandReader();
        String input = "DEPEND TELNET TCPIP NETCARD";
        reader.read(input);

        input = "DEPEND TCPIP NETCARD";
        reader.read(input);

        input = "DEPEND DNS TCPIP NETCARD";
        reader.read(input);

        input = "DEPEND BROWSER TCPIP HTML";
        reader.read(input);

        input = "INSTALL NETCARD";
        reader.read(input);

        input = "INSTALL TELNET";
        reader.read(input);

        input = "INSTALL foo";
        reader.read(input);

        input = "REMOVE NETCARD";
        reader.read(input);

        input = "INSTALL BROWSER";
        reader.read(input);

        input = "INSTALL DNS";
        reader.read(input);

        input = "LIST";
        reader.read(input);

        input = "REMOVE TELNET";
        reader.read(input);

        input = "REMOVE NETCARD";
        reader.read(input);

        input = "REMOVE DNS";
        reader.read(input);

        input = "REMOVE NETCARD";
        reader.read(input);

        input = "INSTALL NETCARD";
        reader.read(input);

        input = "REMOVE TCPIP";
        reader.read(input);

        input = "REMOVE BROWSER";
        reader.read(input);

        input = "REMOVE TCPIP";
        reader.read(input);

        input = "LIST";
        reader.read(input);

    }

    @Test
    public void testContains() throws Exception {
        HashMap<Component, List<Component>> map = new HashMap<Component, List<Component>>();

        Component component = new Component("TCPIP");

        Component component1 = new Component("BROWSER");

        component.addDependant(component1);


        map.put(component, component.getDependants());



        System.out.println(map.containsKey(component));

        map.remove(component);

        System.out.println(map.containsKey(component));
    }
}
