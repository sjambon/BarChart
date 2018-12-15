
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
        for (String item : groups) {
            if (item != null) {
                if (eersteLegePlaats == groups.length - 1) {
                    return false;
                } else {
                    eersteLegePlaats++;
                }
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
        if (NRGROUPS == 1) {
            outputString += "Title :\t" + TITLE + "\nData :\n\t\t" + groups[0] + "\n";
        } else {
            outputString += "Title :\t" + TITLE + "\nData :\n\t\t\t";
            for (int i = 0; i < groups.length; i++) {
                outputString += groups[i] + "\t";
            }
            outputString += "\n";
            for (int j = 0; j < data.length; j++) {
                outputString += categories[j] + "\t\t";
                for (int k = 0; k < data[j].length; k++) {
                    outputString += data[j][k] + "\t\t";
                }
                outputString += "\n";
            }
            outputString += "\n\n";
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
            for (int i = 0; i < data[0].length; i++) {
                outputString += categories[i] + "\t\t";
                for (int j = 0; j < data.length; j++) {
                    if (j != 0 && !stacked) {
                        outputString += "\t\t\t";
                    }
                    int amount = data[j][i];
                    for (int k = 0; k < amount; k++) {
                        outputString += symbols[j];
                    }
                    if (!stacked) {
                        outputString += "\n";
                    }
                }
                outputString += "\n";
            }
            outputString += "\n" + showLegend();
        } else if (orientation == "V".charAt(0)) {
            if (!stacked) {
                int maxValue = maxValueTable(this.data);
                for (int i = maxValue; i > 0; i--) {
                    for (int k = 0; k < data[0].length; k++) {
                        for (int j = 0; j < data.length; j++) {
                            if (data[j][k] >= i) {
                                outputString += symbols[j];
                            } else {
                                outputString += " ";
                            }
                            outputString += " ";
                        }
                        outputString += "\t\t";
                    }
                    outputString += "\n";
                }
                for (String categorie : categories) {
                    outputString += categorie + "\t\t";
                }
                outputString += "\n\n\n" + showLegend();
            } else {
                System.out.println("Buiten de scope van dit project.");
            }
        } else {
            System.out.println("ERROR: ongeldige orientatie.");
        }
        return outputString;
    }
}
