
package BarChart;


public class BarChart {
    private final String TITLE;
    private final int NRGROUPS;
    private String[] categories;
    private int[][] data;
    private String[] groups;
    private char[] symbols;

    public BarChart(String title, String[] categories, String groupName, char symbol, int[] data) {
        this.TITLE = title;
        this.NRGROUPS = 1;
        this.categories = categories;
        this.symbols = new char[]{symbol};
        this.groups = new String[]{groupName};
        this.data = new int[][]{data};
    }

    public BarChart(String title, int nrGroups, String[] categories) {
        this.TITLE = title;
        this.NRGROUPS = nrGroups;
        this.categories = categories;
        this.groups = new String[nrGroups];
        this.data = new int[nrGroups][];
        this.symbols = new char[nrGroups];
    }

    public boolean putGroupData(String groupName, char symbol, int[] data) {
        int eersteLegePlaats = 0;
        for (String group : groups) {
            if (group != null) {
                eersteLegePlaats++;
                break;
            }
        }
        for (String name : groups) {
            if (groupName.equals(name)) {
                return false;
            }
        }
        for (char item : symbols) {
            if (item == symbol) {
                return false;
            }
        }
        this.groups[eersteLegePlaats] = groupName;
        this.symbols[eersteLegePlaats] = symbol;
        this.data[eersteLegePlaats] = data;
        return true;
    }

    public String showData() {
        String outputString = "";
        for (int i = 0; i < groups.length; i++) {
            outputString += "Title :\t" + TITLE + "\nData :\n\t\t" + groups[i] + "\n";
            for (int j = 0; j < categories.length; j++) {
            }
        }
        return outputString;
    }

    public String showLegend() {
        String outputString = "Legend:\t";
        for (int i = 0; i < groups.length; i++) {
            outputString += symbols[i] + "(" + groups[i] + ")";
        }
        return outputString;
    }

    private int maxValueTable(int[][] tabel) {
        int maxVal = 0;
        for (int i = 0; i < tabel.length; i++) {
            for (int j = 0; j < tabel[i].length; j++) {
                if (tabel[i][j] > maxVal) {
                    maxVal = tabel[i][j];
                }
            }
        }
        return maxVal;
    }

    public String makeChart(char orientation, boolean stacked) {
        String outputString = "";
        outputString += "Title :\t" + TITLE + "\n\n";
        if (orientation == "H".charAt(0)) {
            if (!stacked) {
                for (int k = 0; k < data.length; k++) {
                    for (int i = 0; i < data[k].length; i++) {
                        outputString += categories[i] + "\t";
                        int amount = data[k][i];
                        for (int j = 0; j < amount; j++) {
                            outputString += symbols[k];
                        }
                        outputString += "\n\n";
                    }
                }
                outputString += "\n" + showLegend();
            } else {
                //TODO: Stacked horizontal graph maken.
            }
        } else if (orientation == "V".charAt(0)) {
            int maxValue = maxValueTable(this.data);
            char[][] outputArray = new char[maxValue][data.length * data[0].length];
            if (!stacked) {
                int categorie = 0;
                for (int i = 0; i < outputArray.length; i++) {
                    for (int j = 0; j < outputArray[i].length; j++) {
                        if (i < data[categorie][j]) {
                            outputArray[i][j] = symbols[categorie];
                        }
                    }
                }
                //TODO: verticale graph maken.
            } else {
                //Buiten de scope van dit project.
            }
        } else {
            System.out.println("ERROR: ongeldige orientatie.");
        }
        return outputString;
    }

}
