package discount;

public class PercentageDiscount implements Discount {
    private double percent;

    public PercentageDiscount(double percent) {
        this.percent = percent;
    }

    @Override
    public double applyDiscount(double total) {
        return total - total * (percent / 100);

    }

    @Override
    public void printDiscount() {
        System.out.println("Percent discount witd " + percent + "%");
    }
}
