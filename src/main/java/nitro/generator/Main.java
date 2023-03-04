package nitro.generator;

import nitro.generator.Utils.Startup;

import static nitro.generator.Utils.Utils.isNew;

public class Main {
    public static void main(String[] args) {
        if (!isNew()) {
            new Gui();
        } else {
            Startup.init();
        }
    }
}