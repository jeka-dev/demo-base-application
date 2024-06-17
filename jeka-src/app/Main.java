package app;

import dev.jeka.core.tool.JkInjectClasspath;

@JkInjectClasspath("com.sun.jersey:jersey-client:1.19.4")
@JkInjectClasspath("com.google.code.gson:gson:2.11.0")
public class Main {


    public static void main(String[] args) {
        if (args.length == 0 || args[0].equals("--help")) {
            System.out.println("List available versions for a given Java distribution");
            System.out.println("Usage: '[distrib_name]' to list versions of Java distribution");
            System.out.println("or      '--distrib' for displaying available distributions.e");
            System.out.println("or      ' --help' for displaying this help message");
            return;
        }
        if (args[0].equals("--distrib")) {
            FoojayDiscoClient.getDistros().forEach(System.out::println);
        } else {
            FoojayDiscoClient.getVersions(args[0]).forEach(System.out::println);
        }
    }

}
