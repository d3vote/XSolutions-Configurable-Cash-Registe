package at.ac.fhcampuswien.xsolutions;

import java.text.DecimalFormat;


public class Tables {
    private static int billNumber;
    private String allProducts = "";
    private double randomDouble;
    private double amountBeforeTaxes;
    private double amountAfterTaxes;
    private int tableNumber;
    final static double TAXES_MULTIPLIER = 1.20;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static int count;

    public Tables(){
        count++;
        billNumber++;
        this.tableNumber = count;
        this.amountBeforeTaxes = Double.parseDouble(df.format(randomDouble));
        this.amountAfterTaxes = Double.parseDouble(df.format(amountBeforeTaxes * TAXES_MULTIPLIER));
    }

    public static void setTablesCount(int count) {
        Tables.count = count;
    }

    public static int getCount(){
        return count;
    }

    public String getTableNumberAsString(){
        return "Tisch " + tableNumber;
    }

    public String getAmountAfterTaxes() {
        return String.valueOf(amountAfterTaxes);
    }

    public String getBill(){
        return tableNumber + System.lineSeparator() + allProducts;
    }
    public void addToAllProducts(String name){
        allProducts = allProducts + name + System.lineSeparator();
    }
}