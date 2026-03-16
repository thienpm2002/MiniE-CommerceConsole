package discount;

public class FixedDiscount implements Discount {
    private double fixAmout;

    public FixedDiscount(double fixAmout) {
        this.fixAmout = fixAmout;
    }

    @Override
    public double applyDiscount(double total) {
        if (total <= fixAmout)
            return total;
        return total - fixAmout;
    }

    @Override
    public void printDiscount() {
        System.out.println("Fix discount witd " + fixAmout + "$");
    }
}
