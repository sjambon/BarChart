
package BarChart;

/**
 * Project Java Fundamentals: BarChart klasse die een tabel bestaande uit rijen en kolommen kan visualiseren.
 *
 * @author Steven Jambon
 * @version december 2018
 */

public class BarChart {

    /**
     * In dit veld wordt de titel van de tabel opgeslagen.
     */
    private final String TITLE;

    /**
     * In dit veld wordt het maximum aantal groepen opgeslagen.
     */
    private final int NRGROUPS;

    /**
     * In dit veld worden alle rijen (categorieën) bijgehouden in een array van Strings.
     */
    private String[] categories;

    /**
     * In dit veld wordt de data van alle groepen bijgehouden in een 2dim array van integer getallen.
     */
    private int[][] data;

    /**
     * In dit veld worden de namen van alle kolommen (groepen) bijgehouden in een array van Strings.
     */
    private String[] groups;

    /**
     * In dit veld worden alle symbolen waarmee een groep wordt aangeduid bijgehouden in een array van chars.
     */
    private char[] symbols;

    /**
     * Constructor methode voor 1 groep.
     *
     * @param title      de titel van de tabel.
     * @param categories de namen van de rijen (categorieën).
     * @param groupName  de naam van de kolom (groep).
     * @param symbol     het symbool van de groep.
     * @param data       de data die in de tabel zit.
     */
    public BarChart(String title, String[] categories, String groupName, char symbol, int[] data) {
        this.TITLE = title;
        this.NRGROUPS = 1;
        this.categories = categories;
        this.symbols = new char[]{symbol};
        this.groups = new String[]{groupName};
        this.data = new int[][]{data};
    }

    /**
     * Constructor methode voor meerdere groepen
     *
     * @param title      de titel van de tabel.
     * @param nrGroups   het aantal kolommen (groepen) er zijn.
     * @param categories het aantal rijen (categorieën) er zijn.
     */
    public BarChart(String title, int nrGroups, String[] categories) {
        this.TITLE = title;
        this.NRGROUPS = nrGroups;
        this.categories = categories;
        this.groups = new String[nrGroups];
        this.data = new int[nrGroups][];
        this.symbols = new char[nrGroups];
    }

    /**
     * Voegt data toe aan de tabel.
     *
     * @param groupName de naam van de kolom (groep).
     * @param symbol    het symbool van de groep.
     * @param data      de data die in die kolom zit.
     * @return Booleanse waarde die zegt of de data al dan niet is toegevoegd.
     */
    public boolean putGroupData(String groupName, char symbol, int[] data) {
        int eersteLegePlaats = -1;
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] == null) {
                eersteLegePlaats = i;
                break;
            }
        }
        for (int i = 0; i < groups.length; i++) {
            if (groupName.equals(groups[i]) || symbol == symbols[i]) {
                return false;
            }
        }
        if (eersteLegePlaats == -1) {
            return false;
        }
        this.groups[eersteLegePlaats] = groupName;
        this.symbols[eersteLegePlaats] = symbol;
        this.data[eersteLegePlaats] = data;
        return true;
    }

    /**
     * Toont de data die in de tabel zit.
     *
     * @return De data die in de tabel zit.
     */
    public String showData() {
        String outputString = "";
        outputString += "Title :\t" + TITLE + "\nData :\n\t\t\t";
        for (String group : groups) {
            outputString += group + "\t";
        }
        outputString += "\n";
        for (int k = 0; k < data[0].length; k++) {
            outputString += categories[k] + "\t\t";
            for (int[] datum : data) {
                outputString += datum[k] + "\t\t";
            }
            outputString += "\n";
        }
        outputString += "\n";
        return outputString;
    }

    /**
     * Toont de legende van de tabel
     *
     * @return De legende van de tabel.
     */
    public String showLegend() {
        String outputString = "Legend:\t";
        for (int i = 0; i < groups.length; i++) {
            outputString += symbols[i] + "(" + groups[i] + ")";
        }
        return outputString;
    }

    /**
     * Bepaalt de grootste waarde die in de meegegeven tabel zit.
     *
     * @param tabel bevat data die in de tabel weergegeven kan worden.
     * @return De grootste waarde van de meegegeven tabel.
     */
    private int maxValueTable(int[][] tabel) {
        int maxVal = 0;
        for (int[] rij : tabel) {
            for (int item : rij) {
                if (item > maxVal) {
                    maxVal = item;
                }
            }
        }
        return maxVal;
    }

    /**
     * Maakt een kolom- of staaf-diagram dat al dan niet gestapeld is naargelang de meegegeven parameters.
     *
     * @param orientation bepaalt of er een kolom- of staaf-diagram moet gegenereerd worden.
     * @param stacked     bepaalt of de kolom- of staaf-diagram gestapeld moet zijn of niet.
     * @return Een kolom- of staaf-diagram dat al dan niet gestapeld is.
     */
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
                        if (NRGROUPS == 1) {
                            outputString += "\t\t\t\t";
                        } else {
                            outputString += "\t\t\t";
                        }
                    }
                    outputString += "\n";
                }
                for (String categorie : categories) {
                    if (categories.length == 1) {
                        outputString += categorie + "\t\t";
                    } else {
                        outputString += categorie + "\t\t\t";
                    }
                }
                outputString += "\n\n\n" + showLegend();
            } else {
                System.out.println("Buiten de scope van dit project.");
            }
        } else {
            System.out.println("ERROR: ongeldige oriëntatie.");
        }
        return outputString;
    }
}