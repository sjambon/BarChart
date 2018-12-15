package BarChart;

/**
 * Project Java Fundamentals: DemoBarChart klasse die de BarChart klasse test.
 *
 * @author Steven Jambon
 * @version december 2018
 */
public class DemoBarChart {

    /**
     * Het hoofdprogramma die de BarChart klasse volledig uittest.
     *
     * @param args de input voor het programma (hier niet van toepassing).
     * @see BarChart
     */
    public static void main(String[] args) {
        // Scenario 1 : 1 groep
        BarChart chart1group = new BarChart("BarChart with only 1 group", new String[]{"Boys", "Girls", "Adults"},
                "Blue", 'B', new int[]{12, 5, 8});

        System.out.println("\nTesting the show methods : ");
        System.out.println(chart1group.showData());
        System.out.println(chart1group.showLegend());


        // Scenario 2 : staafdiagram 1 groep
        System.out.println(chart1group.makeChart('H', false));

        //Scenario 3 : kolomdiagram 1 groep
        System.out.println(chart1group.makeChart('V', false));

        //Scenario 4 : meerdere groepen
        BarChart chart3groups = new BarChart("BarChart with 3 groups", 3, new String[]{"Boys", "Girls", "Adults"});

        System.out.println("\nTesting the putGroupData method : ");
        System.out.println(chart3groups.putGroupData("Pink", 'P', new int[]{19, 7, 14}));//ok
        System.out.println(chart3groups.putGroupData("Green", 'G', new int[]{15, 12, 9}));//ok
        System.out.println(chart3groups.putGroupData("Green", 'Y', new int[]{15, 12, 9})); //nok groep bestaat al
        System.out.println(chart3groups.putGroupData("Yellow", 'G', new int[]{15, 12, 9}));//nok symbool bestaat al
        System.out.println(chart3groups.putGroupData("Blue", 'B', new int[]{8, 12, 20}));//ok
        System.out.println(chart3groups.putGroupData("Orange", 'O', new int[]{15, 12, 9}));//nok max 3 groepen

        System.out.println("\nTesting the show methods : ");
        System.out.println(chart3groups.showData());
        System.out.println(chart3groups.showLegend());

        // Scenario 5 gegroepeerd staafdiagram
        System.out.println(chart3groups.makeChart('H', false));

        // Scenario 6 gestapeld staafdiagram
        System.out.println(chart3groups.makeChart('H', true));

        // Scenario 7 gegroepeerd kolomdiagram
        System.out.println(chart3groups.makeChart('V', false));


    }

}
